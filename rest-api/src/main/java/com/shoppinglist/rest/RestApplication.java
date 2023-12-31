package com.shoppinglist.rest;

import com.shoppinglist.rest.persistence.SLEntity;
import com.shoppinglist.rest.persistence.SLItemEntity;
import com.shoppinglist.rest.persistence.SLItemRepository;
import com.shoppinglist.rest.persistence.SLRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.filter.ShallowEtagHeaderFilter;

import java.math.BigDecimal;

@SpringBootApplication
public class RestApplication {

    public static void main(String[] args) {
        SpringApplication.run(RestApplication.class, args);
    }

    @Bean
    public ShallowEtagHeaderFilter shallowEtagHeaderFilter() {
        return new ShallowEtagHeaderFilter();
    }

    @Bean
    public CommandLineRunner run(final SLRepository lists, final SLItemRepository items) {
        return (String[] args) -> {
            final var groceries = lists.save(new SLEntity(
                "Grocery list",
                new SLItemEntity("Banana", BigDecimal.valueOf(.75), 10),
                new SLItemEntity("Apple", BigDecimal.valueOf(1.15), 35),
                new SLItemEntity("Brick", BigDecimal.valueOf(.35), 350)
            ));

            items.saveAll(groceries.getItems());

            final var wishlist = lists.save(new SLEntity(
                "Wishlist",
                new SLItemEntity("Phone", BigDecimal.valueOf(999), 1),
                new SLItemEntity("Sock", BigDecimal.valueOf(3.5), 5)
            ));

            items.saveAll(wishlist.getItems());
        };
    }
}

package com.shoppinglist.rest.persistence;

import com.shoppinglist.rest.input.SLItemInput;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.lang.NonNull;

import java.math.BigDecimal;
import java.time.Instant;

@Entity
@Data
@NoArgsConstructor
public class SLItemEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @CreationTimestamp
    private Instant created;

    @UpdateTimestamp
    private Instant updated;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private SLEntity list;
    private String description;
    private BigDecimal price;
    private Integer quantity;

    public SLItemEntity(String description, BigDecimal price, int quantity) {
        this.description = description;
        this.price = price;
        this.quantity = quantity;
    }

    public SLItemEntity(SLItemInput input) {
        this.update(input);
    }

    public BigDecimal getTotal() {
        return price.multiply(new BigDecimal(quantity));
    }

    public SLItemEntity update(@NonNull SLItemInput input) {
        this.description = input.description();
        this.price = input.price();
        this.quantity = input.quantity();
        return this;
    }
}

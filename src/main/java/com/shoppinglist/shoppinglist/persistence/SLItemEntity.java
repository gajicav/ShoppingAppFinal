package com.shoppinglist.shoppinglist.persistence;

import com.shoppinglist.shoppinglist.input.SLItemInput;
import com.shoppinglist.shoppinglist.input.SLItemPostInput;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

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

    public SLItemEntity(SLItemPostInput input, SLEntity list) {
        this.merge(input, list);
    }

    public BigDecimal getTotal() {
        return price.multiply(new BigDecimal(quantity));
    }

    public SLItemEntity merge(@NonNull SLItemInput input, @Nullable SLEntity list) {
        if (input.description() != null) this.description = input.description();
        if (input.price() != null) this.price = input.price();
        if (input.quantity() != null) this.quantity = input.quantity();
        if (list != null) this.list = list;
        return this;
    }
}

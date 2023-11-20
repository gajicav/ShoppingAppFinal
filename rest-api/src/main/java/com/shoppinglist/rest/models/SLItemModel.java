package com.shoppinglist.rest.models;

import com.shoppinglist.rest.persistence.SLItemEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;
import org.springframework.lang.NonNull;

import java.math.BigDecimal;

@Data
@EqualsAndHashCode(callSuper = false)
@Relation(collectionRelation = "items", itemRelation = "item")
public class SLItemModel extends RepresentationModel<SLItemModel> {
    private final Long id;
    private final String description;
    private final Integer quantity;
    private final BigDecimal price;
    private final BigDecimal total;

    SLItemModel(@NonNull SLItemEntity item) {
        this.id = item.getId();
        this.description = item.getDescription();
        this.quantity = item.getQuantity();
        this.price = item.getPrice();
        this.total = item.getTotal();
    }
}

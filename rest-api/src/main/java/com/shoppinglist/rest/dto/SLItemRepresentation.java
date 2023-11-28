package com.shoppinglist.rest.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.shoppinglist.rest.persistence.SLItemEntity;
import org.springframework.lang.NonNull;

import java.math.BigDecimal;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record SLItemRepresentation(Long id, String description, Integer quantity, BigDecimal price, BigDecimal total) {
    SLItemRepresentation(@NonNull SLItemEntity item, BigDecimal total) {
        this(item.getId(), item.getDescription(), item.getQuantity(), item.getPrice(), total);
    }
}

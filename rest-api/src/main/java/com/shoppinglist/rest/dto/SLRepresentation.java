package com.shoppinglist.rest.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.shoppinglist.rest.persistence.SLEntity;
import org.springframework.lang.NonNull;

import java.math.BigDecimal;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record SLRepresentation(Long id, String description, Long count, BigDecimal total) {
    SLRepresentation(@NonNull SLEntity list, @NonNull Long count, BigDecimal total) {
        this(list.getId(), list.getDescription(), count, total);
    }
}

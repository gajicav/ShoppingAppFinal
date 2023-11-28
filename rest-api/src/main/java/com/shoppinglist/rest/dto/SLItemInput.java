package com.shoppinglist.rest.dto;

import jakarta.validation.constraints.*;

import java.math.BigDecimal;

public record SLItemInput(
    @NotBlank @Size(min = 1, max = 100) String description,
    @NotNull @Digits(integer = 6, fraction = 2) BigDecimal price,
    @NotNull @Positive @Max(9999) Integer quantity
) {
}

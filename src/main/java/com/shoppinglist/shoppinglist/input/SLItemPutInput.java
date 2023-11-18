package com.shoppinglist.shoppinglist.input;

import com.shoppinglist.shoppinglist.validators.NullOrNotBlank;
import jakarta.validation.constraints.*;

import java.math.BigDecimal;

public record SLItemPutInput(
    @NullOrNotBlank @Size(max = 100) String description,
    @Digits(integer = 6, fraction = 2) BigDecimal price,
    @Positive @Max(9999) Integer quantity,
    Long list_id
) implements SLItemInput {}

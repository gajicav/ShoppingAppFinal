package com.shoppinglist.rest.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record SLInput(
    @NotBlank @Size(min = 1, max = 100) String description
) {
}

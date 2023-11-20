package com.shoppinglist.rest.input;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record SLInput(
    @NotBlank @Size(min = 1, max = 100) String description
) {
}

package com.shoppinglist.shoppinglist.input;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record SLInput(
    @NotBlank @Size(max = 100) String description
) {
}

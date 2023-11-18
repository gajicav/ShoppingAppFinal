package com.shoppinglist.shoppinglist.validators;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Constraint(
    validatedBy = NullOrNotBlankValidator.class
)
@Target({ ElementType.FIELD, ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
public @interface NullOrNotBlank {
    String message() default "Must be null or not blank";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}

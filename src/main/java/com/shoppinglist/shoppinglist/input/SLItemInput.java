package com.shoppinglist.shoppinglist.input;

import java.math.BigDecimal;

public interface SLItemInput {
    String description();
    BigDecimal price();
    Integer quantity();
}

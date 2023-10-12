package com.shoppinglist.shoppinglist.dto;

import lombok.Data;

public @Data class Item {
    private int itemID;
    private String name;
    private String desc;
    private int price;
}

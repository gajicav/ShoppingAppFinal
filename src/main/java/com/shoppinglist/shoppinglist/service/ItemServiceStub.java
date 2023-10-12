package com.shoppinglist.shoppinglist.service;

import com.shoppinglist.shoppinglist.dao.ItemDAO;
import com.shoppinglist.shoppinglist.dto.Item;

public class ItemServiceStub {
    private ItemDAO itemDAO;

    public ItemServiceStub(){}

    public ItemServiceStub(ItemDAO itemDAO){}

    public Item save(Item item){
        return itemDAO.save(item);
    }
}

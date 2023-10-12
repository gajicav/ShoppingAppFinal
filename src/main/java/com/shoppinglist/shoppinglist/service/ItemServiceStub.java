package com.shoppinglist.shoppinglist.service;

import com.shoppinglist.shoppinglist.dao.ItemDAO;
import com.shoppinglist.shoppinglist.dto.Item;
import org.springframework.stereotype.Component;

@Component
public class ItemServiceStub implements ItemService{
    private ItemDAO itemDAO;

    public ItemServiceStub(){}

    public ItemServiceStub(ItemDAO itemDAO){}

    public Item save(Item item){
        return itemDAO.save(item);
    }
}

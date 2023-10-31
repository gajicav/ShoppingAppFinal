package com.shoppinglist.shoppinglist.dao;

import com.shoppinglist.shoppinglist.dto.ListItem;

import java.util.List;
import java.util.NoSuchElementException;

public interface IShoppingListDAO {
    ListItem saveItem(ListItem item);

    List<ListItem> fetchAll();

    ListItem fetch(int id) throws NoSuchElementException;

    void delete(int id) throws NoSuchElementException;
}
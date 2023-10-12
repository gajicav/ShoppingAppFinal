package com.shoppinglist.shoppinglist.service;

import com.shoppinglist.shoppinglist.dto.ListItem;

import java.util.List;
import java.util.NoSuchElementException;

public interface IShoppingListService {
    ListItem save(ListItem listItem);

    List<ListItem> fetchAll();

    ListItem fetchById(int id) throws NoSuchElementException;

    void delete(int id) throws NoSuchElementException;
}
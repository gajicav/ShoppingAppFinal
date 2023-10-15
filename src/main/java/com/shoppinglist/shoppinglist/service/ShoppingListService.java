package com.shoppinglist.shoppinglist.service;

import com.shoppinglist.shoppinglist.dao.IShoppingListDAO;
import com.shoppinglist.shoppinglist.dto.ListItem;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.NoSuchElementException;

@Component
public class ShoppingListService implements IShoppingListService {
    // Todo: Add cache annotations
    private final IShoppingListDAO shoppingListDAO;

    public ShoppingListService(IShoppingListDAO shoppingListDAO) {
        this.shoppingListDAO = shoppingListDAO;
    }

    @Override
    public ListItem saveItem(ListItem item) {
        return shoppingListDAO.saveItem(item);
    }

    @Override
    public List<ListItem> fetchAll() {
        return shoppingListDAO.fetchAll();
    }

    @Override
    public ListItem fetchById(int id) throws NoSuchElementException {
        return shoppingListDAO.fetch(id);
    }

    @Override
    public void delete(int id) throws NoSuchElementException {
        shoppingListDAO.delete(id);
    }
}
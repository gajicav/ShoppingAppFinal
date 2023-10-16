package com.shoppinglist.shoppinglist.service;

import com.shoppinglist.shoppinglist.dao.IShoppingListDAO;
import com.shoppinglist.shoppinglist.dto.ListItem;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.NoSuchElementException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component
public class ShoppingListService implements IShoppingListService {
    // Todo: Add cache annotations
    private final IShoppingListDAO shoppingListDAO;
    private static final Logger logger = LoggerFactory.getLogger(ShoppingListService.class);

    public ShoppingListService(IShoppingListDAO shoppingListDAO) {
        this.shoppingListDAO = shoppingListDAO;
    }

    @Override
    public ListItem save(ListItem item) {
        return shoppingListDAO.save(item);
    }

    @Override
    public List<ListItem> fetchAll() {
        return shoppingListDAO.fetchAll();
    }

    @Override
    public ListItem fetchById(int id) throws NoSuchElementException {
        try {
            return shoppingListDAO.fetch(id);
        } catch (NoSuchElementException e) {
            logger.error("Item not found with ID: {}", id);
            throw e;
        }
    }

    @Override
    public void delete(int id) throws NoSuchElementException {
        try {
            shoppingListDAO.delete(id);
            logger.info("Deleted item with ID: {}", id);
        } catch (NoSuchElementException e) {
            logger.error("Item not found with ID: {}", id);
            throw e;
        }
    }
}
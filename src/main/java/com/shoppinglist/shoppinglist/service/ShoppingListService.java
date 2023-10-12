package com.shoppinglist.shoppinglist.service;

import com.shoppinglist.shoppinglist.dao.IShoppingListDAO;
import com.shoppinglist.shoppinglist.dto.ListItem;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

@Component
public class ShoppingListService implements IShoppingListService {
    // Todo: Might want to benchmark this later to make sure cache annotations are correct
    private final IShoppingListDAO shoppingListDAO;

    public ShoppingListService(IShoppingListDAO specimenDAO) {
        this.shoppingListDAO = specimenDAO;
    }

    @Override
    @Transactional
    @CachePut(value="items", key="#listItem.itemId")
    public ListItem save(ListItem listItem) {
        return shoppingListDAO.save(listItem);
    }

    @Override
    @Transactional
    @Cacheable(value="items")
    public List<ListItem> fetchAll() {
        return shoppingListDAO.fetchAll();
    }

    @Override
    @Transactional
    @Cacheable(value="items", key="#id")
    public ListItem fetchById(int id) throws NoSuchElementException {
        return shoppingListDAO.fetch(id);
    }

    @Override
    @Transactional
    @CacheEvict(value="items", key="#id")
    public void delete(int id) throws NoSuchElementException {
        shoppingListDAO.delete(id);
    }
}
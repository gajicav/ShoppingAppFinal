package com.shoppinglist.shoppinglist.dao;

import com.shoppinglist.shoppinglist.dto.ListItem;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.NoSuchElementException;

@Repository
public class ShoppingListDAO implements IShoppingListDAO {

    private final ShoppingListRepository shoppingListRepository;

    public ShoppingListDAO(ShoppingListRepository shoppingListRepository) {
        this.shoppingListRepository = shoppingListRepository;
    }

    /**
     * Save an item to the shopping list.
     *
     * @param item The item to be saved.
     * @return The saved item.
     */
    @Override
    public ListItem saveItem(ListItem item) {
        return shoppingListRepository.save(item);
    }

    /**
     * Fetch all shopping list items.
     *
     * @return A list of all shopping list items.
     */
    @Override
    public List<ListItem> fetchAll() {
        return shoppingListRepository.findAll();
    }

    /**
     * Fetch a shopping list item by ID.
     *
     * @param id The ID of the item to fetch.
     * @return The fetched item.
     * @throws NoSuchElementException if the item is not found.
     */
    @Override
    public ListItem fetch(int id) throws NoSuchElementException {
        return shoppingListRepository.findById(id).orElseThrow();
    }

    /**
     * Delete a shopping list item by its ID.
     *
     * @param id The ID of the item to delete.
     * @throws NoSuchElementException if the item does not exist.
     */
    @Override
    @Transactional
    public void delete(int id) throws NoSuchElementException {
        ListItem item = shoppingListRepository.findById(id).orElseThrow();
        shoppingListRepository.delete(item);
    }
}
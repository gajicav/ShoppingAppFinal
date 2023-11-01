package com.shoppinglist.shoppinglist.dao;

import com.shoppinglist.shoppinglist.dto.ListItem;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;

import java.util.List;
import java.util.NoSuchElementException;

@Repository
public class ShoppingListDAO implements IShoppingListDAO {

    private final ShoppingListRepository shoppingListRepository;

    public ShoppingListDAO(ShoppingListRepository shoppingListRepository) {
        this.shoppingListRepository = shoppingListRepository;
    }

    @Override
    public ListItem save(ListItem item) {


        if (item.getName().isEmpty() || item.getName() == null){
            return null;
        }
        if (item.getPrice() == null || item.getPrice().toString().isEmpty()){
            item.setPrice(0.00);
        }
        if (item.getPrice() == null || item.getPrice().toString().isEmpty()){
            item.setQuantity(1);
        }

            return shoppingListRepository.save(item);

    }

    @Override
    public List<ListItem> fetchAll() {
        return shoppingListRepository.findAll();
    }

    @Override
    public ListItem fetch(int id) throws NoSuchElementException {
        return shoppingListRepository.findById(id).orElseThrow();
    }

    @Override
    @Transactional
    public void delete(int id) throws NoSuchElementException {
        ListItem item = shoppingListRepository.findById(id).orElseThrow();
        shoppingListRepository.delete(item);
    }
}
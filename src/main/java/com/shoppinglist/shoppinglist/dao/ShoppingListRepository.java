package com.shoppinglist.shoppinglist.dao;

import com.shoppinglist.shoppinglist.dto.ListItem;
import org.springframework.data.repository.ListCrudRepository;

public interface ShoppingListRepository extends ListCrudRepository<ListItem, Integer> {
}
package com.shoppinglist.rest.controllers;

import com.shoppinglist.rest.dto.SLItemInput;
import com.shoppinglist.rest.dto.*;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/items")
public class SLItemController {
    private final SLService lists;
    private final SLItemService items;

    public SLItemController(
        final SLService lists,
        final SLItemService items
    ) {
        this.lists = lists;
        this.items = items;
    }

    /**
     * Gets all items.
     *
     * @return collection of all items.
     */
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<SLItemRepresentation> fetchItems() {
        return items.all();
    }

    /**
     * Gets an item with the given ID.
     *
     * @param id the id of the item.
     * @return the item.
     */
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public SLItemRepresentation fetchItem(@PathVariable final Long id) {
        return items.one(id);
    }

    /**
     * Add an item to a list.
     *
     * @param input a valid item.
     * @return the new item.
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public SLItemRepresentation createItem(@Valid @RequestBody final SLItemInput input) {
        return items.post(input);
    }

    /**
     * Update an item.
     *
     * @param input a valid item.
     * @param id    the id of the item to update.
     * @return the new item.
     */
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public SLItemRepresentation updateItem(@Valid @RequestBody final SLItemInput input, @PathVariable final Long id) {
        return items.put(input, id);
    }

    /**
     * Move an item to another list.
     *
     * @param listId a valid list URI.
     * @param id    the id of the item to move.
     * @return the new item.
     */
    @PutMapping("/{id}/list")
    @ResponseStatus(HttpStatus.OK)
    public SLItemRepresentation moveItem(@Valid @RequestBody final Long listId, @PathVariable final Long id) {
        return items.move(listId, id);
    }

    /**
     * Delete an item with the given ID, if it exists.
     *
     * @param id the unique ID for the item.
     */
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteItem(@PathVariable final Long id) {
        items.delete(id);
    }

    /**
     * Find the shopping list that an item is in.
     *
     * @param id the unique ID for the item.
     * @return the shopping list if it was found.
     */
    @GetMapping("/{id}/list")
    @ResponseStatus(HttpStatus.OK)
    public SLRepresentation findList(@PathVariable final Long id) {
        return lists.find(id);
    }
}

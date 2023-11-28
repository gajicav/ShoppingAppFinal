package com.shoppinglist.rest.controllers;

import com.shoppinglist.rest.dto.SLInput;
import com.shoppinglist.rest.dto.*;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/lists")
public class SLController {
    private final SLService lists;
    private final SLItemService items;

    public SLController(
        final SLService lists,
        final SLItemService items
    ) {
        this.items = items;
        this.lists = lists;
    }

    /**
     * Gets all shopping lists.
     *
     * @return List of all shopping lists.
     */
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<SLRepresentation> fetchLists() {
        return lists.all();
    }

    /**
     * Gets a shopping list with the given ID.
     *
     * @param id the unique ID for the shopping list.
     * @return The shopping list.
     */
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public SLRepresentation fetchList(@PathVariable final Long id) {
        return lists.one(id);
    }

    /**
     * Create a shopping list.
     *
     * @param input a valid shopping list.
     * @return the new shopping list.
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public SLRepresentation createList(@Valid @RequestBody final SLInput input) {
        return lists.post(input);
    }

    /**
     * Update a shopping list.
     *
     * @param id a valid shopping list.
     * @return the new shopping list.
     */
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public SLRepresentation updateList(@Valid @RequestBody final SLInput input, @PathVariable final Long id) {
        return lists.put(input, id);
    }

    /**
     * Delete a shopping list with the given ID, if it exists.
     *
     * @param id the unique ID for the shopping list.
     */
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteList(@PathVariable final Long id) {
        lists.delete(id);
    }


    /**
     * Gets the items on the specified shopping list.
     *
     * @param id the unique ID for the shopping list.
     * @return collection of all items found on the list.
     */
    @GetMapping("/{id}/items")
    @ResponseStatus(HttpStatus.OK)
    public List<SLItemRepresentation> findItems(@PathVariable final Long id) {
        return items.find(id);
    }
}

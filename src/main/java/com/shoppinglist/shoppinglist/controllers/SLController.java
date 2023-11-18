package com.shoppinglist.shoppinglist.controllers;

import com.shoppinglist.shoppinglist.input.SLInput;
import com.shoppinglist.shoppinglist.persistence.SLEntity;
import com.shoppinglist.shoppinglist.persistence.SLItemRepository;
import com.shoppinglist.shoppinglist.persistence.SLRepository;
import com.shoppinglist.shoppinglist.models.*;
import jakarta.validation.Valid;
import org.springframework.hateoas.CollectionModel;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/lists")
public class SLController {
    private final SLRepository lists;
    private final SLRepresentationModelAssembler listAssembler;
    private final SLItemRepository items;
    private final SLItemRepresentationModelAssembler itemAssembler;

    public SLController(
        final SLRepository lists,
        final SLRepresentationModelAssembler listAssembler,
        final SLItemRepository items,
        final SLItemRepresentationModelAssembler itemAssembler
    ) {
        this.lists = lists;
        this.items = items;
        this.listAssembler = listAssembler;
        this.itemAssembler = itemAssembler;
    }

    /**
     * Gets all shopping lists.
     *
     * @return List of all shopping lists.
     */
    @GetMapping
    public CollectionModel<SLModel> fetchLists() {
        return listAssembler.toCollectionModel(lists.findAll());
    }

    /**
     * Gets a shopping list with the given ID.
     *
     * @param id the unique ID for the shopping list.
     * @return The shopping list.
     */
    @GetMapping("/{id}")
    public SLModel fetchList(@PathVariable final Long id) {
        return listAssembler.toModel(lists.findById(id).orElseThrow());
    }

    /**
     * Create a shopping list.
     *
     * @param input a valid shopping list.
     * @return the new shopping list.
     */
    @PostMapping
    public SLModel createList(@Valid @RequestBody final SLInput input) {
        return listAssembler.toModel(lists.save(new SLEntity(input)));
    }

    /**
     * Create or update a shopping list.
     *
     * @param id a valid shopping list.
     * @return the new shopping list.
     */
    @PutMapping("/{id}")
    public SLModel updateList(@Valid @RequestBody final SLInput input, @PathVariable final Long id) {
        return listAssembler.toModel(
            lists.findById(id)
                .map(list -> lists.save(list.merge(input)))
                .orElseThrow());
    }

    /**
     * Delete a shopping list with the given ID, if it exists.
     *
     * @param id the unique ID for the shopping list.
     */
    @DeleteMapping("/{id}")
    public void deleteList(@PathVariable final Long id) {
        lists.deleteById(id);
    }


    /**
     * Gets the items on the specified shopping list.
     *
     * @param id the unique ID for the shopping list.
     * @return collection of all items found on the list.
     */
    @GetMapping("/{id}/items")
    public CollectionModel<SLItemModel> findItems(@PathVariable final Long id) {
        return itemAssembler.toCollectionModel(items.findByListId(id).orElseThrow());
    }
}

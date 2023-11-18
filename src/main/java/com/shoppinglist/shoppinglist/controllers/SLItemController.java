package com.shoppinglist.shoppinglist.controllers;

import com.shoppinglist.shoppinglist.input.SLItemPostInput;
import com.shoppinglist.shoppinglist.input.SLItemPutInput;
import com.shoppinglist.shoppinglist.models.SLItemModel;
import com.shoppinglist.shoppinglist.models.SLItemRepresentationModelAssembler;
import com.shoppinglist.shoppinglist.models.SLModel;
import com.shoppinglist.shoppinglist.models.SLRepresentationModelAssembler;
import com.shoppinglist.shoppinglist.persistence.SLItemEntity;
import com.shoppinglist.shoppinglist.persistence.SLItemRepository;
import com.shoppinglist.shoppinglist.persistence.SLRepository;
import jakarta.validation.Valid;
import org.springframework.hateoas.CollectionModel;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/items")
public class SLItemController {
    private final SLRepository lists;
    private final SLRepresentationModelAssembler listAssembler;
    private final SLItemRepository items;
    private final SLItemRepresentationModelAssembler itemAssembler;

    public SLItemController(
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
     * Gets all items.
     *
     * @return collection of all items.
     */
    @GetMapping
    public CollectionModel<SLItemModel> fetchItems() {
        return itemAssembler.toCollectionModel(items.findAll());
    }

    /**
     * Gets an item with the given ID.
     *
     * @param id the id of the item.
     * @return the item.
     */
    @GetMapping("/{id}")
    public SLItemModel fetchItem(@PathVariable final Long id) {
        return itemAssembler.toModel(items.findById(id).orElseThrow());
    }

    /**
     * Add an item to a list.
     *
     * @param input a valid item.
     * @return the new item.
     */
    @PostMapping
    public SLItemModel createItem(@Valid @RequestBody final SLItemPostInput input) {
        final var list = lists.findById(input.list_id()).orElseThrow();
        return itemAssembler.toModel(items.save(new SLItemEntity(input, list)));
    }

    /**
     * Update an item.
     *
     * @param input a valid item.
     * @param id the id of the item to update.
     * @return the new item.
     */
    @PatchMapping("/{id}")
    public SLItemModel updateItem(@Valid @RequestBody final SLItemPutInput input, @PathVariable final Long id) {
        final var list_id = input.list_id();
        final var list = list_id == null ? null : lists.findById(list_id).orElseThrow();
        return itemAssembler.toModel(items.findById(id).map(item -> items.save(item.merge(input, list))).orElseThrow());
    }

    /**
     * Delete an item with the given ID, if it exists.
     *
     * @param id the unique ID for the item.
     */
    @DeleteMapping("/{id}")
    public void deleteItem(@PathVariable final Long id) {
        items.deleteById(id);
    }

    /**
     * Find the shopping list that an item is in.
     *
     * @param id the unique ID for the item.
     * @return the shopping list if it was found.
     */
    @GetMapping("/{id}/list")
    public SLModel findList(@PathVariable final Long id) {
        return listAssembler.toModel(lists.findByItemId(id).orElseThrow());
    }
}

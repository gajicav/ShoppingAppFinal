package com.shoppinglist.rest.controllers;

import com.shoppinglist.rest.input.SLItemInput;
import com.shoppinglist.rest.models.SLItemModel;
import com.shoppinglist.rest.models.SLItemRepresentationModelAssembler;
import com.shoppinglist.rest.models.SLModel;
import com.shoppinglist.rest.models.SLRepresentationModelAssembler;
import com.shoppinglist.rest.persistence.SLItemEntity;
import com.shoppinglist.rest.persistence.SLItemRepository;
import com.shoppinglist.rest.persistence.SLRepository;
import jakarta.validation.Valid;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriTemplate;

import java.net.URI;

@RestController
@RequestMapping("/api/items")
public class SLItemController {
    private static final UriTemplate LIST_URI_TEMPLATE = new UriTemplate("/api/lists/{id}");
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
    @ResponseStatus(HttpStatus.OK)
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
    @ResponseStatus(HttpStatus.OK)
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
    @ResponseStatus(HttpStatus.CREATED)
    public SLItemModel createItem(@Valid @RequestBody final SLItemInput input) {
        return itemAssembler.toModel(items.save(new SLItemEntity(input)));
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
    public SLItemModel updateItem(@Valid @RequestBody final SLItemInput input, @PathVariable final Long id) {
        return itemAssembler.toModel(items.save(items.findById(id).map(item -> item.update(input)).orElseThrow()));
    }

    /**
     * Move an item to another list.
     *
     * @param listUri a valid list URI.
     * @param id      the id of the item to move.
     * @return the new item.
     */
    @PutMapping("/{id}/list")
    @ResponseStatus(HttpStatus.OK)
    public SLItemModel moveItem(@Valid @RequestBody final URI listUri, @PathVariable final Long id) {
        final var listId = LIST_URI_TEMPLATE.match(listUri.toASCIIString()).get("id");
        final var list = lists.findById(Long.valueOf(listId)).orElseThrow();
        return itemAssembler.toModel(items.findById(id).map(item -> {
            item.setList(list);
            return items.save(item);
        }).orElseThrow());
    }

    /**
     * Delete an item with the given ID, if it exists.
     *
     * @param id the unique ID for the item.
     */
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
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
    @ResponseStatus(HttpStatus.OK)
    public SLModel findList(@PathVariable final Long id) {
        return listAssembler.toModel(lists.findByItemId(id).orElseThrow());
    }
}

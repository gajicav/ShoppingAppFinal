package com.shoppinglist.rest.controllers;

import com.shoppinglist.rest.input.SLInput;
import com.shoppinglist.rest.models.SLItemModel;
import com.shoppinglist.rest.models.SLItemRepresentationModelAssembler;
import com.shoppinglist.rest.models.SLModel;
import com.shoppinglist.rest.models.SLRepresentationModelAssembler;
import com.shoppinglist.rest.persistence.SLEntity;
import com.shoppinglist.rest.persistence.SLItemRepository;
import com.shoppinglist.rest.persistence.SLRepository;
import jakarta.validation.Valid;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
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
    @ResponseStatus(HttpStatus.OK)
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
    @ResponseStatus(HttpStatus.OK)
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
    @ResponseStatus(HttpStatus.CREATED)
    public SLModel createList(@Valid @RequestBody final SLInput input) {
        return listAssembler.toModel(lists.save(new SLEntity(input)));
    }

    /**
     * Update a shopping list.
     *
     * @param id a valid shopping list.
     * @return the new shopping list.
     */
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
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
    @ResponseStatus(HttpStatus.NO_CONTENT)
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
    @ResponseStatus(HttpStatus.OK)
    public CollectionModel<SLItemModel> findItems(@PathVariable final Long id) {
        return itemAssembler.toCollectionModel(items.findByListId(id).orElseThrow());
    }
}

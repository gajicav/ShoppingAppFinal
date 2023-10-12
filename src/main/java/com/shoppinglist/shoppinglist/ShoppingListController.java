package com.shoppinglist.shoppinglist;

import com.shoppinglist.shoppinglist.dto.ListItem;
import com.shoppinglist.shoppinglist.service.IShoppingListService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.NoSuchElementException;

@Controller
public class ShoppingListController {

    private final IShoppingListService shoppingListService;

    public ShoppingListController(IShoppingListService shoppingListService) {
        this.shoppingListService = shoppingListService;
    }

    /**
     * Handle the root endpoint and return the home page.
     *
     * @return home/index.html
     */
    @GetMapping("/")
    public String index() {
        return "index";
    }

    /**
     * Save an item and redirect to the home page.
     *
     * @return home/index.html
     */
    @PostMapping("/save")
    public String save(ListItem item) {
        try {
            createSpecimen(item);
        } catch (Exception e) {
            // No error page yet
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), e);
        }
        return "index";
    }

    /**
     * Fetch an item with the given ID, returns one of the following status codes:
     * 200: item found
     * 404: item not found
     *
     * @param id the unique ID for the item.
     * @return a JSON representation of the item object if it was found.
     */
    @GetMapping("/{id}")
    public ListItem fetchSpecimen(@PathVariable int id) {
        try {
            return shoppingListService.fetchById(id);
        } catch (NoSuchElementException e) {
            e.fillInStackTrace();
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
        }
    }

    /**
     * Create or update an item object, returns one of the following status codes:
     * 201: successfully created a new item.
     * 400: unable to create an item because the data is malformed.
     *
     * @param item a JSON representation of an item object.
     * @return the newly created item object.
     */
    @PostMapping(path="/save", consumes="application/json", produces="application/json")
    public ListItem createSpecimen(@RequestBody ListItem item) {
        try {
            return shoppingListService.save(item);
        } catch (IllegalArgumentException e) {
            e.fillInStackTrace();
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage(), e);
        }
    }

    /**
     * Delete an item with the given ID, returns one of the following status codes:
     * 204: item successfully deleted
     * 200: item does not exist
     *
     * @param id the unique ID for the item.
     */
    @DeleteMapping("/delete/{id}/")
    public void deleteSpecimen(@PathVariable int id) {
        try {
            shoppingListService.delete(id);
        } catch (NoSuchElementException e) {
            e.fillInStackTrace();
            throw new ResponseStatusException(HttpStatus.OK, e.getMessage(), e);
        }
    }

    // Todo: Add error page + privacy page.
}
package com.shoppinglist.shoppinglist;

import com.shoppinglist.shoppinglist.dto.Item;
import com.shoppinglist.shoppinglist.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;

@Controller
public class HomeController {
    @Autowired
    ItemService itemService;

    /**
     * Handle the root endpoint and return the home page.
     *
     * @return home/index.html
     */

    @GetMapping("/")
    public String index(Model model) {
        Item item = new Item();
        item.setDesc("");
        item.setName("");
        item.setPrice(0);
        model.addAttribute(item);
        return "home/index";
    }

    /**
     * Endpoint to fetch Items
     */
    @GetMapping("/item")
    public ResponseEntity fetchAllItems(){
        return new ResponseEntity(HttpStatus.OK);
    }

    /**
     * Endpoint to fetch Item by ID
     */
    @GetMapping("/item/{id}")
    public ResponseEntity fetchItemById(@PathVariable("id") String id){
        return new ResponseEntity(HttpStatus.OK);
    }

    /**
     * Endpoint to create Item
     */
    @PostMapping(value = "/item", consumes = "application/json", produces = "application/json")
    public Item createItem(@RequestBody Item item){
        return item;
    }

    /**
     * Endpoint to delete Item by ID
     */
    @DeleteMapping("/item/{id}")
    public ResponseEntity deleteItem(@PathVariable("id") String id){
        return new ResponseEntity(HttpStatus.OK);
    }

    /**
     * Endpoint to save Item
     */
    @RequestMapping("/saveItem")
    public String saveItem(Item item){
        try {
            itemService.save(item);
        } catch (Exception e){
            e.printStackTrace();
            return "home/index";
        }
        return "home/index";
    }

    // Todo: Add error page + privacy page.
}
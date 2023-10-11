package com.shoppinglist.shoppinglist;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    /**
     * Handle the root endpoint and return the home page.
     *
     * @return home/index.html
     */

    @GetMapping("/")
    public String index() {
        return "home/index";
    }

    // Todo: Add error page + privacy page.
}
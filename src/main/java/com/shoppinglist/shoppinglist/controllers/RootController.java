package com.shoppinglist.shoppinglist.controllers;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.mvc.BasicLinkBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class RootController {

    @GetMapping
    public RepresentationModel<?> api() {
        final String root = BasicLinkBuilder.linkToCurrentMapping() + "/api";

        return new RepresentationModel<>().add(
            Link.of(root + "/lists", "lists"),
            Link.of(root + "/items", "items")
        );
    }
}

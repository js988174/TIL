package com.work.Blog.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PostController {

    @GetMapping
    public String get() {
        return "Hello World";
    }
}

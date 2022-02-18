package com.spring.site.web;

import lombok.extern.java.Log;
import org.springframework.web.bind.annotation.GetMapping;

public class LoginController {

    @GetMapping("/login")
    public String login() throws Exception {
        System.out.println("loginPage");
        return "login";
    }

}

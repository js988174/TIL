package io.security.springsecurity.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MessageController {

    @GetMapping(value = "/message")
    public String message() throws Exception {

        return "user/messages";
    }

    @GetMapping(value = "/api/messages")
    public String apiMessage() {
        return "messages ok";
    }
}

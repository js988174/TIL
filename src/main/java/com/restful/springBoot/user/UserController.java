package com.restful.springBoot.user;

import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    private UserDaoService service;

    public UserController(UserDaoService service) {
        this.service = service;
    }

    @GetMapping("/users")
    public List<User> allUserList() {
        return service.findAll();
    }

    @GetMapping("/users/{id}")
    public User oneUser(@PathVariable int id) {
        return service.findOne(id);
    }

    @PostMapping("/users")
    public void createUser(@RequestBody User user) {
        User savedUser = service.save(user);

    }
}

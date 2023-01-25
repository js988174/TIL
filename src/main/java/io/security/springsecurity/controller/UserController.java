package io.security.springsecurity.controller;

import io.security.springsecurity.domain.AccountDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping(value = "/mypage")
    public String myPage() throws Exception {
        return "home";
    }

    @GetMapping("/users")
    public String createUser() throws Exception {
        return "login/register";
    }

    @PostMapping("/users")
    public String createUser(AccountDto accountDto) throws Exception {

        ModelMapper modelMapper = new ModelMapper();
        modelMapper.map(accountDto, AccountDto.class);

        return "redirect:/";
    }
}

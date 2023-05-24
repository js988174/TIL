package io.security.springsecurity.service.impl;

import io.security.springsecurity.domain.Account;
import io.security.springsecurity.repository.UserRepository;
import io.security.springsecurity.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public void createUser(Account account) {
        userRepository.save(account);
    }
}

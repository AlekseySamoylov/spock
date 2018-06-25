package com.alekseysamoylov.spock.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alekseysamoylov.spock.entity.User;
import com.alekseysamoylov.spock.exception.AuthenticationException;
import com.alekseysamoylov.spock.repository.UserRepository;

@Component
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public boolean processUser(String name) {
        User user = userRepository.findOneByName(name);
        if (user == null) {
            throw new AuthenticationException("User is not found");
        }
        return true;
    }


    public int sum(int a, int b) {
        return a + b;
    }
}

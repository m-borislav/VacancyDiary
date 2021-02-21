package com.vacancydiary.inmost.demo.controller;

import com.vacancydiary.inmost.demo.model.User;
import com.vacancydiary.inmost.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    private final UserRepository userRepository;

    @Autowired
    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping
    public User findUserByFirstName(@PathVariable String firstName){
        return userRepository.findByFirstName(firstName);
    }

}

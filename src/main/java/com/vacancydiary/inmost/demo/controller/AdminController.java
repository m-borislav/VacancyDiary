package com.vacancydiary.inmost.demo.controller;

import com.vacancydiary.inmost.demo.repository.UserRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(value = "Admin Controller")
@PreAuthorize("hasAnyAuthority('ADMIN')")
@RestController
@RequestMapping(path = "/admin")
public class AdminController {

    private final UserRepository userRepository;


    @Autowired
    public AdminController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @ApiOperation(value = "Deleting users")
    @DeleteMapping(value = "/user/{id}")
    public void deleteUserById(@PathVariable("id") Long id){
        userRepository.deleteById(id);
    }
}

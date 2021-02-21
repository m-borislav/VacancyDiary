package com.vacancydiary.inmost.demo.controller;

import com.vacancydiary.inmost.demo.model.User;
import com.vacancydiary.inmost.demo.service.RegisterService;
import com.vacancydiary.inmost.demo.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Api(value = "Register controller")
@RestController
public class RegisterController {

    private UserService userService;
    private RegisterService registerService;

    @Autowired

    public RegisterController(UserService userService,
                              RegisterService registerService) {
        this.userService = userService;
        this.registerService = registerService;
    }

    @ApiOperation(value = "Register")
    @PostMapping(value = "/register", consumes = "application/json;charset=UTF-8", produces = "application/json")
    public ResponseEntity<User> register(@RequestBody User user){
        final UserDetails userDetails = userService.loadUserByUsername(user.getEmail());

        if (userDetails != null){
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        } else {

            return ResponseEntity.ok(registerService.registerUser(user));
        }
    }
}

package com.vacancydiary.inmost.demo.controller;

import com.vacancydiary.inmost.demo.model.User;
import com.vacancydiary.inmost.demo.service.AuthenticationService;
import com.vacancydiary.inmost.demo.service.UserService;
import com.vacancydiary.inmost.demo.util.JwtTokenUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Api(value = "Authentication controller")
@RestController
public class AuthenticationController {
    private final JwtTokenUtil jwtTokenUtil;
    private final UserService userService;
    private final AuthenticationService authenticationService;

    @Autowired
    public AuthenticationController(JwtTokenUtil jwtTokenUtil, UserService userService, AuthenticationService authenticationService) {
        this.jwtTokenUtil = jwtTokenUtil;
        this.userService = userService;
        this.authenticationService = authenticationService;
    }

    @ApiOperation(value = "Login")
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(@RequestBody User user) {
        User userFromDb = userService.loadUserByUsername(user.getEmail());

        if (authenticationService.authenticate(user, userFromDb)) {
            final String token = jwtTokenUtil.generateToken(user);
            userFromDb.setToken(token);
            userFromDb.setTokenExpirationDate(jwtTokenUtil.getExpirationDateFromToken(token));
            return ResponseEntity.ok(userFromDb);
        } else return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }
}

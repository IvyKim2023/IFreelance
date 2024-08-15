package com.IvyJin.IFreelance.controller;

import com.IvyJin.IFreelance.model.AppUser;
import com.IvyJin.IFreelance.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private UserService service;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody AppUser user) {
        return service.register(user);
    }

    @PostMapping("/login")
    public String login(@RequestBody AppUser user) {
        return service.verify(user);
    }
}

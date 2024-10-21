package com.arrowloki.scim2.controller;

import org.springframework.web.bind.annotation.*;

import com.arrowloki.scim2.model.User;
import com.arrowloki.scim2.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public List<User> getAllUsers(){
        return userService.getAllUsers();
    }

}

package com.arrowloki.scim2.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class User {
    @GetMapping("/")
    public String Home(){
        String s = "Hi Lokesh";
        return s;
    }

    @GetMapping("/about")
    public String About(){
        String s = "Hi DB";
        return s;
    }
}

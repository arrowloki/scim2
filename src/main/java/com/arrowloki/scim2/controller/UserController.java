package com.arrowloki.scim2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.arrowloki.scim2.dto.ScimUserRequest;
import com.arrowloki.scim2.dto.ScimUserResponse;
import com.arrowloki.scim2.service.UserService;
import com.arrowloki.scim2.model.User;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/scim/v2/Users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<ScimUserResponse> createUser(@RequestBody ScimUserRequest request) {
        User createdUser = userService.createUser(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(convertToResponse(createdUser));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ScimUserResponse> getUserById(@PathVariable Long id) {
        Optional<User> user = userService.getUserById(id);
        return user.map(value -> ResponseEntity.ok(convertToResponse(value)))
                   .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @GetMapping
    public ResponseEntity<List<ScimUserResponse>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        List<ScimUserResponse> responses = users.stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
        return ResponseEntity.ok(responses);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ScimUserResponse> updateUser(@PathVariable Long id, @RequestBody ScimUserRequest request) {
        User updatedUser = userService.updateUser(id, request);
        return ResponseEntity.ok(convertToResponse(updatedUser));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

    private ScimUserResponse convertToResponse(User user) {
        ScimUserResponse response = new ScimUserResponse();
        response.setId(user.getId());
        response.setUserName(user.getUserName());
        response.setFirstName(user.getFirstName());
        response.setLastName(user.getLastName());
        response.setEmails(user.getEmails());
        response.setPhoneNumbers(user.getPhoneNumbers());
        return response;
    }
}

package com.test.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import com.test.entity.User;
import com.test.service.UserService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")  // Base URL
public class UserController {
    @Autowired
    private UserService userService;

    // Get All Users
    @GetMapping("/getAllUsers")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    // Get User by ID
    @GetMapping("/{id}")  // Correct path mapping
    public ResponseEntity<User> getUserById(@PathVariable("id") Long id) {
        Optional<User> user = userService.getUserById(id);
        
        // If user is not found, return 204 No Content
        if (user.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        
        // If user is found, return 200 OK with user data
        return ResponseEntity.ok(user.get());
    }

    // Create User
    @PostMapping("/register")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User savedUser = userService.registerUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedUser);
    }

    // Update User
    @PutMapping("/update/{id}")
    public User updateUser(@PathVariable Long id, @RequestBody User user) {
        return userService.updateUser(id, user);
    }

    // Delete User
    @DeleteMapping("/delete/{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }
}

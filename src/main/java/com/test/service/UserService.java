package com.test.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.entity.User;
import com.test.repository.UserRepo;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepo userRepository;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

   

    public User registerUser(User user) {
        // Validate unique email
        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            throw new RuntimeException("Email already in use");
        }
        // Validate unique phone number
        if (userRepository.findByPhonenumber(user.getPhonenumber()).isPresent()) {
            throw new RuntimeException("Phone number already in use");
        }

        return userRepository.save(user);
    }
    

    // Get User by ID
    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);  // Returns an Optional<User>
    }

  

    public User updateUser(Long id, User userDetails) {
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
        user.setUsername(userDetails.getUsername());
        user.setPhonenumber(userDetails.getPhonenumber());
        user.setCountry(userDetails.getCountry());
        user.setEmail(userDetails.getEmail());
        return userRepository.save(user);
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}

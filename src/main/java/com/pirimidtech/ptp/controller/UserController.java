package com.pirimidtech.ptp.controller;

import com.pirimidtech.ptp.entity.User;
import com.pirimidtech.ptp.exception.NotFoundException;
import com.pirimidtech.ptp.exception.UserAlreadyExistException;
import com.pirimidtech.ptp.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;
import java.util.Random;
import java.util.UUID;

@RestController
public class UserController {

    private final String NSDL = "12011111";
    private final int minimum = 0;
    private final int maximum = 99999999;
    @Autowired
    private UserService userService;

    @PostMapping(value = "/signup")
    public ResponseEntity<User> userRegisteration(@RequestBody User user) {
        Optional<User> oldUser = userService.getUserByEmail(user.getEmail());
        if (oldUser.isPresent()) {
            throw new UserAlreadyExistException("User with this email id is already exist");
        }
        int randomNum = minimum + new Random().nextInt((maximum - minimum) + 1);
        String dpId = NSDL + String.valueOf(randomNum);
        user.setDpId(dpId);
        userService.addUser(user);
        return ResponseEntity.ok().body(user);
    }

    @PutMapping(value = "/users")
    public ResponseEntity<User> userProfileUpdate(@RequestBody User user) {
        Optional<User> oldUser = userService.getUserByEmail(user.getEmail());
        if (oldUser.isPresent() && !(oldUser.get().getId().toString().equals(user.getId().toString()))) {
            throw new UserAlreadyExistException("User with this email id is already exist");
        }
        userService.addUser(user);
        return ResponseEntity.ok().body(user);
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<User> getUserById(@PathVariable("id") UUID userId) {
        Optional<User> user = userService.getUserById(userId);
        if (user.isPresent())
            return ResponseEntity.ok().body(user.get());
        throw new NotFoundException();
    }
}

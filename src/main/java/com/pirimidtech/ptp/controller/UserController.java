package com.pirimidtech.ptp.controller;

import com.pirimidtech.ptp.entity.User;
import com.pirimidtech.ptp.exception.NotFoundException;
import com.pirimidtech.ptp.exception.UserAlreadyExistException;
import com.pirimidtech.ptp.service.user.UserService;
import com.pirimidtech.ptp.util.RequestUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
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

    @Autowired
    private RequestUtil requestUtil;

    @PostMapping(value = "/signup")
    public ResponseEntity<User> userRegistration(@RequestBody User user) {
        Optional<User> oldUser = userService.getUserByEmail(user.getEmail());
        if (oldUser.isPresent()) {
            throw new UserAlreadyExistException("Email id already exists");
        }
        int randomNum = minimum + new Random().nextInt((maximum - minimum) + 1);
        String dpId = NSDL + String.valueOf(randomNum);
        user.setDpId(dpId);
        String userPassword = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());
        user.setPassword((userPassword));
        userService.addUser(user);
        return ResponseEntity.ok().body(user);
    }

    @PutMapping(value = "/users")
    public ResponseEntity<User> userProfileUpdate(@RequestBody User user) {
        Optional<User> oldUser = userService.getUserByEmail(user.getEmail());
        if (oldUser.isPresent() && !(oldUser.get().getId().toString().equals(user.getId().toString()))) {
            throw new UserAlreadyExistException("Email id already exists");
        }
        userService.addUser(user);
        return ResponseEntity.ok().body(user);
    }

    @GetMapping("/users/profile")
    public ResponseEntity<User> getUser(HttpServletRequest httpServletRequest) {
        String jwtToken = requestUtil.getTokenFromCookies(httpServletRequest);
        UUID userId = requestUtil.getUserIdFromToken(jwtToken);
        Optional<User> user = userService.getUserById(userId);
        if (user.isPresent())
            return ResponseEntity.ok().body(user.get());
        throw new NotFoundException();
    }
}

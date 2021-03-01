package com.pirimidtech.ptp.controller;

import com.pirimidtech.ptp.entity.User;
import com.pirimidtech.ptp.service.user.UserService;
import com.pirimidtech.ptp.util.JwtToken;
import com.pirimidtech.ptp.util.JwtTokenUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@Slf4j
public class AuthenticationController {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @PostMapping("/login")
    public ResponseEntity<JwtToken> login(@RequestBody User user) {
        log.info("get login request from user {}", user.getEmail());
        Optional<User> userDetail = userService.verifyUser(user.getEmail(), user.getPassword());
        log.info("check if user present {}", userDetail.isPresent());
        if (userDetail.isPresent()) {
            String token = jwtTokenUtil.generateToken(userDetail.get());
            JwtToken jwtToken=new JwtToken();
            jwtToken.setToken(token);
            log.info("token {}",jwtToken.getToken());
            return ResponseEntity.ok(jwtToken);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
}

package com.pirimidtech.ptp.controller;

import com.pirimidtech.ptp.entity.User;
import com.pirimidtech.ptp.service.user.UserService;
import com.pirimidtech.ptp.util.JwtTokenUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.Optional;
import java.util.UUID;

@RestController
@Slf4j
public class AuthenticationController {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Value("${cookie.maxAge}")
    private int maxCookieAge;

    private static final String TOKEN = "token";

    @GetMapping("/user")
    public ResponseEntity<UUID> getUser(HttpServletRequest httpServletRequest) {
        String jwtToken = null;
        UUID userId = null;
        final Cookie[] cookies = httpServletRequest.getCookies();
        if (cookies != null) {
            Optional<Cookie> optionalCookie = Arrays.stream(cookies).filter(cookie -> cookie.getName().equals(TOKEN)).findFirst();
            if (optionalCookie.isPresent()) {
                jwtToken = optionalCookie.get().getValue();
            }
        }
        userId = jwtTokenUtil.getUserIdFromToken(jwtToken);
        Optional<User> userDetail = userService.getUserById(userId);
        if (userDetail.isPresent()) {
            userId = userDetail.get().getId();
            return ResponseEntity.ok(userId);
        }
        else {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User user, HttpServletResponse response) {
        log.info("login request from user {}", user.getEmail());
        Optional<User> userDetail = userService.verifyUser(user.getEmail(), user.getPassword());
        if (userDetail.isPresent()) {
            String token = jwtTokenUtil.generateToken(userDetail.get());
            Cookie cookie = new Cookie(TOKEN, token);
            cookie.setPath("/");
            cookie.setHttpOnly(true);
            cookie.setMaxAge(maxCookieAge);
            response.addCookie(cookie);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(HttpServletResponse response) {

        Cookie cookie = new Cookie(TOKEN, null);
        cookie.setPath("/");
        cookie.setHttpOnly(true);
        cookie.setMaxAge(0);
        response.addCookie(cookie);

        return ResponseEntity.ok().build();
    }
}

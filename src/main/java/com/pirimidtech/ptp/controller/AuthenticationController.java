package com.pirimidtech.ptp.controller;

import com.pirimidtech.ptp.DTO.UserSessionDTO;
import com.pirimidtech.ptp.entity.User;
import com.pirimidtech.ptp.service.user.UserService;
import com.pirimidtech.ptp.util.JwtTokenUtil;
import com.pirimidtech.ptp.util.RequestUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;
import java.util.UUID;

@RestController
@Slf4j
public class AuthenticationController {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private RequestUtil requestUtil;

    @Value("${cookie.maxAge}")
    private int maxCookieAge;

    private static final String TOKEN = "token";

    @GetMapping("/user")
    public ResponseEntity<UserSessionDTO> getUser(HttpServletRequest httpServletRequest) {
        String jwtToken = requestUtil.getTokenFromCookies(httpServletRequest);
        UUID userId = requestUtil.getUserIdFromToken(jwtToken);
        Optional<User> userDetail = userService.getUserById(userId);
        UserSessionDTO userSessionDTO = new UserSessionDTO();
        if (userDetail.isPresent()) {
            userSessionDTO.setUserId(userDetail.get().getId());
            userSessionDTO.setEmail(userDetail.get().getEmail());
            userSessionDTO.setName(userDetail.get().getName());
        }
        return ResponseEntity.ok(userSessionDTO);
    }

    @PostMapping("/login")
    public ResponseEntity<UserSessionDTO> login(@RequestBody User user, HttpServletResponse response) {
        log.info("login request from user {}", user.getEmail());
        Optional<User> userDetail = userService.getUserByEmail(user.getEmail());
        if (userDetail.isPresent()) {
            boolean isValid = BCrypt.checkpw(user.getPassword(), userDetail.get().getPassword());
            if (isValid) {
                String token = jwtTokenUtil.generateToken(userDetail.get());
                Cookie cookie = new Cookie(TOKEN, token);
                cookie.setPath("/");
                cookie.setHttpOnly(true);
                cookie.setMaxAge(maxCookieAge);
                response.addCookie(cookie);
                UserSessionDTO userSessionDTO = new UserSessionDTO(userDetail.get().getId(), userDetail.get().getName(), userDetail.get().getEmail());
                return ResponseEntity.ok(userSessionDTO);
            }
        }
        return ResponseEntity.badRequest().build();
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

package com.pirimidtech.ptp.filter;

import com.pirimidtech.ptp.entity.User;
import com.pirimidtech.ptp.service.user.UserService;
import com.pirimidtech.ptp.util.JwtTokenUtil;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.Optional;
import java.util.UUID;

@Component
@Slf4j
public class AuthorizationFilter extends OncePerRequestFilter {

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private UserService userService;

    private static final String TOKEN = "token";

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {

        UUID userId = null;
        String jwtToken = null;
        final Cookie[] cookies = httpServletRequest.getCookies();
        if (cookies != null) {
            Optional<Cookie> optionalCookie = Arrays.stream(cookies).filter(cookie -> cookie.getName().equals(TOKEN)).findFirst();
            if (optionalCookie.isPresent()) {
                jwtToken = optionalCookie.get().getValue();
            }
        }
        if (jwtToken != null) {
            try {
                userId = jwtTokenUtil.getUserIdFromToken(jwtToken);
            } catch (IllegalArgumentException illegalArgumentException) {
                log.info("Unable to extract userId from JWT {}", jwtToken);
            } catch (ExpiredJwtException expiredJwtException) {
                log.info("JWT {} has expired", jwtToken);
            } catch (SignatureException | MalformedJwtException signatureException) {
                log.info("tempered JWT found: {}", jwtToken);
            }
            if (userId != null) {
                Optional<User> user = userService.getUserById(userId);
                if (user.isPresent() && jwtTokenUtil.validateToken(jwtToken, user.get())) {
                    filterChain.doFilter(httpServletRequest, httpServletResponse);
                    return;
                }
            }
        }
        httpServletResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        String path = request.getRequestURI();
        return "/login".equals(path);
    }
}

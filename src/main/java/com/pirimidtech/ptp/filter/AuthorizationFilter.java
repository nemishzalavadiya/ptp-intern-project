package com.pirimidtech.ptp.filter;

import com.pirimidtech.ptp.entity.User;
import com.pirimidtech.ptp.service.user.UserService;
import com.pirimidtech.ptp.util.JwtTokenUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
@Slf4j
public class AuthorizationFilter extends OncePerRequestFilter {

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private UserService userService;

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {

        final String requestTokenHeaderAuthentication = httpServletRequest.getHeader("Authentication");
        UUID userId = null;
        String jwtToken = null;
        log.info("Got the token: {}", requestTokenHeaderAuthentication);
        if (requestTokenHeaderAuthentication != null && requestTokenHeaderAuthentication.startsWith("Bearer ")) {
            jwtToken = requestTokenHeaderAuthentication.substring(7);
            userId = jwtTokenUtil.getUserIdFromToken(jwtToken);
        }
        if (userId != null) {
            Optional<User> user = userService.getUserById(userId);
            if (user.isPresent() && jwtTokenUtil.validateToken(jwtToken, user.get())) {
                filterChain.doFilter(httpServletRequest, httpServletResponse);
            }
        }
        httpServletResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");
        return;
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        String path = request.getRequestURI();
        log.info("requested path: {}", path);
        Pattern pattern = Pattern.compile("/ptp.*");
        Matcher matcher = pattern.matcher(path);
        return "/login".equals(path) || matcher.find();
    }
}

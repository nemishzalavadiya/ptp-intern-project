package com.pirimidtech.ptp.filter;

import com.pirimidtech.ptp.entity.User;
import com.pirimidtech.ptp.service.user.UserService;
import com.pirimidtech.ptp.util.JwtTokenUtil;
import com.pirimidtech.ptp.util.RequestUtil;
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

@Component
@Slf4j
public class AuthorizationFilter extends OncePerRequestFilter {

    private static final String TOKEN = "token";
    @Autowired
    private RequestUtil requestUtil;
    @Autowired
    private UserService userService;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {

        String jwtToken = requestUtil.getUserIdFromCookies(httpServletRequest);
        UUID userId = requestUtil.getUserIdFromToken(jwtToken);
        if (jwtToken != null && userId != null) {
            Optional<User> user = userService.getUserById(userId);
            if (user.isPresent() && jwtTokenUtil.validateToken(jwtToken, user.get())) {
                filterChain.doFilter(httpServletRequest, httpServletResponse);
                return;
            }
        }
        httpServletResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        //String path = request.getRequestURI();
        //return "/login".equals(path) || "/signup".equals(path) || "/register".equals(path);
        return true;
    }
}

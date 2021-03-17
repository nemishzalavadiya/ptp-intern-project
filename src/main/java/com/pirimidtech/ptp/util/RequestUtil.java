package com.pirimidtech.ptp.util;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Optional;
import java.util.UUID;

@Component
@Slf4j
public class RequestUtil {

    private static final String TOKEN = "token";
    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    public String getUserIdFromCookies(HttpServletRequest httpServletRequest) {
        String jwtToken = null;
        final Cookie[] cookies = httpServletRequest.getCookies();
        if (cookies != null) {
            Optional<Cookie> optionalCookie = Arrays.stream(cookies).filter(cookie -> cookie.getName().equals(TOKEN)).findFirst();
            if (optionalCookie.isPresent()) {
                jwtToken = optionalCookie.get().getValue();
            }
        }
        return jwtToken;
    }

    public UUID getUserIdFromToken(String jwtToken) {
        UUID userId = null;
        try {
            userId = jwtTokenUtil.getUserIdFromToken(jwtToken);
        } catch (IllegalArgumentException illegalArgumentException) {
            log.info("Unable to extract userId from JWT {}", jwtToken);
        } catch (ExpiredJwtException expiredJwtException) {
            log.info("JWT {} has expired", jwtToken);
        } catch (SignatureException | MalformedJwtException signatureException) {
            log.info("Tampered JWT found: {}", jwtToken);
        }
        return userId;
    }
}

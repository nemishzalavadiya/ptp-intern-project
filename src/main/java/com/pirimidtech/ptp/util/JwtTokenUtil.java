package com.pirimidtech.ptp.util;

import com.pirimidtech.ptp.entity.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.function.Function;

@Component
public class JwtTokenUtil implements Serializable {

    @Value("${jwt.secret}")
    private String jwtSecret;

    @Value("${jwt.token.validity}")
    public long JWT_TOKEN_VALIDITY;

    //retrieve userId from jwt token
    public UUID getUserIdFromToken(String token) {
        return UUID.fromString(getClaimFromToken(token, Claims::getSubject));
    }

    //extract expiration date from jwt token
    public Date getExpirationDateFromToken(String token) {
        return getClaimFromToken(token, Claims::getExpiration);
    }

    public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }

    //extract any information from token we will need the secret key
    private Claims getAllClaimsFromToken(String token) {
        return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody();
    }

    //check if the token has expired
    private Boolean isTokenExpired(String token) {
        final Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }

    //generate token for user
    public String generateToken(User user) {
        Map<String, Object> claims = new HashMap<>();
        return doGenerateToken(claims, user.getId());
    }

    //Generate Token
    private String doGenerateToken(Map<String, Object> claims, UUID subject) {

        return Jwts.builder().setClaims(claims).setSubject(subject.toString()).setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY))
                .signWith(SignatureAlgorithm.HS512, jwtSecret).compact();
    }

    //validate token
    public Boolean validateToken(String token, User user) {
        final UUID userId = getUserIdFromToken(token);
        return (userId.equals(user.getId()) && !isTokenExpired(token));
    }
}

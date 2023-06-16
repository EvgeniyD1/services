package com.example.main_service.config;

import com.example.main_service.domain.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

@Component
public class JwtTokenUtil {

    /*token validity time*/
    @Value("${jwt_token_life_time}")
    private Long lifeTime;

    /*generated security key*/
    private final SecretKey key = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    /*token generation method*/
    public String generateToken(Authentication authentication) {
        //UserDetails
        User userPrincipal = (User) authentication.getPrincipal();
        return Jwts.builder()
                .setSubject(userPrincipal.getUsername())
                .setId(userPrincipal.getId().toString())
                .setIssuedAt(new Date())
                .setExpiration(new Date((new Date()).getTime() + lifeTime))
                .signWith(key)
                .compact();
    }

    /*method returning Subject (username) from token*/
    public String getUsernameFromToken(String token) {
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
        return claims.getSubject();
    }

    /*method returning user Id from token*/
    public String getIdFromToken(String token) {
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
        return claims.getId();
    }

    /*validity check*/
    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}

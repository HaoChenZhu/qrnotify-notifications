package com.nebrija.tfg.qrnotify.notifications.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.security.Key;
import java.util.Date;

public class JwtUtil {

    private static final String SECRET = "g97q3IABYfbIbUo/BaTkBEXdgm6QBiMRHFWbmRKS+jY="; // Asegúrate de que el secreto tenga al menos 256 bits

    private static final Key key = Keys.hmacShaKeyFor(SECRET.getBytes());

    public static String parseToken(String token) {
        try {
            Jws<Claims> jws = Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
            return jws.getBody().getSubject();
        } catch (JwtException e) {
            return null;
        }
    }

    public static String generateToken() {
        return Jwts.builder().setSubject("625529850").claim("name", "hao").setIssuedAt(new Date()).setIssuedAt(new Date()).setExpiration(new Date(System.currentTimeMillis() + 24 * 60 * 60 * 1000)) // 24 hora de duración del token
                .signWith(key).compact();
    }

    public static String getCurrentToken(HttpServletRequest request) {
        String bearerToken = null;
        String authorizationHeader = request.getHeader("Authorization");

        if (StringUtils.hasText(authorizationHeader) && authorizationHeader.startsWith("Bearer ")) {
            bearerToken = authorizationHeader.substring(7);
        }

        return bearerToken;
    }

}
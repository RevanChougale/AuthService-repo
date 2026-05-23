package com.AuthService.AuthService.Config;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

public class JwtUtility {
    private static final String SECRET =
            "mysecretkeymysecretkeymysecretkey";

    private static final SecretKey KEY =
            Keys.hmacShaKeyFor(SECRET.getBytes(StandardCharsets.UTF_8));

//    // 🔐 CREATE TOKEN
//    public static String generateToken(String username, String role) {
//        return Jwts.builder()
//                .setSubject(username)
//                .claim("role", role)
//                .signWith(KEY)
//                .compact();
//    }
public static String generateToken(String username, String role) {

    Date now = new Date();

    Date expiryDate = new Date(now.getTime() + 1000 * 60 * 60);

    return Jwts.builder()
            .setSubject(username)
            .claim("role", role)
            .setIssuedAt(now)
            .setExpiration(expiryDate)
            .signWith(KEY)
            .compact();
}
}

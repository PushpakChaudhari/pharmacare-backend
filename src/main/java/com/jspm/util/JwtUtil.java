package com.jspm.util;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;
import java.util.Date;

public class JwtUtil {

	private static final String SECRET_KEY = "f9L4j8Nq2eM7RvWyZ6BpXkUa1CtG0hDq";
 // private static final long EXPIRATION_TIME = 86400000; // 1 day in milliseconds
	private static final long EXPIRATION_TIME = 15*60 * 1000; // 1 minute in milliseconds

    public static String generateToken(String username) {
        SecretKey key = getSigningKey();

        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    private static SecretKey getSigningKey() {
        byte[] keyBytes = SECRET_KEY.getBytes();
        return Keys.hmacShaKeyFor(keyBytes);
    }
}

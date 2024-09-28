package com.micro.gateway.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.security.Key;
import java.util.function.Function;

@Component
public class JwtUtil {


    private static final String SECRET_KEY = "97e74ee81f5206429721abf0cd87b2450299e2ba3be8feca9d85d3c2c18842e7";

    public void validateToken(final String token) {
        Jwts.parser().setSigningKey(getSignKey()).build().parseClaimsJws(token);
    }



    private Key getSignKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public String extractUsername(String token){
        return extractClaim(token, Claims::getSubject);
    }


//    public String extractUserId(String token){
//        return extractClaim(token, Claims::getId);
//    }

    public String extractUserId(String jwt) { //when we will use this method? when we receive the token from the client when client makes a request to the server and through this token we get the username
        var id = extractAllClaims(jwt).get("id");
        return String.valueOf(id);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts
                .parser()
                .verifyWith((SecretKey) getSignKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }


}

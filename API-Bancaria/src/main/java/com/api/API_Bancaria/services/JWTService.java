package com.api.API_Bancaria.services;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;
import java.util.Date;

@Service
public class JWTService {

    @Value("${jwt.secret}")
    private String secretkey;

    public String generateJWTToken(String cpf){
        return Jwts.builder()
                .subject(cpf)
                .issuedAt(new Date())
                .expiration(
                        new Date(
                                System.currentTimeMillis()
                                +1000 * 60 * 60
                        )
                )
                .signWith(getSignKey())
                .compact();
    }

    private SecretKey getSignKey(){
        byte[] encodedKey = Decoders.BASE64.decode(secretkey);

        return Keys.hmacShaKeyFor(encodedKey);
    }

    public String extractUsername(String token){
        return Jwts.parser()
                .verifyWith(getSignKey())
                .build()
                .parseSignedClaims(token)
                .getPayload().getSubject();
    }

    public boolean validateToken(String token, UserDetails userDetails){
        String cpf =  extractUsername(token);

        return cpf.equals(userDetails.getUsername()) && !isTokenExpired(token);
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token)
                .before(new Date());
    }

    private Date extractExpiration(String token) {

        return Jwts.parser()
                .verifyWith(getSignKey())
                .build()
                .parseSignedClaims(token)
                .getPayload().getExpiration();
    }
}

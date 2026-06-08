package org.example.cafemanagementsystem.security;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.function.Function;

@Service
public class JWTService {

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration}")
    private Long expiration;

    public String generateToken(String userName,String role){

        return Jwts.builder()
                .setSubject(userName)
                .claim("role",role)
                .setIssuedAt(new Date())
                .setExpiration(new Date (System.currentTimeMillis()+expiration))
                .signWith(getKey(),
                        SignatureAlgorithm.HS256)
                .compact();
    }

    private Key getKey() {
        byte[] keyBytes =
               secret.getBytes();
        return Keys.hmacShaKeyFor(keyBytes);

    }

    public String extractUserName(String token){
        return Jwts.parserBuilder()
                .setSigningKey(getKey())
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();

    }
    public String extractRole(String token){
        return Jwts.parserBuilder()
                .setSigningKey(getKey())
                .build()
                .parseClaimsJws(token)
                .getBody()
                .get("role",String.class);
    }

    public boolean validateToken(String token, UserDetails userDetails){

        final String userName = extractUserName(token);

        return userName.equals(
                userDetails.getUsername()
        ) &&  !isTokenExpired(token);
    }

    private boolean isTokenExpired(String token) {

        return  extractExpiration(token).before(new Date());
    }
    private Date extractExpiration(
            String token) {

        return extractClaim(
                token,
                Claims::getExpiration
        );
    }
    public <T> T extractClaim(
            String token,
            Function<Claims,T> resolver) {

        Claims claims =
                Jwts.parserBuilder()
                        .setSigningKey(getKey())
                        .build()
                        .parseClaimsJws(token)
                        .getBody();

        return resolver.apply(claims);
    }

    public String extractRoleByAuthority(String role){
        String str = role.toLowerCase();

        if(str.contains("admin")){
            return "ADMIN";
        } else if (str.contains("vendor")) {
            return "VENDOR";
        }
        return "STUDENT";

    }
}

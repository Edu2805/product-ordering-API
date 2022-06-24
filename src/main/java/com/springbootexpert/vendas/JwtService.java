package com.springbootexpert.vendas;

import com.springbootexpert.vendas.user.UserData;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@Service
public class JwtService {

    @Value("${security.jwt.expiration}")
    private String expiration;
    @Value("${security.jwt.signature-key}")
    private String signatureKey;

    public String generateToken(UserData userData){
        long expString = Long.valueOf(expiration);
        LocalDateTime expirationDateTime = LocalDateTime.now()
                .plusMinutes(expString);

        Date date = Date.from(expirationDateTime
                .atZone(ZoneId.systemDefault())
                .toInstant());
        return Jwts
                .builder()
                .setSubject(userData.getLogin())
                .setExpiration(date)
                .signWith(SignatureAlgorithm.HS512, signatureKey)
                .compact();
    }

    private Claims getClaims (String token) throws ExpiredJwtException {
        return Jwts
                .parser()
                .setSigningKey(signatureKey)
                .parseClaimsJws(token)
                .getBody();
    }

    public boolean validateToken (String token){
        try{
            Claims claims = getClaims(token);
            Date expirationDate = claims.getExpiration();
            var localDateTime =
                    expirationDate.toInstant()
                            .atZone(ZoneId.systemDefault()).toLocalDateTime();
            return !LocalDateTime.now().isAfter(localDateTime);
        }catch (Exception e){
            return false;
        }
    }

    public String getUserPassword(String token) throws ExpiredJwtException{
        return (String) getClaims(token).getSubject();
    }
}

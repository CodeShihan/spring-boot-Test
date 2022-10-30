package com.spring.ws.spring.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JwtTest {


    public static void main(String[] args) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("userId",37994);
        map.put("name","帅");
        map.put("companyCode","C00001");
        String token = Jwts.builder().addClaims(map)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 1000))
                .signWith(SignatureAlgorithm.HS256, "token!@shuai")
                .compact();
        System.out.println(token);

        Claims body = Jwts.parser().setSigningKey("token!@shuai")
                .parseClaimsJws(token).getBody();
        Date date = body.getExpiration();
        System.out.println(date);
        Long userId = body.get("userId", Long.class);
        String name = body.get("name", String.class);
        Date notBefore = body.getNotBefore();
        Date issuedAt = body.getIssuedAt();
        Date expiration = body.getExpiration();
        String issuer = body.getIssuer();
        String companyCode = body.get("companyCode", String.class);
        System.out.println(userId);
        System.out.println(name);
        System.out.println(companyCode);
        System.out.println(notBefore);
        System.out.println(issuer);
        System.out.println(issuedAt);
        System.out.println();
    }



}

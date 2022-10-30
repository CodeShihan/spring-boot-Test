package com.spring.ws.spring.jwt;

import com.auth0.jwt.exceptions.AlgorithmMismatchException;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import io.jsonwebtoken.*;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JwtUtil {

    //密匙
    private static final String sign="token!@shuai";


    /**
     * 生成token
     * @param map
     * @return
     */
    public static String createToken(Map<String,Object> map){
        Calendar calendar = Calendar.getInstance();

        calendar.add(Calendar.SECOND,1);
        Date date=new Date();

        return Jwts.builder().addClaims(map)
                .setIssuedAt(date)
                .setExpiration(calendar.getTime())
                .signWith(SignatureAlgorithm.HS384, sign)
                .compact();
    }


    /**
     * 根据过期时间进行判断是否合法
     * @param token
     * @return true 已过期不合法 false 合法
     * @throws Exception
     */
    public static void parseToken(String token) throws Exception {
        try {
            Jwts.parser().setSigningKey(sign).parseClaimsJws(token);
        }catch (SignatureException e){
            throw new Exception("token不合法！");
        }catch (ExpiredJwtException e){
            throw new Exception("token已经过期，请重新登录");
        }catch (AlgorithmMismatchException e){
            throw new Exception("算法不匹配");
        }catch (InvalidClaimException e){
            throw new Exception("失效的payload");
        }

    }

    /**
     * 获取payload中的信息
     * @param token
     * @return
     */
    public static Claims getTokenInfo(String token){
        return Jwts.parser().setSigningKey(sign).parseClaimsJws(token)
                .getBody();
    }


    public static void main(String[] args) throws Exception {
//        HashMap<String, Object> map = new HashMap<>();
//        map.put("name","帅");
//        String token = createToken(map);
//        System.out.println(token);
//        //校验token不合法
//        parseToken(token);
//
//        //获取token信息
//        Claims claims = getTokenInfo(token);
//        String name = claims.get("name", String.class);
//        System.out.println(name);
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.SECOND,1);
        System.out.println(calendar.getTime());
    }
}

package com.spring.ws.spring.jwt.interceptor;

import com.auth0.jwt.exceptions.AlgorithmMismatchException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.spring.ws.spring.jwt.JwtUtil;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.InvalidClaimException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureException;
import lombok.experimental.Tolerate;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class JWTInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader("token");
        Map<String,Object> map=new HashMap<>();
        if (StringUtils.isEmpty(token)){
            map.put("state",501);
            map.put("msg","token不能为空");
        }else {
            try {
                JwtUtil.parseToken(token);
                return true;
            }catch (SignatureException e){
                map.put("state",502);
                map.put("msg","token不合法");
            }catch (ExpiredJwtException e){
                map.put("state",503);
                map.put("msg","token已经过期，请重新登录");
            }catch (Exception e){
                map.put("state",504);
                map.put("msg",e);
            }
        }
        String json = new ObjectMapper().writeValueAsString(map);
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().println(json);
        return false;
    }
}

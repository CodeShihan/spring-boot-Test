package com.spring.ws.spring.redis.listener;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.spring.ws.spring.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.io.ObjectInputStream;

@Slf4j
//@Component
public class ReceiverRedisMessage {

    /**
     * 字符串序列化为对象
     * @param objectStr
     * @return
     * @throws Exception
     */
    public static Object stringSerializeObject(String objectStr)throws Exception{
        //序列化对象（特别注意：发布的时候需要设置序列化；订阅方也需要设置序列化）
        Jackson2JsonRedisSerializer seria = new Jackson2JsonRedisSerializer(User.class);
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        objectMapper.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        seria.setObjectMapper(objectMapper);

        User user = (User)seria.deserialize(objectStr.getBytes());
        return user;
    }

    public void getMessage01(String message){
        log.info("接受到消息01");
        System.err.println(message);
        log.info("输出完毕02");
    }

    public void getMessage02(String message){
        log.info("接受到消息02");
        try {
            User user = JSON.parseObject(message, User.class);
            System.err.println(user.toString());

//            Object o = stringSerializeObject(message);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.err.println(message);
        log.info("输出完毕02");
    }
}

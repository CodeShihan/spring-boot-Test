package com.spring.ws.spring.redis.listener;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;

//@Configuration
public class MySubcribe implements MessageListener {
    @Override
    public void onMessage(Message message, byte[] pattern) {
        System.out.println(message.getBody().toString());
        System.out.println(message.getChannel().toString());
        System.out.println(pattern.length);
    }

    @Bean
    MessageListenerAdapter messageListener(MessageReceiver messageReceiver){
        return new MessageListenerAdapter(messageReceiver,"getMessage");
    }

    @Bean
    MessageListenerAdapter messageListener02(MessageReceiver02 messageReceiver02){
        return new MessageListenerAdapter(messageReceiver02,"getMessage");
    }

    @Bean
    RedisMessageListenerContainer redisContainer(RedisConnectionFactory factory, MessageListenerAdapter messageListener
    , MessageListenerAdapter messageListener02) {
        final RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        container.setConnectionFactory(factory);
        //订阅多个主题
        container.addMessageListener(messageListener, new PatternTopic("shuai"));
        container.addMessageListener(messageListener02, new PatternTopic("da"));
        return container;
    }

}

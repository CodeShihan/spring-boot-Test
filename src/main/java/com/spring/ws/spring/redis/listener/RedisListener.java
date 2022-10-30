package com.spring.ws.spring.redis.listener;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;


import java.util.ArrayList;
import java.util.List;

//@Configuration
public class RedisListener {

    /**
     * 创建连接工厂
     * @param connectionFactory
     * @param listenerAdapter
     * @return
     */
    @Bean
    public RedisMessageListenerContainer container(RedisConnectionFactory connectionFactory,
                                                   MessageListenerAdapter listenerAdapter, MessageListenerAdapter listenerAdapterTest2) {
        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        //接受消息的key
        List<PatternTopic> tops=new ArrayList<>();
        tops.add(new PatternTopic("wen"));
        tops.add(new PatternTopic("shuai"));

        container.addMessageListener(listenerAdapter, tops);
        container.addMessageListener(listenerAdapterTest2, new PatternTopic("test"));
        return container;
    }

    @Bean
    MessageListenerAdapter listenerAdapter(ReceiverRedisMessage receiver){
        return new MessageListenerAdapter(receiver,"getMessage01");
    }

    @Bean
    MessageListenerAdapter listenerAdapterTest2(ReceiverRedisMessage receiver){
        return new MessageListenerAdapter(receiver,"getMessage02");
    }

}

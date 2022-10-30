package com.spring.ws.spring.mq.delay;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;

public class Production {
    public static void main(String[] args) throws Exception {

        DefaultMQProducer producer=new DefaultMQProducer("group1");
        producer.setNamesrvAddr("121.5.73.211:9876;106.54.87.93:9876");
        producer.start();
        Message message=new Message("delay","tag",("send delay message success").getBytes());
        //设置延迟时间等级
        //message.setDelayTimeLevel(5);
        SendResult send = producer.send(message);
        System.err.println(send);
        producer.shutdown();


    }

}

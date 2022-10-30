package com.spring.ws.spring.mq.batch;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;

import java.util.ArrayList;
import java.util.List;

public class Production {
    public static void main(String[] args) throws Exception {

        DefaultMQProducer producer=new DefaultMQProducer("group1");
        producer.setNamesrvAddr("121.5.73.211:9876;106.54.87.93:9876");
        producer.start();
        //批量发送消息topic必须是同一个否则报 Failed to initiate the MessageBatch
        List<Message> messageList=new ArrayList<>();
        messageList.add(new Message("batch1","tag",("send batch1 message success").getBytes()));
        messageList.add(new Message("batch1","tag",("send batch2 message success").getBytes()));

        //设置延迟时间等级
        //message.setDelayTimeLevel(5);
        SendResult send = producer.send(messageList);
        System.err.println(send);
        producer.shutdown();


    }

}

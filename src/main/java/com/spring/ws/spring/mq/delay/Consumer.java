package com.spring.ws.spring.mq.delay;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.message.MessageExt;

public class Consumer {
    public static void main(String[] args) throws Exception {

        DefaultMQPushConsumer consumer=new DefaultMQPushConsumer("group1");
        consumer.setNamesrvAddr("121.5.73.211:9876;106.54.87.93:9876");
        consumer.subscribe("delay","*");
        //广播 //默认负载均衡
        //consumer.setMessageModel(MessageModel.BROADCASTING);
        consumer.registerMessageListener((MessageListenerConcurrently) (list, consumeConcurrentlyContext) -> {
            for (MessageExt msg : list) {
                System.err.println(new String(msg.getBody())+"延迟时间"+(System.currentTimeMillis()-msg.getStoreTimestamp()));
            }
            return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
        });

        consumer.start();
        System.out.println("消费者启动成功");


    }

}

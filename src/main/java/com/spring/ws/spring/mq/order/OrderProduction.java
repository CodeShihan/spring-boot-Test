package com.spring.ws.spring.mq.order;

import com.alibaba.fastjson.JSON;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.MessageQueueSelector;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageQueue;

import java.util.List;

public class OrderProduction {


    public static void main(String[] args) throws Exception {
        DefaultMQProducer producer=new DefaultMQProducer("orderTest");
        producer.setNamesrvAddr("121.5.73.211:9876;106.54.87.93:9876");
        producer.start();

        List<Order> orderList = Order.init();
        for (Order order : orderList) {
            byte[] bytes = JSON.toJSONBytes(order);
            Message message=new Message("order","TagA",bytes);
            producer.send(message, new MessageQueueSelector() {
                @Override
                public MessageQueue select(List<MessageQueue> list, Message message, Object o) {
                    Long id = (Long) o;
                    Integer index= Math.toIntExact(id % orderList.size());
                    return list.get(index);
                }
            },order.getId());
        }

        System.out.println("消息发送完成");

        producer.shutdown();


    }

}

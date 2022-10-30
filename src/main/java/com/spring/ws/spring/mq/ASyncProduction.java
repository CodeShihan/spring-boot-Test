package com.spring.ws.spring.mq;

import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.exception.RemotingException;

import java.util.concurrent.TimeUnit;

public class ASyncProduction {
    /**
     * 发送异步消息
     * @param args
     * @throws MQClientException
     * @throws RemotingException
     * @throws InterruptedException
     * @throws MQBrokerException
     */
    public static void main(String[] args) throws MQClientException, RemotingException, InterruptedException, MQBrokerException {
        DefaultMQProducer producer=new DefaultMQProducer("group1");
        producer.setNamesrvAddr("121.5.73.211:9876;106.54.87.93:9876");
        producer.start();
        Message message=new Message("sync","tag",("send asnyc message success").getBytes());
         producer.send(message, new SendCallback() {
            @Override
            public void onSuccess(SendResult sendResult) {
                System.err.println("发送消息成功"+sendResult);
            }

            @Override
            public void onException(Throwable throwable) {
                System.err.println("发送消息失败"+throwable);

            }
        });
         Thread.sleep(1000);
        producer.shutdown();

    }

}

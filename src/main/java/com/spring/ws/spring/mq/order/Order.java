package com.spring.ws.spring.mq.order;

import lombok.Data;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class Order {

    private Long id;

    private String desc;

    private String name;

    private Integer orderId;

    public static List<Order> init(){
        List<Order> orderList=new LinkedList<>();
        Order order=new Order();
        order.setId(1L);
        order.setDesc("创建");
        order.setName("张三");
        order.setOrderId(1);
        orderList.add(order);

        order=new Order();
        order.setId(1L);
        order.setDesc("付款");
        order.setName("张三");
        order.setOrderId(2);
        orderList.add(order);

        order=new Order();
        order.setId(1L);
        order.setDesc("完成");
        order.setName("张三");
        order.setOrderId(3);
        orderList.add(order);

        order=new Order();
        order.setId(2L);
        order.setDesc("创建");
        order.setName("李四");
        order.setOrderId(1);
        orderList.add(order);


        order=new Order();
        order.setId(3L);
        order.setDesc("创建");
        order.setName("王五");
        order.setOrderId(1);
        orderList.add(order);


        order=new Order();
        order.setId(2L);
        order.setDesc("付款");
        order.setName("李四");
        order.setOrderId(2);
        orderList.add(order);


        order=new Order();
        order.setId(3L);
        order.setDesc("完成");
        order.setName("王五");
        order.setOrderId(3);
        orderList.add(order);


        order=new Order();
        order.setId(3L);
        order.setDesc("推送");
        order.setName("王五");
        order.setOrderId(4);
        orderList.add(order);


        order=new Order();
        order.setId(2L);
        order.setDesc("推送");
        order.setName("李四");
        order.setOrderId(4);
        orderList.add(order);


        order=new Order();
        order.setId(2L);
        order.setDesc("完成");
        order.setName("李四");
        order.setOrderId(3);
        orderList.add(order);


        order=new Order();
        order.setId(1L);
        order.setDesc("推送");
        order.setName("张三");
        order.setOrderId(4);
        orderList.add(order);

        orderList.sort(Comparator.comparing(Order::getId).thenComparing(Order::getOrderId));
        return orderList;

    }

}

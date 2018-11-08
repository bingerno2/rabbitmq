package com.xuecheng.test.rabbitmq.yoyo;

import java.io.IOException;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;

public class CustomerYoYo extends DefaultConsumer {

    public CustomerYoYo(Channel channel) {
        super(channel);
        // TODO Auto-generated constructor stub
    }

    @Override
    public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body)
            throws IOException {
        // 交换机
        String exchange = envelope.getExchange();
        // 消息id，mq在channel中用来标识消息的id，可用于确认消息已接收
        // long deliveryTag = envelope.getDeliveryTag();
        // 消息内容
        String message = new String(body, "utf-8");
        System.out.println("recive exchange:" + exchange + " receive message:" + message);
    }

}

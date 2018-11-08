package com.xuecheng.test.rabbitmq.yoyo;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class Constants {

    /**
     * 队列——邮件
     */
    public static final String QUEUE_INFORM_EMAIL = "queue_inform_email";
    /**
     * 队列——短信
     */
    public static final String QUEUE_INFORM_SMS = "queue_inform_sms";

    /**
     * 通道模式——交换机
     */
    public static final String EXCHANGE_FANOUT_INFORM = "exchange_fanout_inform";

    public static Channel createChannel() {

        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("127.0.0.1");
        factory.setPort(5672);
        factory.setVirtualHost("/");
        factory.setUsername("guest");
        factory.setPassword("guest");
        Connection conn = null;
        Channel channel = null;
        try {
            conn = factory.newConnection();
            channel = conn.createChannel();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return channel;

    }

}

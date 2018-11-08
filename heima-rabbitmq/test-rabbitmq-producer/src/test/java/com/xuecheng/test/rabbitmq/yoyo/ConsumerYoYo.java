package com.xuecheng.test.rabbitmq.yoyo;

import java.io.IOException;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;

public class ConsumerYoYo {

    public static void main(String[] args) {
        new ConsumerYoYo().new ConsumeFonOutEmail().publishConsume(2);
    }

    public class ConsumeFonOutEmail extends ChannelTemplate {

        @Override
        protected void syspublish(Channel channel) throws IOException {

            String listmail = Constants.QUEUE_INFORM_EMAIL;

            channel.exchangeDeclare(Constants.EXCHANGE_FANOUT_INFORM, BuiltinExchangeType.FANOUT);// 申明交换机，并设置成发布订阅模式

            channel.queueDeclare(listmail, true, false, false, null);// 队列——邮件

            // 绑定
            channel.queueBind(listmail, Constants.EXCHANGE_FANOUT_INFORM, "");

            channel.basicConsume(listmail, true, new CustomerYoYo(channel));
        }

    }

}

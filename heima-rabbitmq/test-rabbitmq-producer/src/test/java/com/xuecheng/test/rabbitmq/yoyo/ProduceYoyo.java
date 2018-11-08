package com.xuecheng.test.rabbitmq.yoyo;

import java.io.IOException;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;

public class ProduceYoyo {

    public static void main(String[] args) throws Exception {

        new ProduceYoyo().new FanoutPublish().publishConsume(1);// 订阅发布模式

    }

    public class FanoutPublish extends ChannelTemplate {

        @Override
        protected void syspublish(Channel channel) throws IOException {

            channel.exchangeDeclare(Constants.EXCHANGE_FANOUT_INFORM, BuiltinExchangeType.FANOUT);// 申明交换机，并设置成发布订阅模式

            channel.queueDeclare(Constants.QUEUE_INFORM_EMAIL, true, false, false, null);// 队列——邮件
            channel.queueDeclare(Constants.QUEUE_INFORM_SMS, true, false, false, null);// 队列——短息

            // 绑定
            channel.queueBind(Constants.QUEUE_INFORM_EMAIL, Constants.EXCHANGE_FANOUT_INFORM, "");
            channel.queueBind(Constants.QUEUE_INFORM_SMS, Constants.EXCHANGE_FANOUT_INFORM, "");

            int i = 5;
            while (i-- > 0) {
                String info = "send for overtime tip:" + i;
                channel.basicPublish(Constants.EXCHANGE_FANOUT_INFORM, "", null, info.getBytes());
                System.out.println("send to mq " + info);
            }

        }

    }

}

package com.example.dubbo.springbootdubbo.mq;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Sender {
    private static final String QUEUE_NAME="hello";
    private static final String EXCHANGE_NAME="pub-sub-queue";
    public static void main(String[] args) throws Exception{
        sendMulti();
    }

    public static void sendMulti() throws Exception{
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("192.168.0.3");
        factory.setUsername("admin");
        factory.setPassword("123");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();
        channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.FANOUT);
        for(int i=0;i<100;i++){
            String message = "hello world "+i;
            channel.basicPublish(EXCHANGE_NAME,"",null,message.getBytes("UTF-8"));
            log.info("sendMessage="+message);
        }
        //channel.close();
        //connection.close();
    }

    public static void sendSingle() throws Exception{
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("192.168.0.3");
        factory.setUsername("admin");
        factory.setPassword("123");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();
        channel.queueDeclare(QUEUE_NAME,false,false,false,null);
        for(int i=0;i<100;i++){
            String message = "hello world "+i;
            channel.basicPublish("",QUEUE_NAME,null,message.getBytes("UTF-8"));
            log.info("sendMessage="+message);
        }
        channel.close();
        connection.close();
    }
}

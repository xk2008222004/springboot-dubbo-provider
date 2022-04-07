package com.example.dubbo.springbootdubbo.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.example.springbootdubbo.service.RabbitMqService;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageDeliveryMode;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Map;

@Service(interfaceClass = RabbitMqService.class,version = "1.0.0",group = "delayTemplate" ,timeout = 15000)
@Component
public class DelayRabbitMqServiceImpl implements RabbitMqService {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Value("${spring.delay.time}")
    private int time;

    @Override
    public void sendExchangeByRouting(String s, String s1, Map<String, Object> map) {
        //id + 时间戳 全局唯一
        CorrelationData correlationData = new CorrelationData("12345678909"+new Date());
        rabbitTemplate.convertAndSend(s,s1,map,new MessagePostProcessor() {
            @Override
            public Message postProcessMessage(Message message) throws AmqpException {
                //设置消息持久化
                message.getMessageProperties().setDeliveryMode(MessageDeliveryMode.PERSISTENT);
                //message.getMessageProperties().setHeader("x-delay", "6000");
                message.getMessageProperties().setDelay(Integer.valueOf(time));
                return message;
            }
        }, correlationData);
    }
}

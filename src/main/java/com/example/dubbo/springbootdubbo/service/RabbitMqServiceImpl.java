package com.example.dubbo.springbootdubbo.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.example.springbootdubbo.service.RabbitMqService;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

@Service(interfaceClass = RabbitMqService.class,version = "1.0.0",timeout = 15000,group = "fixTemplate")
@Component
public class RabbitMqServiceImpl implements RabbitMqService {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Override
    public void sendExchangeByRouting(String s, String s1, Map<String, Object> map) {
        rabbitTemplate.convertAndSend(s,s1,map);
    }
}

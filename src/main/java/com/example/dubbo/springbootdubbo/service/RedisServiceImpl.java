package com.example.dubbo.springbootdubbo.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.example.springbootdubbo.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Service(interfaceClass = RedisService.class,version = "1.0.0",timeout = 15000)
@Component
public class RedisServiceImpl implements RedisService {

    @Autowired
    private RedisTemplate<Object,Object> redisTemplate;


    @Override
    public void set(String s,String value) {
        redisTemplate.opsForValue().set(s,value);;
        redisTemplate.expire(s,24, TimeUnit.HOURS);
    }

    @Override
    public Object get(String s) {
        return redisTemplate.opsForValue().get(s);
    }

    @Override
    public void remove(String s) {
        redisTemplate.delete(s);
    }

    @Override
    public void set(String s, String s2,long l) {
        redisTemplate.opsForValue().set(s,s2,l);
        redisTemplate.expire(s,l, TimeUnit.MILLISECONDS);
    }
}

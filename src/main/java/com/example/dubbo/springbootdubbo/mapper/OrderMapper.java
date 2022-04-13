package com.example.dubbo.springbootdubbo.mapper;

import com.example.springbootdubbo.po.UserOrder;
import org.springframework.stereotype.Repository;


@Repository
public interface OrderMapper {

    int insertModel(UserOrder userOrder);

    int deleteModel(UserOrder userOrder);
}

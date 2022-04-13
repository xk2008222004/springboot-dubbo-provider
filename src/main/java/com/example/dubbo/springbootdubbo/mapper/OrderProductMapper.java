package com.example.dubbo.springbootdubbo.mapper;

import com.example.springbootdubbo.po.OrderProduct;
import com.example.springbootdubbo.po.UserOrder;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderProductMapper {

    int insertModel(OrderProduct orderProduct);


    int deleteModel(OrderProduct orderProduct);


    int deleteByNo(UserOrder userOrder);
}

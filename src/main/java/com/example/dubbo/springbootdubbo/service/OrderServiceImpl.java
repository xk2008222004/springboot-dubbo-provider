package com.example.dubbo.springbootdubbo.service;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.dubbo.config.annotation.Service;
import com.example.dubbo.springbootdubbo.mapper.OrderMapper;
import com.example.dubbo.springbootdubbo.mapper.OrderProductMapper;
import com.example.springbootdubbo.dict.ParamDict;
import com.example.springbootdubbo.po.Message;
import com.example.springbootdubbo.po.OrderProduct;
import com.example.springbootdubbo.po.ResultObject;
import com.example.springbootdubbo.po.UserOrder;
import com.example.springbootdubbo.service.MessageService;
import com.example.springbootdubbo.service.UserOrderService;
import com.example.springbootdubbo.vo.MessageVo;
import com.example.springbootdubbo.vo.OrderProductVo;
import com.example.springbootdubbo.vo.OrderVo;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service(interfaceClass = UserOrderService.class,version = "1.0.0",timeout = 5000)
@Component
public class OrderServiceImpl implements UserOrderService {


    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private OrderProductMapper orderProductMapper;

    @Reference(interfaceClass = MessageService.class,version = "1.0.0",check = false)
    private MessageService messageService;

    @Override
    @Transactional
    public ResultObject simpleCreateOrder(OrderVo orderVo) {
        List<OrderProductVo> list = orderVo.getOrderProductVos();
        String orderNo = orderVo.getOrderNo();
        UserOrder userOrder = new UserOrder();
        userOrder.setCreateTime(new Date());
        userOrder.setOrderNo(orderNo);
        userOrder.setUserName(orderVo.getUserName());
        int result = orderMapper.insertModel(userOrder);
        if(!CollectionUtils.isEmpty(list)){
            list.forEach(orderProductVo -> {
                OrderProduct orderProduct = new OrderProduct();
                orderProduct.setCreateTime(new Date());
                orderProduct.setNum(orderProductVo.getNum());
                orderProduct.setOrderId(userOrder.getId());
                orderProduct.setPrice(orderProductVo.getPrice());
                orderProduct.setProductId(orderProductVo.getProductId());
                orderProduct.setProductName(orderProductVo.getProductName());
                orderProductMapper.insertModel(orderProduct);
            });
        }
        return ResultObject.success(result);
    }

    @Override
    public ResultObject createOrder(OrderVo orderVo) {
        //创建预消息
        String messageId = UUID.randomUUID().toString();
        Message message = new Message();
        message.setMessageId(messageId);
        message.setState(String.valueOf(ParamDict.MessageState.NOTSEND.getCode()));
        try{
            message.setData(new ObjectMapper().writeValueAsString(orderVo));
        }catch (JsonProcessingException e){
            e.printStackTrace();
        }
        ResultObject resultObject = messageService.createMessage(message);
        boolean flag = false,flag2 = false;
        try{
            this.simpleCreateOrder(orderVo);
            flag = true;
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(flag){

                try{
                    MessageVo messageVo = new MessageVo();
                    messageVo.setMessageId(messageId);
                    messageVo.setUpdateMessageState(true);
                    messageVo.setStateList(Arrays.asList(ParamDict.MessageState.SENDING.getCode()));
                    messageVo.setData(new ObjectMapper().writeValueAsString(orderVo));
                    messageService.sendMessage(messageVo);
                    flag2 = true;
                }catch (Exception e2){
                    e2.printStackTrace();
                }finally {
                    if(!flag2){
                        Message message1 = new Message();
                        message1.setMessageId(messageId);
                        message1.setState(String.valueOf(ParamDict.MessageState.SENDFAIL.getCode()));
                        messageService.updateMessage(message1);
                    }
                }

            }else{
                messageService.delMessage(message);
            }
        }
        return ResultObject.success(flag&&flag2?"true":"false");
    }

    @Override
    @Transactional
    public ResultObject delOrder(OrderVo orderVo) {
        UserOrder userOrder = new UserOrder();
        userOrder.setOrderNo(orderVo.getOrderNo());
        int result2 = orderProductMapper.deleteByNo(userOrder);
        int result = orderMapper.deleteModel(userOrder);
        return ResultObject.success((result!=0&&result2!=0)?"true":"false");
    }

}

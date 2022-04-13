package com.example.dubbo.springbootdubbo;

import com.alibaba.dubbo.config.annotation.Reference;
import com.example.springbootdubbo.service.UserOrderService;
import com.example.springbootdubbo.vo.OrderProductVo;
import com.example.springbootdubbo.vo.OrderVo;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest(classes = SpringbootDubboApplicationTests.class)
@RunWith(SpringRunner.class)
public class SpringbootDubboApplicationTests {

    @Reference(interfaceClass = UserOrderService.class,version = "1.0.0")
    private UserOrderService orderService;


    @Test
    public void testCreateOrder(){
        OrderVo orderVo = new OrderVo();
        orderVo.setOrderNo("2222222222222222");
        orderVo.setUserName("李四");
        List<OrderProductVo> list = new ArrayList();

        OrderProductVo orderProductVo = new OrderProductVo();
        orderProductVo.setNum(1);//122
        orderProductVo.setPrice(BigDecimal.valueOf(32.65));
        orderProductVo.setProductId(1);

        OrderProductVo orderProductVo1 = new OrderProductVo();
        orderProductVo1.setNum(2);//98
        orderProductVo1.setPrice(BigDecimal.valueOf(21.54));
        orderProductVo1.setProductId(2);

        OrderProductVo orderProductVo2 = new OrderProductVo();
        orderProductVo2.setNum(3);//53
        orderProductVo2.setPrice(BigDecimal.valueOf(11.36));
        orderProductVo2.setProductId(3);

        list.add(orderProductVo);
        list.add(orderProductVo1);
        list.add(orderProductVo2);

        orderVo.setOrderProductVos(list);

        orderService.createOrder(orderVo);
    }

}

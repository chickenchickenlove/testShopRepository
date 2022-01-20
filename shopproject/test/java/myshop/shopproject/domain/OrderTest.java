package myshop.shopproject.domain;


import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

@SpringBootTest
@Transactional
public class OrderTest {


    @Autowired
    EntityManager em;


    @Test
    void order_총주문_가격확인_1건() throws Exception{

        Order order = new Order();
        OrderItem orderItem = new OrderItem();
        orderItem.setOrderPrice(20000);
        orderItem.setCount(2);

        order.getOrderItems().add(orderItem);

        Assertions.assertThat(order.getTotalPrice()).isEqualTo(40000);

    }

    @Test
    void order_총주문_가격확인_2건() throws Exception{

        Order order = new Order();
        OrderItem orderItem = new OrderItem();
        orderItem.setOrderPrice(20000);
        orderItem.setCount(2);

        OrderItem orderItem2 = new OrderItem();
        orderItem2.setOrderPrice(40000);
        orderItem2.setCount(8);

        order.getOrderItems().add(orderItem);
        order.getOrderItems().add(orderItem2);

        Assertions.assertThat(order.getTotalPrice()).isEqualTo(360000);
    }


}

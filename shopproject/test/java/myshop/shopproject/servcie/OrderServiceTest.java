package myshop.shopproject.servcie;

import myshop.shopproject.domain.DeliveryStatus;
import myshop.shopproject.domain.Member;
import myshop.shopproject.domain.Order;
import myshop.shopproject.domain.item.Book;
import myshop.shopproject.domain.item.Item;
import myshop.shopproject.service.OrderService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

@SpringBootTest
@Transactional
public class OrderServiceTest {

    @Autowired
    EntityManager em;

    @Autowired
    OrderService orderService;


    @Test
    @Rollback(value = false)
    void 주문_생성() throws Exception{
        // DB 조회용 테스트 코드

        Member member = Member.createMember("memberA", "a", "a", "b", "abc","abcd","123123");

        Item item = new Book();
        item.setStockQuantity(100);
        item.setName("itemA");
        item.setPrice(20000);

        em.persist(item);
        em.persist(member);


        orderService.saveOrder(member.getId(), item.getId(), 2);
    }


    @Test
    void 주문_생성_실패() throws Exception{
        //given
        Member member = Member.createMember("memberA", "a", "a", "b", "abc","abcd","123123");

        Item item = new Book();
        item.setStockQuantity(100);
        item.setName("itemA");
        item.setPrice(20000);


        em.persist(item);
        em.persist(member);

        try {
            //when
            orderService.saveOrder(member.getId(), item.getId(), 102);
        } catch (Exception e) {
            //then
            System.out.println("Error Message Here = " + e.getMessage());
            Assertions.assertThat(e).isInstanceOf(IllegalStateException.class);
            Assertions.assertThat(e.getMessage()).isEqualTo("재고가 부족합니다.");
        }
    }


    @Test
    void 주문_취소_성공() throws Exception{
        //given
        Member member = Member.createMember("memberA", "a", "a", "b", "abc","abcd","123123");

        Item item = new Book();
        item.setStockQuantity(100);
        item.setName("itemA");
        item.setPrice(20000);


        em.persist(item);
        em.persist(member);

        Long savedOrderId = orderService.saveOrder(member.getId(), item.getId(), 2);
        em.flush();
        em.clear();

        //when
        System.out.println("=================================UpdateQuery 확인==========================");
        Order findOrder = orderService.findOrderById(savedOrderId);
        orderService.orderCancel(findOrder);

        em.flush();
        em.clear();
        //then

        Order cancelOrder = orderService.findOrderById(savedOrderId);
        Assertions.assertThat(cancelOrder.getOrderItems().get(0).getItem().getStockQuantity()).isEqualTo(100);

    }

    @Test
    void 주문_취소_실패() throws Exception{
        //given
        Member member = Member.createMember("memberA", "a", "a", "b", "abc","abcd","123123");

        Item item = new Book();
        item.setStockQuantity(100);
        item.setName("itemA");
        item.setPrice(20000);

        em.persist(item);
        em.persist(member);

        Long savedOrderId = orderService.saveOrder(member.getId(), item.getId(), 2);

        //when
        Order findOrder = orderService.findOrderById(savedOrderId);
        findOrder.getDelivery().setStatus(DeliveryStatus.COMPLETED);

        //then
        try {
            orderService.orderCancel(findOrder);
        } catch (Exception e) {
            Assertions.assertThat(e.getMessage()).isEqualTo("이미 배송 완료된 상품은 취소할 수 없습니다.");
        }


    }









}

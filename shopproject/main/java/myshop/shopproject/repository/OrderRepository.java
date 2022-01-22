package myshop.shopproject.repository;


import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import myshop.shopproject.domain.Order;
import myshop.shopproject.domain.OrderStatus;
import myshop.shopproject.domain.QOrder;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

import java.util.List;

import static myshop.shopproject.domain.QOrder.*;

@Repository
@RequiredArgsConstructor
public class OrderRepository {

    //== 의존관계 주입==//
    private final EntityManager em;
    private final JPAQueryFactory queryFactory;



    //== 비즈니스 로직==//


    // 주문 저장
    public Long saveOrder(Order order) {
        em.persist(order);
        return order.getId();
    }

    // 주문 조회
    public Order findOrderByOrderId(Long id) {
        return em.find(Order.class, id);
    }

    // 멤버 이름 + 주문 상태로 조회
    public List<Order> findOrderByOrderSearch(OrderSearch orderSearch) {
        return queryFactory.select(order)
                .from(order)
                .where(getUsername(orderSearch.getUsername()), getOrderStatus(orderSearch.getOrderStatus()))
                .fetch();
    }










    //== JPA용 체크 로직==//

    private BooleanExpression getOrderStatus(OrderStatus orderStatus) {
        return orderStatus == null ? null : order.orderStatus.eq(orderStatus);
    }

    private BooleanExpression getUsername(String name) {
        return name == null ? null : order.member.name.eq(name);
    }









}

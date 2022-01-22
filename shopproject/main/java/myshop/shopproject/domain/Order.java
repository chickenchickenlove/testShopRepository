package myshop.shopproject.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import myshop.shopproject.domain.item.Item;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue
    @Column(name = "order_id")
    private Long id;


    @JoinColumn(name = "member_id")
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Member member;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "order")
    private List<OrderItem> orderItems = new ArrayList<>();

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Delivery delivery;

    private LocalDateTime orderDate;

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;


    //== 연관관계 편의 메서드==//
    public void setMember(Member member) {
        this.member = member;
        member.getOrders().add(this);
    }

    public void setDelivery(Delivery delivery) {
        this.delivery = delivery;
        delivery.setOrder(this);
    }

    public void addOrderItems(OrderItem orderItem) {
        this.orderItems.add(orderItem);
        orderItem.setOrder(this);
    }

    //== 생성 메서드 ==//

    public static Order createdOrder(Member member, Delivery delivery, OrderItem... orderItems) {

        Order order = new Order();
        order.setMember(member);
        order.setDelivery(delivery);
        order.setOrderStatus(OrderStatus.ORDER);
        order.setOrderDate(LocalDateTime.now());

        for (OrderItem orderItem : orderItems) {
            order.addOrderItems(orderItem);
        }

        return order;
    }




    //== 비즈니스 로직==//

    /**
     * 주문 가격 조회
     */

    public int getTotalPrice() {
        return orderItems.stream().mapToInt(value -> value.getTotalPrice()).sum();
    }

    /**
     * 취소
     */

    public void cancel() {
        // 배송이 완료된 건은 취소할 수 없다.
        if (delivery.getStatus() == DeliveryStatus.COMPLETED) {
            throw new IllegalStateException("이미 배송 완료된 상품은 취소할 수 없습니다.");
        }

        // order 상태가 바뀌어야 한다
        setOrderStatus(OrderStatus.CANCEL);

        // order에 포함된 모든 item들의 갯수가 올라가야한다.
        orderItems.forEach(orderItem -> orderItem.cancel());
    }




}

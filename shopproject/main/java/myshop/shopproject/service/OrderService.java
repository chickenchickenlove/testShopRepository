package myshop.shopproject.service;


import lombok.RequiredArgsConstructor;
import myshop.shopproject.domain.Delivery;
import myshop.shopproject.domain.Member;
import myshop.shopproject.domain.Order;
import myshop.shopproject.domain.OrderItem;
import myshop.shopproject.domain.item.Item;
import myshop.shopproject.repository.ItemRepository;
import myshop.shopproject.repository.MemberRepository;
import myshop.shopproject.repository.OrderRepository;
import myshop.shopproject.repository.OrderSearch;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class OrderService {

    //== 의존관계 주입 ==//
    private final OrderRepository orderRepository;
    private final MemberRepository memberRepository;
    private final ItemRepository itemRepository;




    // 주문 생성 및 저장

    @Transactional
    public Long saveOrder(Long memberId, Long itemId, int count) {


        Item item = itemRepository.findItem(itemId);
        Member member = memberRepository.findMember(memberId);


        OrderItem orderItem = OrderItem.createOrderItem(item, item.getPrice(), count);

        Delivery delivery = new Delivery();
        delivery.setAddress(member.getAddress());

        Order order = Order.createdOrder(member, delivery, orderItem);


        return orderRepository.saveOrder(order);
    }


    // 주문 취소
    @Transactional
    public void orderCancel(Order order) {
        // 주문 취소 + 더티 체킹으로 업데이트
        order.cancel();
    }


    // 주문 리스트 조회
    public List<Order> findOrders(OrderSearch orderSearch) {
        return orderRepository.findOrderByOrderSearch(orderSearch);
    }



    // 주문 단건 조회
    public Order findOrderById(Long id) {
        return orderRepository.findOrderByOrderId(id);
    }









}

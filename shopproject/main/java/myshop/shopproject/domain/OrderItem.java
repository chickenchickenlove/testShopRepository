package myshop.shopproject.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import myshop.shopproject.domain.item.Item;

import javax.persistence.*;

import static javax.persistence.FetchType.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class OrderItem {

    @Id
    @GeneratedValue
    @Column(name = "orderitem_id")
    private Long id;

    @ManyToOne(fetch = LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "item_id")
    private Item item;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "order_id")
    private Order order;
    private int orderPrice;
    private int count;



    //== 비즈니스 로직==//

    public int getTotalPrice() {
        return orderPrice * count;
    }

    public void cancel() {
        getItem().addStock(count);
    }


    //== 연관관계 편의 메서드==//


    //== 생성 메서드 ==//

    public static OrderItem createOrderItem(Item item, int price, int count) {

        OrderItem orderItem = new OrderItem();

        orderItem.setItem(item);
        orderItem.setOrderPrice(price);

        // 주문 했으니 갯수가 줄어든다.
        orderItem.setCount(count);
        item.removeStock(count);

        return orderItem;
    }








}

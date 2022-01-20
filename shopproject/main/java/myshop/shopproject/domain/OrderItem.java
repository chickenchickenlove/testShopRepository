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

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "item_id")
    private Item item;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "order_id")
    private Order order;
    private int orderPrice;
    private int count;

    public int getTotalPrice() {
        return orderPrice * count;
    }


    //== 연관관계 편의 메서드==//



    //== 비즈니스 로직==//





}

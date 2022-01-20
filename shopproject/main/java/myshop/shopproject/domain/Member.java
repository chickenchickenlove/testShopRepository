package myshop.shopproject.domain;

import lombok.Getter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
public class Member {

    @Id
    @GeneratedValue
    @Column(name = "member_id")
    private Long id;
    private String name;

    @Embedded
    private Address address;


    @OneToMany(mappedBy = "member")
    private List<Order> orders = new ArrayList<>();


    /**
     * 회원 엔티티가 해야하는 비즈니스 로직이 무엇이 있을까?
     */

    //== 비즈니스 로직==//





    //== 연관관계 편의 메서드==//



    //== 생성 메서드==// + 연관관계 편의 메서드와 비슷한데, 생성자 로직이라고 보면 될 것 같다.


}

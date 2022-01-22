package myshop.shopproject.domain;

import lombok.Getter;
import org.springframework.util.StringUtils;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Entity
@Getter

public class Member {

    @Id
    @GeneratedValue
    @Column(name = "member_id")
    private Long id;
    private String name;
    private String cellPhone;
    private String userId;
    private String password;

    //== 엔티티 영역 ==//
    @Embedded
    private Address address;

    @OneToMany(mappedBy = "member")
    private List<Order> orders = new ArrayList<>();


    /**
     * 회원 엔티티가 해야하는 비즈니스 로직이 무엇이 있을까?
     */

    //== 비즈니스 로직==//


    public static String transCellPhone(String cellPhone) {
        StringBuilder builder = new StringBuilder();

        for (int index = 0; index < cellPhone.length(); index++) {
            char nowString = cellPhone.charAt(index);
            if (nowString != '-') {
                builder.append(nowString);
            }
        }

        return builder.toString();
    }



    //== 연관관계 편의 메서드==//



    //== 생성 메서드==// + 연관관계 편의 메서드와 비슷한데, 생성자 로직이라고 보면 될 것 같다.

    // 멤버 생성 메서드
    public static Member createMember(String name, String city, String street, String zipCode) {

        Member member = new Member();


        Address address = new Address();
        address.setCity(city);
        address.setStreet(street);
        address.setZipcode(zipCode);


        // 기본값 설정
        member.name = name;
        member.address = address;

        return member;
    }


    public static Member createMember(String name, String city, String street, String zipCode, String userId, String password, String cellPhone) {

        Member member = new Member();


        Address address = new Address();
        address.setCity(city);
        address.setStreet(street);
        address.setZipcode(zipCode);


        // 기본값 설정
        member.name = name;
        member.address = address;
        member.userId = userId;
        member.password = password;
        member.cellPhone = cellPhone;

        return member;
    }




}

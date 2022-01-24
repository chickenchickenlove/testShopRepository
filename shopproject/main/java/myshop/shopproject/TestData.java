package myshop.shopproject;

import lombok.RequiredArgsConstructor;
import myshop.shopproject.domain.Member;
import myshop.shopproject.domain.item.Item;
import myshop.shopproject.repository.MemberRepository;
import myshop.shopproject.service.ItemService;
import myshop.shopproject.service.MemberService;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@RequiredArgsConstructor
public class TestData {


    private final MemberService memberService;
    private final ItemService itemService;

    // 테스트 데이터 주입
    @PostConstruct
    private void init() {

        Member member1 = Member.createMember("김영한", "대구", "동구", "신암", "member1", "1234", "01012345678");
        Member member2 = Member.createMember("김철수", "수원", "강남", "논현", "member2", "234", "0101332128");

        memberService.save(member1);
        memberService.save(member2);

        Item item = Item.createItem("abc", 123, 123, "abc", "sdf");
        itemService.saveItem(item);


    }


}

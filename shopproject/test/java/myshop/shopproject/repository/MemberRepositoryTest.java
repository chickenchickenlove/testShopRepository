package myshop.shopproject.repository;


import myshop.shopproject.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Transactional

public class MemberRepositoryTest {


    @Autowired
    MemberRepository memberRepository;

    @Autowired
    EntityManager em;


    @BeforeEach
    void init() {
        Member member1 = Member.createMember("member2", "대구2", "광역시2", "집2");
        Member member2 = Member.createMember("member3", "대구3", "광역시3", "집3");
        Member member3 = Member.createMember("member4", "대구4", "광역시4", "집4");
        Member member4 = Member.createMember("member5", "대구5", "광역시5", "집5");
        Member member5 = Member.createMember("member6", "대구6", "광역시6", "집6");

        em.persist(member1);
        em.persist(member2);
        em.persist(member3);
        em.persist(member4);
        em.persist(member5);

    }


    @Test
    void 멤버_저장() throws Exception{
        //given

        Member member = Member.createMember("member10", "대구","광역시","집");

        //when
        Long saveMemberId = memberRepository.save(member);
        Member findMember = em.find(Member.class, saveMemberId);

        //then
        assertThat(findMember).isEqualTo(member);
    }


    @Test
    void 중복회원_가입실패() throws Exception{
        //given
        Member member = Member.createMember("member2", "대구","광역시","집");

        try {
            //when
            memberRepository.save(member);
        }catch(Exception e){
            //then
            System.out.println("e.getMessage() = " + e.getMessage());
            assertThat(e.getMessage()).isEqualTo("중복 회원입니다.");
        }
    }


    @Test
    void 멤버_전체_조회() throws Exception{
        //when
        List<Member> members = memberRepository.findMembers();

        //then
        assertThat(members.size()).isEqualTo(5);
        assertThat(members).extracting("name").containsExactly("member2", "member3", "member4", "member5", "member6");
    }


    @Test
    void 멤버_이름으로_검색() throws Exception{
        //when
        Member member2 = memberRepository.findMemberByName("member2");

        //then
        assertThat(member2.getName()).isEqualTo("member2");
        assertThat(member2.getAddress().getCity()).isEqualTo("대구2");
    }





}

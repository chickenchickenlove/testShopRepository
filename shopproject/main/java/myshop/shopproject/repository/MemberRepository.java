package myshop.shopproject.repository;

import com.querydsl.core.types.Predicate;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import myshop.shopproject.domain.Member;
import myshop.shopproject.domain.QMember;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import java.util.List;

import static myshop.shopproject.domain.QMember.*;

@Repository
@RequiredArgsConstructor
public class MemberRepository {

    private final EntityManager em;
    private JPAQueryFactory queryFactory;


    @PostConstruct
    public void init() {
        queryFactory = new JPAQueryFactory(em);
    }


    // 멤버 저장
    public Long save(Member member) {

        // 중복 이름 회원 저장 불가능.
        Member memberByName = findMemberByName(member.getName());
        if (memberByName != null) {
            throw new RuntimeException("중복 회원입니다.");
        }

        // 성공 로직
        em.persist(member);
        return member.getId();
    }

    // 멤버 조회
    public Member findMember(Long id) {
        return em.find(Member.class, id);
    }

    // 멤버 전체 조회
    public List<Member> findMembers() {
        return queryFactory
                .selectFrom(member)
                .fetch();
    }

    // 멤버 이름으로 검색
    // 동일 이름은 한 명만 가입 가능

    public Member findMemberByName(String name) {
        return queryFactory.selectFrom(QMember.member).where(QMember.member.name.eq(name)).fetchOne();
    }


}

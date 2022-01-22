package myshop.shopproject.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import myshop.shopproject.domain.Member;
import myshop.shopproject.domain.QMember;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import java.util.List;

import static myshop.shopproject.domain.QMember.*;

@Repository
@RequiredArgsConstructor
public class MemberRepository {

    private final EntityManager em;
    private final JPAQueryFactory queryFactory;


//    @PostConstruct
//    public void init() {
//        queryFactory = new JPAQueryFactory(em);
//    }


    //== 회원 저장 로직 ==//
    public Long save(Member member) {
        // 체크 로직
        isPossibleSave(member);
        // 성공 로직
        em.persist(member);
        return member.getId();
    }



    //== 회원 조회 로직 ==//

    // 회원 단건 조회
    public Member findMember(Long id) {
        return em.find(Member.class, id);
    }

    // 회원 전체 조회
    public List<Member> findMembers() {
        return queryFactory.selectFrom(member).fetch();
    }

    // 회원 전체 조회 : 동일 전화번호
    public List<Member> findMemberByCellPhone(String cellPhone) {
        // 휴대폰 전화번호 변환
        String transCellPhone = Member.transCellPhone(cellPhone);
        // 비어있는지 확인
        return queryFactory.selectFrom(member).where(member.cellPhone.eq(transCellPhone)).fetch();
    }

    // 회원 가입 ID로 조회
    public List<Member> findMemberById(String userId) {
        return queryFactory.selectFrom(member).where(member.userId.eq(userId)).fetch();
    }

    // 회원 이름으로 조회
    public Member findMemberByName(String name) {
        return queryFactory.selectFrom(QMember.member).where(QMember.member.name.eq(name)).fetchOne();
    }



    //== 비즈니스 로직==//

    // 회원 가입 Validation
    protected void isPossibleSave(Member member) {
        // 중복 이름 회원 저장 불가능.
        if (findMemberByName(member.getName()) != null) {
            throw new RuntimeException("중복 이름이 있는 회원입니다.");
        }

        // 중복 ID 회원 가입 불가능
        if (!findMemberById(member.getUserId()).isEmpty()){
            throw new RuntimeException("중복된 ID가 있는 회원입니다.");
        }

        // 중복 전화번호 가입 불가능
        if (!findMemberByCellPhone(member.getCellPhone()).isEmpty()){
            throw new RuntimeException("같은 전화번호가 존재합니다.");
        }
    }


}

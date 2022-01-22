package myshop.shopproject.service;

import lombok.RequiredArgsConstructor;
import myshop.shopproject.domain.Member;
import myshop.shopproject.repository.MemberRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;


    /**
     * 회원 저장
     * 회원 여러 건 조회
     * 회원 이름으로 조회
     * 회원 조회
     */


    @Transactional
    public Long save(Member member) {
        // return PK
        return memberRepository.save(member);
    }


    public Member findMember(Long id) {
        // return : 회원 조회
        return memberRepository.findMember(id);
    }


    public List<Member> findMember() {
        // return : 회원 리스트
        return memberRepository.findMembers();
    }


    public boolean isJoinable(String userId, String cellPhone) {
        List<Member> memberById = memberRepository.findMemberById(userId);
        List<Member> memberByCellPhone = memberRepository.findMemberByCellPhone(cellPhone);

        int result = memberById.size() + memberByCellPhone.size();

        if (result == 0) {
            return true;
        } else {
            return false;
        }





    }


    public List<Member> findMemberAll() {
        return memberRepository.findMembers();

    }
}

package myshop.shopproject.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import myshop.shopproject.controller.form.MemberForm;
import myshop.shopproject.domain.Member;
import myshop.shopproject.service.MemberService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.List;

@Controller
@Slf4j
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;



    @GetMapping("/members/new")
    public String memberForm(Model model) {

        MemberForm memberForm = new MemberForm();
        model.addAttribute("memberForm", memberForm);

        return "members/createMemberForm";
    }

    @PostMapping("/members/new")
    public String memberSave(@Valid @ModelAttribute MemberForm memberForm, BindingResult bindingResult) {

        // 검증 로직
        checkValidation(memberForm, bindingResult);

        // 에러 발생 시, 에러 표기
        if (bindingResult.hasErrors()) {
            return "members/createMemberForm";
        }

        // 성공 로직
        Member member = transMemberFormToMember(memberForm);
        memberService.save(member);

        return "redirect:/";
    }

    @GetMapping("/members")
    public String memberList(Model model) {

        List<Member> members = memberService.findMemberAll();
        model.addAttribute("members", members);

        return "members/memberList";

    }




    //== 내부 사용 메서드==//

    private void checkValidation(MemberForm memberForm, BindingResult bindingResult) {

        // 비밀번호 검증
        if (!memberForm.getPassword().equals(memberForm.getPasswordValid())) {
            bindingResult.rejectValue("passwordValid",null, "비밀번호를 다시 입력해주세요");
        }

        // 동일 전화번호 + ID 있는지
        if (!memberService.isJoinable(memberForm.getUserId(), memberForm.getCellPhone())) {
            bindingResult.rejectValue("userId",null, "회원 ID가 이미 존재할 수 있습니다. ");
            bindingResult.rejectValue("cellPhone",null, "전화번호가 이미 존재할 수 있습니다.");
        }
    }


    // 멤버 생성용 메서드
    private Member transMemberFormToMember(MemberForm memberForm) {
        return  Member.createMember(memberForm.getName(), memberForm.getCity(),
                memberForm.getStreet(), memberForm.getZipcode(),memberForm.getUserId(),
                memberForm.getPassword(), memberForm.getCellPhone());
    }


}

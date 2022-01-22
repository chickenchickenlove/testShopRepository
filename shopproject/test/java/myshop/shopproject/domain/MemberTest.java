package myshop.shopproject.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
public class MemberTest {



    @Test
    void 휴대전화_변환_test() throws Exception{
        //given
        String num = "01012345678";
        Member member = new Member();

        //when
        String saveNumber = member.transCellPhone(num);

        //then
        Assertions.assertThat(saveNumber).isEqualTo("01012345678");
    }


    @Test
    void 휴대전화_변환_test2() throws Exception{
        //given
        String num = "010-1234-5678";
        Member member = new Member();

        //when
        String saveNumber = member.transCellPhone(num);

        //then
        Assertions.assertThat(saveNumber).isEqualTo("01012345678");
    }


    @Test
    void 휴대전화_변환_test3() throws Exception{
        //given
        String num = "0101234-5678";
        Member member = new Member();

        //when
        String saveNumber = member.transCellPhone(num);

        //then
        Assertions.assertThat(saveNumber).isEqualTo("01012345678");
    }


}

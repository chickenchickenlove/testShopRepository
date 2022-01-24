package myshop.shopproject.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Slf4j
@Controller
public class HomeController {

    @RequestMapping("/")
    public String home() {
        return "home";
    }


    //TODO
    //로그인 접근 시, 페이지 다르게 뿌려주기.

}

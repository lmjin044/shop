package com.shop.Control;

import com.shop.Dto.MemberLogin;
import com.shop.Service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor    //final에 필요한 생성자를 알아서 생성해줌.
@RequestMapping("/member")

public class MemberController {
    private final MemberService memberService;

    @GetMapping("/signIn")
    public String loginPage(Model model){
        model.addAttribute("memberLogin", new MemberLogin());
        return "member/login";
    }
}

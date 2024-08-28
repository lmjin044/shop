package com.shop.Control;

import com.shop.Dto.MemberForm;
import com.shop.Dto.MemberLogin;
import com.shop.Service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor    //final에 필요한 생성자를 알아서 생성해줌.
@RequestMapping("/member")

public class MemberController {
    private final MemberService memberService;
    private final PasswordEncoder passwordEncoder;

    //로그인 페이지 요청
    @GetMapping("/signIn")
    public String loginPage(Model model){

        return "member/login";
    }

    //회원가입 페이지 요청
    @GetMapping("/signUp")
    public String joinPage(Model model){
        model.addAttribute("memberForm", new MemberForm());
        return "/member/join";
    }

    //회원가입 요청
    @PostMapping("/signUp")
    public String join(@Valid MemberForm memberForm, BindingResult bindingResult, Model model){
        if(bindingResult.hasErrors()){  //=유효하지 않은 값이 있다면?
            return "member/join";       //=다시 회원가입 페이지로 보내라
        }
        try {
            memberService.saveMember(memberForm, passwordEncoder);
        }catch(IllegalStateException e1){
            bindingResult.rejectValue("userId","error.MemberForm", e1.getMessage());
            return "member/join";
        }catch(IllegalArgumentException e2) {
            bindingResult.rejectValue("email", "error.memberForm", e2.getMessage());
            return "member/join";
        }

        return "redirect:/member/signIn";   //이동시킬 로그인 페이지의 '주소'를 쓰는거다.
    }

    //로그인 실패시
    @GetMapping("/signIn/error")
    public String loginFail(Model model){
        model.addAttribute("loginFailMsg","아이디 또는 비밀번호가 올바르지 않습니다");

        return "member/login";
    }


}

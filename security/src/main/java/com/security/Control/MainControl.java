package com.security.Control;

import com.security.Dto.MemberDto;
import com.security.Service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class MainControl {
    @Autowired
    private MemberService memberService;

    @GetMapping("/")
    public String home(){
        return "index";
    }

    @GetMapping("/signUp")
    public String 회원가입(Model model){
        model.addAttribute("memberDto", new MemberDto());
        return "signUp";

    }

    @PostMapping("/signUp")
    public String 회원가입요청(@Valid MemberDto memberDto, BindingResult bindingResult, Model model){
        if(bindingResult.hasErrors()){//유효하지 않은 값이 입력된 경우
            return "signUp";
        }

        memberService.회원가입처리(memberDto);
        return "redirect:/";
    }

    @GetMapping("/signIn")
    public String 로그인페이지요청(){
        return "login";
    }

    @PostMapping("")
    public String 로그인처리(){
        return "redirect:/";
    }
}

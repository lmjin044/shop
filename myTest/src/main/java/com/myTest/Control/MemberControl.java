package com.myTest.Control;

import com.myTest.Dto.MemberDto;
import com.myTest.Service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
public class MemberControl {

    @Autowired
    private MemberService memberService;
    private final PasswordEncoder passwordEncoder;

    @GetMapping("/")
    public String home(){
        return "index";
    }

    @GetMapping("/signUp")
    public String signUpPage(Model model){
        model.addAttribute("memberDto", new MemberDto());
        return "signUp";
    }

    @PostMapping("/signUp")
    public String requestSignUp(@Valid MemberDto memberDto, BindingResult bindingResult, Model model){
        if(bindingResult.hasErrors()){
            return "signUp";
        }
        memberService.signUp(memberDto, passwordEncoder);
        return "redirect:/";
    }

    @GetMapping("/signIn")
    public String requiredLogin(){
        return "login";
    }

    @GetMapping("/adminHome")
    public String adminHome(){
        return "admin";
    }



}

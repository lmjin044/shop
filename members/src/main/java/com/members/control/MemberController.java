package com.members.control;

import com.members.Dto.MemberDto;
import com.members.Dto.MemberLoginDto;
import com.members.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;

@Controller
public class MemberController {

    @Autowired
    private MemberService memberService;

    //회원가입 페이지 요청
    @GetMapping("/signUp")
    public String 회원가입페이지(Model model){
        model.addAttribute("memberDto" , new MemberDto());
        return "member/signUp";
    }

    //회원가입 요청
    @PostMapping("/signUp")
    public String 회원가입( MemberDto memberDto){

        memberService.회원가입저장(memberDto);

        return "redirect:/";
    }

    //로그인 페이지 요청
    @GetMapping("/signIn")
    public String 로그인페이지(Model model){
        model.addAttribute("memberLoginDto", new MemberLoginDto());
        return "member/signIn";
    }

    // 로그인 요청
    @PostMapping("/signIn")
    public String 로그인(MemberLoginDto memberLoginDto ,
                      HttpSession session){

        memberService.로그인처리(memberLoginDto);

        session.setAttribute("user", memberLoginDto.getUserId() );

        return "redirect:/";
    }

    //회원정보수정 페이지 요청
    @GetMapping("/modify")
    public String 회원수정(Model model, HttpSession session){
        String userId = (String)session.getAttribute("user");
        MemberDto memberDto = memberService.회원정보얻기(userId);
        model.addAttribute("memberDto",memberDto );

        return "member/signUp";
    }
}
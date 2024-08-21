package com.members.control;

import com.members.Dto.MemberDto;
import com.members.Dto.MemberLoginDto;
import com.members.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@Controller
public class MemberController {

    @Autowired
    private MemberService memberService;

    //회원가입 페이지 요청
    @GetMapping("/signUp")
    public String signIn(Model model){
        model.addAttribute("memberDto", new MemberDto());
        return "member/signUp";
    }

    //회원가입 요청
    @PostMapping ("/signUp")
    public String signUp(MemberDto memberDto){

         memberService.signUpSave(memberDto);

        return "redirect:/";
    }

    //로그인 페이지 요청
    @GetMapping("/signIn")
    public String loginPage(Model model){
        model.addAttribute("memberLoginDto", new MemberLoginDto());

        return "member/signIn";
    }

    // 로그인 요청
    @PostMapping("/signIn")
    public String login(MemberLoginDto memberLoginDto, HttpSession session){

        memberService.login(memberLoginDto);
        session.setAttribute("user", memberLoginDto.getUserId());
        return "redirect:/";
    }

    //회원정보수정 페이지 요청
    @GetMapping("/modify")
    public String modify(@RequestParam("id")int id, Model model, HttpSession session){
        String userId = (String)session.getAttribute("user");
        MemberDto memberDto = memberService.getMember(userId);
        model.addAttribute("memberDto", memberDto);

        return "member/signUp";
    }
}



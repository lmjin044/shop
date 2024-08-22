package com.security.control;

import ch.qos.logback.core.net.SyslogOutputStream;
import com.security.Dto.MemberDto;
import com.security.Service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
public class MainControl {

    @Autowired
    private MemberService memberService;

    private final PasswordEncoder passwordEncoder;


    @GetMapping("/")
    public String home(){
        return "index";
    }

    @GetMapping("/signUp")
    public String 회원가입페이지요청(Model model){
        model.addAttribute( "memberDto", new MemberDto());
        return "signUp";
    }

    @PostMapping("/signUp")
    public String 회원가입요청(@Valid MemberDto memberDto,
                         BindingResult bindingResult, Model model){
        if( bindingResult.hasErrors() ){ // 유효하지않은 값인 경우
            return "signUp";
        }

        memberService.회원가입처리(memberDto, passwordEncoder);
        return "redirect:/";  // 회원가입 하면 첫페이지로 보내기

    }

    @GetMapping("/signIn")
    public String 로그인페이지요청(){

        return "login";
    }

//    @PostMapping("/login_chk")
//    public String 로그인요청(@RequestParam("id") String userId,
//                        @RequestParam("pw") String password , HttpSession session){
//
//        System.out.println("로그인 요청   ");
//        boolean isSuccess = memberService.로그인처리( userId, password);
//        if( isSuccess ){
//            session.setAttribute("user", userId );
//        }
//
//        return "redirect:/";
//    }


}
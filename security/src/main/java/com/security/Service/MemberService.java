package com.security.Service;

import com.security.Dto.MemberDto;
import com.security.Entity.Member;
import com.security.Repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.validation.Valid;

@Service
public class MemberService implements UserDetailsService {
    @Autowired
    private MemberRepository memberRepository;
    public void 회원가입처리(@Valid MemberDto memberDto, PasswordEncoder passwordEncoder) {
        //Dto로 받은 정보들을 Entity로 전환하는 것
        Member member = memberDto.createEntity(passwordEncoder);

        memberRepository.save(member);
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //이걸 해야 실제로 로그인 처리할 때 사용됨
        //커스텀 로그인 방식으로 하는 경우 서비스크래스에서 구현
        //loasUserByUserName 메서드는 로그인 아이디만 받음.
        //  = 회원가입 시 아이디 중복저장이 되면 안 됨.
        Member member = memberRepository.findByUserId(username);
        if(member==null){
            throw new UsernameNotFoundException(username);
        }
        return User.builder().username(member.getUserId())
                .password(member.getPassword())
                .roles("USER")  //이게 인가와 관련된 역할임.
                .build();
    }
        //이게 db의 아이디와 패스워드를 비교하여 로그인 처리를 진행함
        //단 이대로 끝내면 at com.security.Service.MemberService.loadUserByUsername(MemberService.java:38) ~[main/:na] 오류 발생
        //MemberService와 SecurityConfig 클래스 간 연동과 복호화를 해야 함


//    public boolean 로그인처리(String userId, String password) {
//        Member member =memberRepository.findByUserIdAndPassword(userId, password);
//        if(member != null)
//            return true;
//
//        return false;
//    }
}

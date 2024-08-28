package com.shop.Service;

import com.shop.Dto.MemberForm;
import com.shop.Entity.Member;
import com.shop.Repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
@RequiredArgsConstructor
public class MemberService implements UserDetailsService {
    private final MemberRepository memberRepository;
    //회원가입 폼의 내용을 DB에 저장하기.

    public void saveMember(MemberForm memberForm, PasswordEncoder passwordEncoder){
        Member member = memberForm.createEntity(passwordEncoder);

        validUserIdEmail(member);
        memberRepository.save(member);
    }

    private void validUserIdEmail(Member member){
        //DB와 입력값의 중복 데이터 비교하기
        Member find=memberRepository.findByUserId(member.getUserId());
        if(find !=null){
            throw new IllegalStateException("이미 가입 된 아이디 입니다.");
        }
        find = memberRepository.findByEmail(member.getEmail());
        if(find !=null){
            throw new IllegalArgumentException("이미 가입 된 이메일 입니다.");
        }
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //로그인 할 때 입력한 아이디와 비밀번호와 DB에 저장된 내용을 비교
        Member member = memberRepository.findByUserId(username);    //1)아이디비교
        if(member == null){
            throw new UsernameNotFoundException(username);
        }
        return User.builder()
                .username(member.getUserId())
                .password(member.getPassword())
                .roles(member.getRole().toString()).build();
    }
}

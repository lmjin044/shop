package com.myTest.Service;

import com.myTest.Dto.MemberDto;
import com.myTest.Entity.Member;
import com.myTest.Repository.MemberRepository;
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
    public void signUp(@Valid MemberDto memberDto, PasswordEncoder passwordEncoder){
        Member member =memberDto.createEntity(passwordEncoder);
        memberRepository.save(member);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Member member = memberRepository.findByUserId(username);
        System.out.println(member.getUserId());
        if(member==null){
            throw new UsernameNotFoundException(username);
        }
        return User.builder().username(member.getUserId())
                .password(member.getPassword())
                .roles(member.getRole().toString())
                .build();
    }
}

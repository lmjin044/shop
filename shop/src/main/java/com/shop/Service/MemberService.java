package com.shop.Service;

import com.shop.Dto.MemberForm;
import com.shop.Entity.Member;
import com.shop.Repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    //회원가입 폼의 내용을 DB에 저장하기.

    public void saveMember(MemberForm memberForm){
        Member member = memberForm.createEntity();

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
}

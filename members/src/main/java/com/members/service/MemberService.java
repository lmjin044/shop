package com.members.service;

import com.members.Dto.MemberDto;
import com.members.Dto.MemberLoginDto;
import com.members.entity.Member;
import com.members.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberService {

    @Autowired
    private MemberRepository memberRepository;

    public void signUpSave(MemberDto memberDto) {
        Member member = memberDto.createEntity();
            //memberDto에서 만든 객체를 여기서 불러오는거.

        memberRepository.save(member);
    }

    public void login(MemberLoginDto memberLoginDto) {
        memberRepository.findByUserIdAndPassword(memberLoginDto.getUserId(), memberLoginDto.getPassword());
        //=select * from member_test where user_id=(아이디명) and password=(비밀번호값)
        //findBy이거 되게 길지만 결국 유저 아이디와 비밀번호를 찾아라 라는 뜻이라서 repository에 등록하면 됨
    }

    public MemberDto getMember(String userId){
        Member member =memberRepository.findByUserId(userId).orElse(null);

        MemberDto memberDto =MemberDto.of(member);
        return memberDto;
    }

}
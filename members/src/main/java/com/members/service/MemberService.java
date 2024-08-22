package com.members.service;

import com.members.Dto.MemberDto;
import com.members.Dto.MemberLoginDto;
import com.members.entity.Member;
import com.members.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MemberService {
    @Autowired
    private MemberRepository memberRepository;

    public void 회원가입저장(MemberDto memberDto) {

        Member member = memberDto.createEntity();

        memberRepository.save(member);
    }

    public void 로그인처리(MemberLoginDto memberLoginDto) {
        // select * from member_test where user_id=xxx and password=xxx
        memberRepository.findByUserIdAndPassword(memberLoginDto.getUserId(), memberLoginDto.getPassword());

    }

    public MemberDto 회원정보얻기(String userId) {

        Member member = memberRepository.findByUserId(userId);
            //findByUserId 메서드는 컨트롤러 클래스에서 userId를 기준으로 조회하도록 지정해서
            //사용자 Id(String) 값을 받아올 수 있도록 임의로 만든 메서드임
            //또한 레포지토리에 동일한 메서드가 있음을 전제로 한 구문이므로 레포지토리에도 지정을 해야함
        MemberDto memberDto = MemberDto.of(member);
            //같은 의미에서 MemberDto.of(member) 구문이 성립하려면 MemberDto에서도 of가 들어가는 get 구문을
            //만들어줘야 한다
        return memberDto;
    }
}
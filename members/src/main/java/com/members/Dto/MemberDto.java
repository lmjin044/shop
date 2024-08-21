package com.members.Dto;

import com.members.entity.Member;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberDto {
    private int id;
    private String userId;
    private String password;
    private String tel;

    //DTO >> Entity로 변환하는 작업
    public Member createEntity(){
        Member member = new Member();
        member.setId(this.id);
        member.setUserId(this.userId);
        member.setPassword(this.password);
        member.setTel(this.tel);

        return member;
    }
}


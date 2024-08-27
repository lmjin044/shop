package com.shop.Dto;

import com.shop.Entity.Member;
import lombok.Getter;
import lombok.Setter;
import org.modelmapper.ModelMapper;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
public class MemberForm {
    private Long id;
    @NotBlank(message = "아이디는 필수 입니다.")
    private String userId;

    @NotBlank(message = "이메일을 작성해주세요")
    private String email;

    @Size(min=4 , max=12, message="비밀번호는 4~12자리 입니다.")
    private String password;

    private String name;
    private String addr1; // 주소
    private String addr2; // 상세주소
    private int zipCode;  // 우편번호

    //DTO > Entity
    public Member createEntity(){
        Member member = new Member();
        member.setName(this.name);
        member.setEmail(this.email);
        member.setUserId(this.userId);
        member.setPassword(this.password);
        member.setZipCode(this.zipCode);
        member.setAddr1(this.addr1);
        member.setAddr2(this.addr2);

        return member;
    }

    //Entity > DTO
    public static MemberForm of(Member member){
        MemberForm memberform = new MemberForm();
        memberform.setName(member.getName());
        memberform.setEmail(member.getEmail());
        memberform.setAddr1(member.getAddr1());
        memberform.setAddr2(member.getAddr2());
        memberform.setZipCode(member.getZipCode());
        memberform.setUserId(member.getUserId());

        return memberform;

    }


}
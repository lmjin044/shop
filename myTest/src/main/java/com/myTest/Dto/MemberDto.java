package com.myTest.Dto;

import com.myTest.Constant.Role;
import com.myTest.Entity.Member;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Getter
@Setter
public class MemberDto {

    @NotBlank(message = "아이디 입력은 필수입니다.")
    private String userId;

    @Size(min=5, max = 15, message = "비밀번호는 5~15자로 구성해주세요.")
    private String password;
    private String email;


    //Dto > Entity
    public Member createEntity(PasswordEncoder passwordEncoder){
        Member member =new Member();
        member.setUserId(this.userId);
        String pw=passwordEncoder.encode(this.password);
        member.setPassword(pw);
        member.setEmail(this.email);
        member.setRole(Role.USER);

        return member;
    }

    //Entity > Dto
    public static MemberDto of(Member member){
        MemberDto memberDto = new MemberDto();
        memberDto.setUserId(member.getUserId());
        member.setPassword(member.getPassword());
        member.setEmail(member.getEmail());

        return memberDto;
    }

}

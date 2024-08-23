package com.security.Dto;

import com.security.Constant.Role;
import com.security.Entity.Member;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
public class MemberDto {
    @NotBlank(message = "아이디는 필수입니다.")
    private String userId;  // 시큐리티 이용자 아이디

    @Size(min = 4, max = 10, message = "비밀번호는 4~10자로 만들어주세요.")
    private String password; // 시큐리티 이용자 비밀번호

    @Min(value = 13, message = "13세 이상만 가입 가능합니다.")
    @Max(value = 80, message = "80세 이하만 가입 가능합니다.")
    private int age; // 시큐리티 이용자 나이

    // Dto > Entity
    public Member createEntity(PasswordEncoder passwordEncoder) {
        Member member = new Member();
        member.setAge(this.age);
        String pw = passwordEncoder.encode(this.password);
        member.setPassword(pw);
        member.setUserId(this.userId);  // id -> userId로 수정해야 함
        member.setRole(Role.USER);
        return member;
    }

    // Entity > Dto
    public static MemberDto of(Member member) {
        MemberDto memberDto = new MemberDto();
        memberDto.setAge(member.getAge());
        memberDto.setPassword(member.getPassword());
        memberDto.setUserId(member.getUserId());

        return memberDto;
    }
}

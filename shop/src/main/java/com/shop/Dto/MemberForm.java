package com.shop.Dto;

import lombok.Getter;
import lombok.Setter;
import org.modelmapper.ModelMapper;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
public class MemberForm {
    private Long id;
    @NotBlank(message = "아이디는 필수입니다.")
    private String userId;

    @NotBlank(message = "이메일을 작성해주세요.")
    private String email;

    @Size(min=4, max=12, message = "비밀번호는 4~12자리입니다.")
    private String password;

    private String name;
    private String addr;    //기본 주소
    private String addr2;   //상세 주소
    private int zipcode;    //우편번호

    private static ModelMapper mapper = new ModelMapper();

}

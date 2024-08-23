package com.security.Entity;

import com.security.Constant.Role;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name="security_member")  //member라는 이름이 이미 쓰이고 있어서 변경 : 그냥 쓰면 클래스 이름이 Member가 됨
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="member_id")
    private long id;

    private String userId;
    private String password;
    private int age;

    @Enumerated(EnumType.STRING)    //enum의 타입을 문자열로 지정한다.
    private Role role;
}

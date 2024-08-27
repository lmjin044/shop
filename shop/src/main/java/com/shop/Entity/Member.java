package com.shop.Entity;

import com.shop.Constant.Role;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name="shop_member")
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="member_id")
    private Long id;

    @Column(unique = true)//중복 불가 유효성 검사
    private String userId;
    private String password;
    private String name;

    @Column(unique = true)
    private String email;

    private String addr1;
    private String addr2;
    private int zipCode;


    @Enumerated(EnumType.STRING)
    private Role role;
    private LocalDateTime regTime;
    private LocalDateTime updateTime;
}

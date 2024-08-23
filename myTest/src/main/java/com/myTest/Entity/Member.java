package com.myTest.Entity;

import com.myTest.Constant.Role;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name="shop_member")
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="member_id")
    private int id;

    private String userId;
    private String password;
    private String email;

    @Enumerated(EnumType.STRING)
    private Role role;
}

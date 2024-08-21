package com.members.entity;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name="member_test")
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="member_id")
    private int id;

    private String userId;
    private String password;
    private String tel;
}
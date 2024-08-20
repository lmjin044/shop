package com.japTest.Entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Setter
@Getter
@Table(name="board_test")
public class Board {
    @Id //이거 하면 primary key 지정 됨.
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name="board_id")
    private int id;
    private String writer;
    private String title;
    private String content;

}

/*
@Column(length=숫자) = varchar(50)
@Column(nullable=false) = null 비허용
@Column(name="nick_name") = 컬럼명을 nick_name으로 지정
@Column(updateable = false) = 해당 컬럼 변경 불가
@Column(insertable = false) = 해당 컬럼에 데이터 삽입 불가
*/
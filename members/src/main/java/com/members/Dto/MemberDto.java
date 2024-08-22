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

    public static MemberDto of(Member member) {
        MemberDto memberDto = new MemberDto();
        memberDto.setId(member.getId());
        memberDto.setTel(member.getTel());
        memberDto.setPassword(member.getPassword());
        memberDto.setUserId(member.getUserId());
        return memberDto;
    }

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

/*
  ※JPA 메서드
      DB에서 =
        1) 데이터 : 컬럼에 저장된 값
        2) 컬럼 = 필드명 : 한 종류의 데이터를 지칭하는 이름
            ex) 컬럼명이 'name' 일 경우 = 김유신, 이순신, 을지문덕 등등
                컬럼명이 'car' 일 경우 = 스포티지, 쏘나타, 쏘렌토 등등
        3) 레코드 : 각 컬럼에 저장된 데이터들
            ex) 이름, 나이, 직업의 컬럼이 있을 경우 = 김유신, 35, 외교관
                이때 레코드 값은 3이 되는 것
        4) 파일 : 여러 개의 레코드가 집합하여 성립되는 것

       추상화되어있는 메서드
       save() : repository.save(Entity 객체)
        - insert : 신규 데이터를 테이블에 저장, Entity 객체에 Id(primary key가 없는 경우 실행
        - update : 테이블 내 특정 데이터를 변경, Entity 객채에 set메서드와 Id가 존재할 경우 실행

       delete() : repository.delete(Entity객체) 말 그대로 삭제임.

       count() : 테이블의 전체 레코드 개수를 알려줌

       findAll() : repository.findAll();
        - 테이블의 전체 레코드를 조회하여 가져옴
        - 반환값은 return : List<Entity 클래스 명> (Optional)~

       findById() : findById(primary key의 타입 속성과 그 컬럼명)
        - 테이블의 primary key 컬럼을 기준으로 조회해 일치하는 레코드를 가져오는 것


  ※Entity 클래스
    public class Member {
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        @Column(name="member_id")
        private int id;

        private String name;
        private int my_age;
    }
    일 경우 : JPA가 자동으로 테이블을 id(primary key), name, my_age를 만들어준다.

 ※커스텀 메서드 : Entity클래스의 변수명이 컬럼으로 사용되어야 함
                변수 첫 글자는 대문자로 표기
                findBy~ 방식으로 뒤에 컬럼명 혹은 연산자를 사용한다.

    테이블 명이 member일 경우
    ex 1) name 칼럼을 기준으로 데이터 조회를 하고자 할 때
        -쿼리문 방식 : select * from member where name='김철수';
        -JPA 메서드 방식 : findByName("김철수");

    ex 2) 나이와 이름을 기준으로 데이터 조회를 하고자 할 경우
        -쿼리문 방식 : select * from member where name='김철수' and  my_age='25';
        -JPA 메서드 방식 : findByNameAndMyAge('김철수', 25);
        *컬럼 순서에 맞춰서 데이터를 넣어줘야 조회가 된다.

    ex 3) 나이 혹은 이름을 기준으로 데이터 조회를 하고자 할 경우
        -쿼리문 방식 : select * from member where my_age='35' and name='이영희';
        -JPA 메서드 방식 : findByMyAgeOrName(35,'이영희')
        *이러면 두 기준 중 하나라도 해당되는 것을 조회함

    ex 4) 두 값 가이에 있는 데이터를 조회하기 (15~30세 사이 조회)
        -쿼리문 방식 : select * from member where my_age between 15 and 30;
        -JPA 메서드 방식: findByMyAgeBetween(15, 30);

    ex 5) 포함된 값을 조회하기 (이름에 '신'이 들어갔을 경우)
        -쿼리문 방식 : select * from member where name like '%신%';
        -JPA 메서드 방식 : findByNameLike("신");

    ex 6) 정렬하여 가져오기(나이 기준으로 내림차순 / 오름차순은 asc, 내림차순은 desc)
        -쿼리문 방식 : select * from member where order by my_age desc;
        -JPA 메서드 방식 : findByOrderByAgeDesc();
                         findByMyAgeOrderByIdAsc(25); = 나이가 25세인 사람들을 id 기준으로 오름차순 정렬.
 */

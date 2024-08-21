package com.members.repository;

import com.members.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

//이건 DAO 역할을 어느 정도 가짐
@Repository
public interface MemberRepository extends JpaRepository<Member, Integer> {
    void findByUserIdAndPassword(String userId, String password);

    Optional<Object> findByUserId(String userId);
}
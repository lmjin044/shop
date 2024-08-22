package com.members.repository;

import com.members.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<Member, Integer> {
    void findByUserIdAndPassword(String userId, String password);

    Member findByUserId(String userId);
}
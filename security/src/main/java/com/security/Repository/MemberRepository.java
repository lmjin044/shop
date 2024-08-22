package com.security.Repository;

import com.security.Entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long>  {

    Member findByUserIdAndPassword(String userId, String password);

    Member findByUserId(String username);
}

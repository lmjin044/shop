package com.japTest.Repository;

import com.japTest.Entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BoardRepository extends JpaRepository<Board, Integer> {
    //엔티티와 프라이머리키로 지정된 타입을 <>안에 지정해줘야 함.
}

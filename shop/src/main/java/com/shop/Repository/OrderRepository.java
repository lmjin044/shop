package com.shop.Repository;

import com.shop.Entity.Order;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    //현재 로그인한 회원의 계정으로 주문내역을 조회하기

    @Query("select od from Order od where od.member.userId = :userId " +
            "order by od.orderDate desc")
        //엔티티 클래스 이름으로 해야 하는거임
    List<Order> findByOrders(@Param("userId")String userId, Pageable pageable);

    //현재 로그인 한 회원의 주문 총 갯수를 가져오기 = 페이징(OrderController 클래스에 있는)을 위해서
    @Query("select COUNT(od) from Order od where od.member.userId = :userId")
    Long countOrder(@Param("userId") String userId);
}

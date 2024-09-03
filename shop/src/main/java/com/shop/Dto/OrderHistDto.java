package com.shop.Dto;

import com.shop.Entity.Member;
import com.shop.Entity.Order;
import com.shop.Constant.OrderStatus;
import lombok.Getter;
import lombok.Setter;
import org.aspectj.weaver.ast.Or;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class OrderHistDto {
    // 주문 내역 화면에 사용할 DTO
    private Long orderId; //주문번호
    private String orderDate; // 주문날짜
    private OrderStatus orderStatus; // 주문상태
    private String addr; // 배송주소
    private String toName;// 받는사람

    private List<OrderItemDto> orderItemDtoList = new ArrayList<>();

    public void addOrderItemDto( OrderItemDto orderItemDto){
        orderItemDtoList.add(orderItemDto);
    }

    public OrderHistDto(Order order, Member member){
        this.orderId = order.getId();
        this.orderDate=order.getOrderDate().format(DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm"));
        this.orderStatus=order.getOrderStatus();
        this.toName=member.getName();
        this.addr = member.getAddr1()+" "+ member.getAddr2();
    }
}
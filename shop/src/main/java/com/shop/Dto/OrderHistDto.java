package com.shop.Dto;

import com.shop.Constant.OrderStatus;
import com.shop.Entity.Order;
import lombok.Getter;
import lombok.Setter;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class OrderHistDto {
    //주문 내역 화면에 사용할 DTO
    private Long orderId;       //주문 번호
    private String orderDate;   //주문 날짜
    private OrderStatus orderStatus;    //주문 상태

    private List<OrderItemDto> orderItemDtoList = new ArrayList<>();

    public void addOrderItemDto(OrderItemDto orderItemDto){
        orderItemDtoList.add(orderItemDto);
    }
    public OrderHistDto(Order order){
        this.orderId=order.getId();
        this.orderDate = order.getOrderDateTime().format(DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm"));
        this.orderStatus=order.getOrderStatus();

    }

}

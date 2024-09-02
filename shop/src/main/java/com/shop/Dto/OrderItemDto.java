package com.shop.Dto;

import com.shop.Entity.OrderItem;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderItemDto {
    //주문 상품 정보를 저장해 주문 내역과 테이블 저장 전에 사용하기
    private String itemName;    //상품명
    private int quantity;       //상품수량
    private int price;          //상품 가격
    private int orderPrice;     //총 구매 가격
    private String imgUrl;      //상품 대표 이미지

    public OrderItemDto(OrderItem orderItem, String imgUrl){
        this.imgUrl=imgUrl;
        this.itemName=orderItem.getItem().getItemName();
        this.price=orderItem.getPrice();
        this.orderPrice=orderItem.getOrderPrice();
        this.quantity=orderItem.getQuantity();

    }

}

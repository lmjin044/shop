package com.shop.Dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderDto {
    //상품 화면에서 구매하기 버튼을 클릭 했을 때, 상품 번호와 수량을 받을 DTO
    private Long itemId;    //상품 번호
    private int quantity;   //상품 수량

}

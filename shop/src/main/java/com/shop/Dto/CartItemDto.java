package com.shop.Dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class CartItemDto {
    @NotNull(message = "잘못된 접근입니다.")
    private Long itemId;  // 상품 번호

    @Min(value=1, message = "최소 1개이상 담아 주세요")
    private int quantity; //상품수량
}
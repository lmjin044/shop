package com.shop.Dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MainSlideImg {
    private Long id;    //상품 번호
    private String imgUrl; //상품 대표 이미지 주소
    private String itemName; //상품명
}

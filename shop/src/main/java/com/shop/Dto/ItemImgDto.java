package com.shop.Dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ItemImgDto {
    private Long id;
    private String imgName;         //이미지 이름(DB에 저장되는)
    private String imgUrl;          //이미지 저장경로
    private String originalName;    //이미지 원본 이름
    private String repImgYn;        //대표 이미지 설정

}

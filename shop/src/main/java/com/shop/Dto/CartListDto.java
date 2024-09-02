package com.shop.Dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CartListDto {
    private Long cartItemId;    //장바구니 상품
    private String itemName;    //장바구니 내용물
    private String imgUrl;      //장바구니속 아이템 이미지 경로
    private int price;          //가격
    private int count;          //상품 수량


    //Dto 객체 생성자 매메서드
    public CartListDto (Long cartItemId, String itemName, String imgUrl, int price, int count){
        this.cartItemId=cartItemId;
        this.itemName=itemName;
        this.imgUrl=imgUrl;
        this.price=price;
        this.count=count;

        //  사자소생 살아있는 벽 생명력 흡수 죽음의 손가락 얼어붙는 구체찌릿한 구체 패밀리어 생성 투시

    }
}

package com.shop.Dto;

import com.shop.Constant.ItemCategory;
import com.shop.Constant.ItemSellStatus;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class ItemForm {
    private long id;

    @NotBlank(message = "상품명을 입력해주세요.")
    private String itemName;

    @NotBlank(message = "가격을 입력해주세요.")
    private Integer price;

    @NotBlank(message = "재고 수량을 입력해주세요.")
    private Integer stock;

    @NotBlank(message = "상품 설명을 입력해주세요.")
    private String itemDetail;

    private ItemCategory itemCategory;
    private ItemSellStatus itemSellStatus;

    private List<ItemImgDto> itemImgDtos = new ArrayList<>();
    private List<Long> itemImgIds = new ArrayList<>();
    private LocalDateTime regTime;      //최초 등록 일자
    private LocalDateTime updateTime;   //수정 일자

}

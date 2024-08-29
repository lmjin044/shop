package com.shop.Dto;

import com.shop.Constant.ItemCategory;
import com.shop.Constant.ItemSellStatus;
import com.shop.Entity.Item;
import lombok.Getter;
import lombok.Setter;
import org.modelmapper.ModelMapper;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class ItemForm {
    private Long id;

    @NotBlank(message = "상품명은 필수 입니다.")
    private String itemName;

    @NotNull(message = "가격은 필수 입력 입니다.")
    private Integer price;

    @NotNull(message="재고는 필수 입력입니다.")
    private Integer stock;

    @NotBlank(message = "상품상세 내용은 필수입니다.")
    private String itemDetail;

    private ItemCategory itemCategory;
    private ItemSellStatus itemSellStatus;
//    private ItemCategory itemCategory;
//    private ItemSellStatus itemSellStatus;

    private List<ItemImgDto> itemImgDtoList = new ArrayList<>();
    private List<Long> itemImgIds = new ArrayList<>();
    private LocalDateTime regTime; //최초 등록 일
    private LocalDateTime updateTime; //수정 일

    private static ModelMapper mapper = new ModelMapper();

    //DTO -> ENtity
    public Item createEntity(){
        return mapper.map(this, Item.class);
    }

    // Entity - > DTO
    public static ItemForm of(Item item){
        return mapper.map(item , ItemForm.class);
    }

}
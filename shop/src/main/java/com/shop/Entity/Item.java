package com.shop.Entity;

import com.shop.Constant.ItemCategory;
import com.shop.Constant.ItemSellStatus;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Type;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Item extends Base{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="item_id")
    private Long id;

    private String itemName;
    private int price;
    private int stock;

    @Type(type = "org.hibernate.type.TextType")
    //컬럼 타입을 지정해주는 애러테이션
    private String itemDetail;

    @Enumerated(EnumType.STRING)
    private ItemCategory itemCategory;

    @Enumerated(EnumType.STRING)
    private ItemSellStatus itemSellStatus;


}

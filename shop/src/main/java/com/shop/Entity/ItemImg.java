package com.shop.Entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class ItemImg extends Base{
    @Id
    @Column(name="item_img_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String imgName;
    private String imgUrl;
    private String originalName;
    private String regImgYn;

    @ManyToOne
    @JoinColumn(name="item_id")
    private Item item;
}

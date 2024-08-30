package com.shop.Entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Min;

@Entity
@Getter
@Setter
public class CartItem extends Base{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="cart_item_id")
    private Long id;

    private int quantity; // 상품 수량

    @ManyToOne
    @JoinColumn(name="item_id")
    private Item item;

    @ManyToOne
    @JoinColumn(name="cart_id")
    private Cart cart;

    //장바구니 추가 시 기존 상품 수량 증가 위한 메서드
    public void addQuantity(int quantity) {
        this.quantity += quantity;
    }

    //장바구니 리스트에서 수량을 변경할 때의 메서드
    public void updateQuantity(int quantity){
        this.quantity = quantity;
    }

    //CartItem 객체 생성 : 로그인한 회원이 새 상품을 장바구니에 추가할 때
    public static CartItem createCartItem(Cart cart, Item item, int quantity){
        CartItem cartItem = new CartItem();
        cartItem.setCart(cart);
        cartItem.setItem(item);
        cartItem.setQuantity(quantity);

        return cartItem;

    }

}
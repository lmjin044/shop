package com.shop.Service;

import com.shop.Dto.CartItemDto;
import com.shop.Entity.Cart;
import com.shop.Entity.CartItem;
import com.shop.Entity.Item;
import com.shop.Entity.Member;
import com.shop.Repository.CartItemRepository;
import com.shop.Repository.CartRepository;
import com.shop.Repository.ItemRepository;
import com.shop.Repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class CartService {
    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;
    private final ItemRepository itemRepository;
    private final MemberRepository memberRepository;

    //장바구니 - CART 버튼 클릭시 - 장바구니 담기
    public Long addCart(CartItemDto cartItemDto, String userId){
        // 장바구니에 담을 상품 조회
        Item item = itemRepository.findById(cartItemDto.getItemId()).get();

        // 현재로그인 한 회원 번호(primary key - join컬럼) 조회
        Member member = memberRepository.findByUserId(userId);

        //카트가 없는 회원은 카트생성, 카트가 있는 회원은 조회만
        Cart cart = cartRepository.findByMemberId(member.getId());
        if(cart == null) { // 카트가 없는 회원
            cart = Cart.createCart(member);
            cartRepository.save( cart); // 카트 테이블에 새 데이터 저장(현재 회원 카트)
        }

        // 장바구니에 상품을 담기
        CartItem cartItem =
                cartItemRepository.findByCartIdAndItemId(cart.getId(), item.getId());
        if(cartItem != null){
            cartItem.addQuantity(cartItemDto.getQuantity());
            return cartItem.getId();
        }else{
            CartItem newCartItem = CartItem.createCartItem(cart, item, cartItemDto.getQuantity());
            cartItemRepository.save(newCartItem);
            return cartItem.getId();
        }


    }
}
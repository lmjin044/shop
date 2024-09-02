package com.shop.Service;

import com.shop.Dto.CartItemDto;
import com.shop.Dto.CartListDto;
import com.shop.Dto.CartOrderDto;
import com.shop.Dto.OrderDto;
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

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class CartService {
    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;
    private final ItemRepository itemRepository;
    private final MemberRepository memberRepository;
    private final OrderService orderService;


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

        // 장바구니에 상품 을 담기
        // 전에 동일한 상품을 장바구니에 담았다면 수량 증가해야 되고, 장바구니에 없다면 신규저장
        CartItem cartItem =
                cartItemRepository.findByCartIdAndItemId(cart.getId(),item.getId());
        if( cartItem != null) { // 기존 상품의 수량 증가
            cartItem.addQuantity( cartItemDto.getQuantity() );
            return cartItem.getId();
        }else{ // 장바구니에 새상품 담을때!!!
            CartItem newCartItem =
                    CartItem.createCartItem(cart, item, cartItemDto.getQuantity());
            cartItemRepository.save(newCartItem);
            return newCartItem.getId();
        }

    }

    // 장바구니  목록 보기
    public List<CartListDto> getCartList(String name) {
        List<CartListDto> cartListDtoList = new ArrayList<>();

        // 현재 로그인한 회원 계정명으로 회원 번호(primary key로지정된 컬럼- member_id)
        // 구해야한다.
        Member member = memberRepository.findByUserId(name);
        // 로그인한 회원의 장바구니 번호 가져오기
        Cart cart = cartRepository.findByMemberId( member.getId() );
        // 장바구니에 상품을 담지 않은 , 장바구니가 없는 회원
        if( cart == null) return cartListDtoList;

        //현재 로그인한 회원의 장바구니 번호에 맞는 장바구니 상품 가져오
        cartListDtoList = cartItemRepository.findList( cart.getId() );

        return cartListDtoList;
    }

    public void updateCartItemQuantity(Long cartItemId, int quantity, String name) {
        CartItem cartItem = cartItemRepository.findById(cartItemId).get();
        cartItem.updateQuantity(quantity);
    }

    //장바구니 목록에서 상품 삭제하기 위한 메서드 = 테이블에서 제거하기
    public void deleteCartItem(Long cartItemId) {
        CartItem cartItem = cartItemRepository.findById(cartItemId).get();
        cartItemRepository.delete(cartItem);
    }

    public Long orderCartItem(List<CartOrderDto> cartOrderDtoList, String name) {
        List<OrderDto> orderDtos = new ArrayList<>();
        for(CartOrderDto cartOrderDto : cartOrderDtoList){
            CartItem cartItem = cartRepository.findById(cartOrderDto.getCartItemId()).get();
        }
        OrderDto orderDto = new OrderDto();
        orderDto.setItemId(cartItem.getItem().getId());
        orderDto.setQuantity(cartItem.getQunatity());
        orederDtos.add(orderDto);

    }


}
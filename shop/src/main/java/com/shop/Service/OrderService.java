package com.shop.Service;

import com.shop.Dto.CartOrderDto;
import com.shop.Dto.OrderDto;
import com.shop.Entity.Item;
import com.shop.Entity.Member;
import com.shop.Entity.OrderItem;
import com.shop.Repository.ItemImgRepository;
import com.shop.Repository.ItemRepository;
import com.shop.Repository.MemberRepository;
import com.shop.Repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.hibernate.cache.spi.support.AbstractReadWriteAccess;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class OrderService {
    private final ItemRepository itemRepository;
    private final MemberRepository memberRepository;
    private final OrderRepository orderRepository;
    private final ItemImgRepository itemImgRepository;

    public Long orders(List<OrderDto> orderDtos, String name){
        Member member = memberRepository.findByUserId(userId);
        List<OrderItem> orderItemList = new ArrayList<>();

        for(OrderDto orderDto : orderDtos){
            Item item = itemRepository.findById(orderDto.getItemId()).orElseThrow(EntityNotFoundException::new);
            OrderItem orderItem = OrderItem.createOrderItem(item, orderDto.getQuantity());
            orderItemList.add(orderItem);

        }

    }
}
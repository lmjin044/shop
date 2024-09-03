package com.shop.Service;

import com.shop.Dto.OrderDto;
import com.shop.Dto.OrderHistDto;
import com.shop.Entity.*;
import com.shop.Repository.*;
import lombok.RequiredArgsConstructor;
import org.aspectj.weaver.ast.Or;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
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

    public Long orders(List<OrderDto> orderDtos, String userId) {
        // 현재 로그인한 회원 Member 객체
        Member member = memberRepository.findByUserId(userId);
        List<OrderItem> orderItemList = new ArrayList<>();

        for(OrderDto orderDto : orderDtos){
            Item item = itemRepository.findById(orderDto.getItemId())
                    .orElseThrow(EntityNotFoundException::new);
            OrderItem orderItem =
                    OrderItem.createOrderItem(item, orderDto.getQuantity());
            orderItemList.add(orderItem);
        }
        Order order = Order.createOrder(member, orderItemList);
        orderRepository.save(order);
        return order.getId();
    }

    public Page<OrderHistDto> getOrderList(String userId, Pageable pageable) {
        //현재 로그인한 회원의 주문내역 가져오기(컨트롤러 메서드에서 지정한 것만큼 5개)
        List<Order> orders = orderRepository.findByOrders(userId, pageable);
        Long total = orderRepository.countOrder(userId);

        List<OrderHistDto> orderHistDtos = new ArrayList<>();
        Member member = memberRepository.findByUserId(userId);
            //현재 로그인 한 회원 정보를 가져오기
        for(Order order : orders){
            OrderHistDto orderHistDto = new OrderHistDto(order, member);
            //주문한 상품 목록의 Entity를 Dto로 바꾸기
            List<OrderItem> orderItemList = order.getOrderItemList();
            //주문한 상품들의 대표 이미지를 가져오기 :
            for(OrderItem orderItem : orderItemList){
                ItemImg itemImg = itemImgRepository.findByItemIdAndRepImgYn(orderItem.getItem().getId(), "Y");
            }
            orderHistDtos.add(orderHistDto);
        }
        return new PageImpl<>(orderHistDtos, pageable, total);
    }

    public Long itemOrder(OrderDto orderDto, String userId) {
        Member member = memberRepository.findByUserId(userId);

        Item item = itemRepository.findById(orderDto.getItemId()).get();
        List<OrderItem> orderItemList = new ArrayList<>();

        OrderItem orderItem = OrderItem.createOrderItem(item, orderDto.getQuantity());
        orderItemList.add(orderItem);

        Order order = Order.createOrder(member, orderItemList);
        orderRepository.save(order);
        return order.getId();
    }

    //주문 취소하기
    public void cancelOrder(Long orderId){
        Order order = orderRepository.findById(orderId).get();

    }

}
package com.shop.Entity;

import com.shop.Constant.OrderStatus;
import com.shop.Dto.OrderHistDto;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name="orders")
public class Order extends Base{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="order_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;
    private LocalDateTime orderDate;

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    //양방향 설정 : 한 쪽을 불러들이면 반대쪽도 불러와짐

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL,
    orphanRemoval = true, fetch = FetchType.LAZY)
    private List<OrderItem> orderItemList = new ArrayList<>();

    public void addOrderItem(OrderItem orderItem){
        orderItemList.add(orderItem);
        orderItem.setOrder(this);
    }

    public static Order createOrder(Member member, List<OrderItem> orderItemList){
        Order order= new Order();
        order.setMember(member);
        for(OrderItem orderItem : orderItemList){
            order.addOrderItem(orderItem);
        }
        order.setOrderStatus(OrderStatus.ORDER);
        order.setOrderDate(LocalDateTime.now());
        return order;
    }

}
    /*

    양방향 설정 상세하게 알아보기 :

    ※ mappedBy="주인 엔티티"
    ※ 하인 엔티티 : private List<하인 엔티티>
    ※ cascade : 주인 엔티티의 저장, 업데이트, 삭제 등이 하인 엔티티에게도 동일 적용하게 됨
    ※ orphanRemoval : 주인엔티티에서 하인 엔티티를 삭제할 경우 실제 DB에도 삭제 됨
    ※ fetch : 양방향 관계에서는 주인과 하인 모두 조회가 완료 되어야 사용 가능
                = 모두 조회될 때까지 지연시켜야 함.
    */
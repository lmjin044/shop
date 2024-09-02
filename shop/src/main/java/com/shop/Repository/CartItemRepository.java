package com.shop.Repository;

import com.shop.Dto.CartListDto;
import com.shop.Entity.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    CartItem findByCartIdAndItemId(Long id, Long id1);

    //지금 한 거 = 각 테이블에 별명을 지음 (Cart Item = ci / ItemImg = im / Item = i)
    //CartItem의 Item 테이블 번호가 연결 되어 ci.item을 통해 CartItem에 있는 Item을 연결
    //ItemImg와 Item을 연결 해야 하ㅡ로 join을 사용해야 함
    //단 현재 repository의 entity가 CartItem이므로 CartItem-Item간 join은 필요없엉

    @Query("select new com.shop.Dto.CartListDto(ci.id, i.itemName, im.imgUrl, i.price, ci.quantity) " +
            "from CartItem ci " +
            "join ci.item i " +
            "join ItemImg im on im.item.id = ci.item.id " +
            "where ci.cart.id = :cartId " +
            "and im.repImgYn = 'Y' " +
            "order by ci.regTime desc")
    List<CartListDto> findList(@Param("cartId") Long id);
}
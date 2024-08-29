package com.shop.Repository;

import com.shop.Entity.ItemImg;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemImgRepository extends JpaRepository<ItemImg, Long> {

    ItemImg findByItemIdAndRepImgYn(Long id, String y);

    List<ItemImg> findByItemIdOrderByIdAsc(Long id);
}
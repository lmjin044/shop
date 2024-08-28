package com.shop.Repository;

import com.shop.Entity.ItemImg;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemImgRepository extends JpaRepository<ItemImg, Long> {
}

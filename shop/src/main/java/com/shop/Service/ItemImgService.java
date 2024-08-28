package com.shop.Service;

import com.shop.Repository.ItemImgRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class ItemImgService {
    private final ItemImgRepository itemImgRepository;

}

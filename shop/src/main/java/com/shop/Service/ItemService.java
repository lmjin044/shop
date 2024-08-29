package com.shop.Service;

import com.shop.Dto.ItemForm;
import com.shop.Entity.Item;
import com.shop.Entity.ItemImg;
import com.shop.Repository.ItemImgRepository;
import com.shop.Repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ItemService {
    private final ItemRepository itemRepository;
    private final ItemImgService itemImgService;
    private final ItemImgRepository itemImgRepository;

    public void saveItem(ItemForm itemForm, List<MultipartFile> multipartFileList) throws Exception {
        Item item = itemForm.createEntity();
        itemRepository.save(item);  // 상품명, 가격, 재고량, 설명을 테이블에 저장

        //이미지 업로드 및 데이터베이스 저장
        for(int i=0; i<multipartFileList.size(); i++){
            ItemImg itemImg = new ItemImg();
            itemImg.setItem(item); // 이미지에 상품 번호(item_id)가 저장되기위해
            if(i==0) //상품의 대표이미지 설정- 첫번째이미지가 대표이미지
                itemImg.setRepImgYn("Y");
            else
                itemImg.setRepImgYn("N");
            //업로드및 데이터베이스 저장하기 위해 itemimgservie클래스의 메서드호출
            itemImgService.saveItemImg( itemImg, multipartFileList.get(i) );
        }
    }
}
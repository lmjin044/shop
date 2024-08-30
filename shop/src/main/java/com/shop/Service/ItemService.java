package com.shop.Service;

import com.shop.Constant.ItemCategory;
import com.shop.Dto.ItemForm;
import com.shop.Dto.ItemImgDto;
import com.shop.Dto.MainSlideImg;
import com.shop.Entity.Item;
import com.shop.Entity.ItemImg;
import com.shop.Repository.ItemImgRepository;
import com.shop.Repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;


import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ItemService {
    private final ItemRepository itemRepository;
    private final ItemImgService itemImgService;
    private final ItemImgRepository itemImgRepository;

    // 랜덤하게 상품 4개 선택 - 상품번호, 상품명, 상품대표이미지
    public List<MainSlideImg> getSlideImg(){
        List<MainSlideImg> mainSlideImgList = new ArrayList<>();
        // 전체 상품 중 랜덤하게 4개 뽑기
        List<Item> itemList = itemRepository.findRandomItem(4);
        // 랜덤 4개 상품의 대표이미지 가져오기
        for( Item item : itemList){
            MainSlideImg mainSlideImg = new MainSlideImg();
            ItemImg itemImg = itemImgRepository.findByItemIdAndRepImgYn(item.getId(), "Y");

            mainSlideImg.setId( item.getId() ); // 상품번호
            mainSlideImg.setItemName( item.getItemName() ); // 상품명
            mainSlideImg.setImgUrl( itemImg.getImgUrl() ); // 상품 이미지
            mainSlideImgList.add( mainSlideImg );
        }

        return mainSlideImgList;
    }


    // 상품 저장
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


    // 관리자 페이지 메인 상품 목록
    public List<ItemForm> getAdminItemPage() {
        List<Item> itemList = itemRepository.findAll();
        List<ItemForm> itemFormList= new ArrayList<>();
        for(Item item : itemList){
            itemFormList.add(ItemForm.of(item));
        }

        return itemFormList;
    }

    // 상품 정보 가져오기
    public ItemForm getItem(Long id) {
        Item item = itemRepository.findById( id ).orElse(null);
        if( item == null){
            return new ItemForm();
        }
        ItemForm itemForm = getItem(item);
        return itemForm;
    }

    // 메인페이지 보여줄 상품 8개 선택하기위한 메서드
    public List<ItemForm> getMainItems() {
        List<ItemForm> itemFormList = new ArrayList<>();

        //최근 등록된상품 8개 가져오기 - query문 만들어서 하는방법과, 메서드로 하는방법
        Pageable pageable = PageRequest.of(0,8);
        List<Item> itemList = itemRepository.findAllByOrderByRegTimeDesc( pageable );

        for( Item item : itemList){
            ItemForm itemForm= getItem(item);
            itemFormList.add(itemForm);
        }
        return itemFormList;
    }
    // 메인페이지와 상품상세 페이지에 사용되는 내용
    private ItemForm getItem(Item item){
        List<ItemImg> itemImgList =
                itemImgRepository.findByItemIdOrderByIdAsc(item.getId());
        List<ItemImgDto> itemImgDtos = new ArrayList<>();
        for( ItemImg itemImg : itemImgList){
            itemImgDtos.add(  ItemImgDto.of(itemImg)); // 이미지 entity -> DTO
        }
        ItemForm itemForm = ItemForm.of(item);
        itemForm.setItemImgDtoList( itemImgDtos ); // 이미지리스트 상품Dto에저장
        return itemForm;
    }


    public List<ItemForm> getItemList(String category) {
        List<ItemForm> itemFormList = new ArrayList<>();
        ItemCategory itemCategory=ItemCategory.valueOf(category);
            //valueOf = enum의 값을 상수로 변환
            //단 일치했을 때만 정상적으로 동작하고 불일치할 경우 예외가 발생
        List<Item> itemList = itemRepository.findByItemCategory(itemCategory);

        for(Item item : itemList){
            ItemForm itemForm = getItem(item);
            itemFormList.add(itemForm);
                //각 상품들의 이미지를 LIST에 담고 itemForm에 저장
                //itemForm에 저장된 객체들은 List<ItemForm>에 저장
        }

        return itemFormList;
    }
}
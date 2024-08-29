package com.shop.Control;

import com.shop.Dto.ItemForm;
import com.shop.Dto.MainSlideImg;
import com.shop.Service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class MainControl {

    private final ItemService itemService;

    @GetMapping("/")
    public String home(Model model){
        //메인화면에 슬라이드이미지에 사용할 상품 이미지들가져오기(4장)
        List<MainSlideImg> mainSlideImgList = itemService.getSlideImg();
        //슬라이드이미지 아래에  상품들 나열(8개) - 최근 등록 순으로
        List<ItemForm> itemFormList = itemService.getMainItems();

        model.addAttribute("slideImg", mainSlideImgList);
        model.addAttribute("itemList" , itemFormList);
        return "index";
    }

    @GetMapping("/admin")
    public String adminHome(Model model){
        List<ItemForm> itemFormList =itemService.getAdminItemPage();
        model.addAttribute("itemFormList",itemFormList);
        return "admin/index";
    }
}
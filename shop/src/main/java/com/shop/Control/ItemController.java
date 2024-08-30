package com.shop.Control;

import com.shop.Dto.ItemForm;
import com.shop.Service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/item")
public class ItemController {
    private final ItemService itemService;

    //요청 들어오는 주소가 /item/OUTER와 같은 경우일 때
    //item은 컨트롤러 클래스가 처리해주고 /OUTER를 받아주는거임
    // = 이렇게만 하면 일일이 상하의 신발 이런거 다 안 해도 됨
    @GetMapping("/{category}")
    public String itemList(@PathVariable("category") String category, Model model){

        List<ItemForm> itemFormList = itemService.getItemList(category);
        model.addAttribute("itemList", itemFormList);
        return "item/list";
    }

    //상품 상세 페이지 요청 : /item/detail/상품번호
    @GetMapping("/detail/{itemId}")
    public String detail(@PathVariable("itemId") long itemId, Model model){
        ItemForm itemForm = itemService.getItem(itemId);
        model.addAttribute("item", itemForm);

        return "item/detail";
    }

}

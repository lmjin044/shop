package com.shop.Control;

import com.shop.Dto.ItemForm;
import com.shop.Service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {

    private final ItemService itemService;

    @GetMapping("/new")
    public String itemPage(Model model){
        model.addAttribute("itemForm",new ItemForm());
        return "admin/itemForm";
    }

    @PostMapping("/new")
    public String itemSave(@Valid ItemForm itemForm,
                           BindingResult bindingResult, Model model,
                           @RequestParam("itemImgFile")List<MultipartFile> multipartFileList){
        if( bindingResult.hasErrors() ){ //유효하지 않은 값이 있다면
            return "admin/itemForm";
        }
        if( multipartFileList.get(0).isEmpty() ){ // 이미지 한장도 선택하지않았다면
            model.addAttribute("errorMsg",
                    "첫번째 상품이미지는 필수 등록입니다.");
            return "admin/itemForm";
        }
        try{
            itemService.saveItem(itemForm, multipartFileList);
        }catch(Exception e){
            model.addAttribute("errorMsg",
                    "상품 등록중 문제가 발생 하였습니다.");
            return "admin/itemForm";
        }
        return "redirect:/admin";
    }

    //상품수정 페이지
    @GetMapping("update/{id}")
    public String updatePage(@PathVariable Long id, Model model){
        model.addAttribute("itemForm", itemService.getItem(id));
        return "admin/itemForm";
    }

}
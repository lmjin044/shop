package com.shop.Control;

import com.shop.Dto.ItemForm;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class adminController {

    @GetMapping("/new")
    public String itemPage(Model model){
        model.addAttribute("itemForm", new ItemForm());

        return "admin/itemForm";
    }
}

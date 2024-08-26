package com.shop.Control;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainControl {

    @GetMapping("/")
    public String home(Model model) {
        return "index";
    }

}

package com.example.hwSeminarEightSite.controller;

import com.example.hwSeminarEightSite.model.Product;
import com.example.hwSeminarEightSite.service.OnlineShopService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@AllArgsConstructor
public class OnlineShopController {

    private OnlineShopService onlineShopService;

    @GetMapping
    public String showMainPage(Model model) {
        model.addAttribute("products", onlineShopService.getAvailableProducts());
        model.addAttribute("balance", onlineShopService.getAccountAmountById(2L));
        return "index.html";
    }

    @GetMapping("/approve/{id}")
    public String approveForm(@PathVariable Long id, Model model) {
        model.addAttribute("product", onlineShopService.getProductById(id));
        return "approve";
    }

    @PostMapping("approve")
    public String approve(Model model,Product product) {
        try {
            model.addAttribute("product" , onlineShopService.buyProduct(product));
            return "success";
        } catch (Exception e) {
            return "error";
        }
    }
}

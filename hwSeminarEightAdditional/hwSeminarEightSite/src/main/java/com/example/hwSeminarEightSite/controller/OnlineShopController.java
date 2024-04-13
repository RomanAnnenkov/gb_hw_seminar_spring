package com.example.hwSeminarEightSite.controller;

import com.example.hwSeminarEightSite.model.Product;
import com.example.hwSeminarEightSite.service.MessageToFileGateway;
import com.example.hwSeminarEightSite.service.OnlineShopService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@AllArgsConstructor
public class OnlineShopController {

    private OnlineShopService onlineShopService;
    private MessageToFileGateway messageToFileGateway;

    @GetMapping
    public String showMainPage(Model model) {
        List<Product> products = null;
        try {
            products = onlineShopService.getAvailableProducts();
        } catch (Exception ignored) {
        }
        model.addAttribute("products", products);

        String balance = null;
        try {
            balance = onlineShopService.getAccountAmountById(2L);
        } catch (Exception ignored) {
        }
        model.addAttribute("balance", balance == null ? "" : balance);
        return "index.html";
    }

    @GetMapping("/approve/{id}")
    public String approveForm(@PathVariable Long id, Model model) {
        model.addAttribute("product", onlineShopService.getProductById(id));
        logUserRequest(String.format("GET /approve/%s", id));
        return "approve";
    }

    @PostMapping("approve")
    public String approve(Model model, Product product) {
        try {
            model.addAttribute("product", onlineShopService.buyProduct(product));
            logUserRequest(String.format("POST /approve %s", product));
            return "success";
        } catch (Exception e) {
            return "error";
        }
    }

    @GetMapping("/admin")
    public String adminPage() {
        var authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null &&
                authentication
                        .getAuthorities()
                        .stream()
                        .anyMatch(x -> x.getAuthority()
                                .equals("ROLE_ADMIN"))) {
            logUserRequest("GET /admin SUCCESS");
            return "admin";
        }
        logUserRequest("GET /admin DENIED");
        return "forbidden";
    }

    private void logUserRequest(String message) {
        messageToFileGateway.writeToFile("requests_from_users.log", message);
    }
}

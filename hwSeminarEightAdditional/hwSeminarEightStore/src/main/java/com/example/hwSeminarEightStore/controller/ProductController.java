package com.example.hwSeminarEightStore.controller;

import com.example.hwSeminarEightStore.model.Product;
import com.example.hwSeminarEightStore.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/store")
public class ProductController {

    private ProductService productService;

    @GetMapping
    public List<Product> getAllProducts() {
        return productService.getProducts();
    }

    @GetMapping("/{id}")
    public Product getProducts(@PathVariable Long id) {
        return productService.getProduct(id);
    }

    @PostMapping("/pick-up")
    public ResponseEntity<Product> getFromStock(@RequestBody Product product) {
        try {
            return ResponseEntity.ok(productService.getProductFromStore(product.getId(), product.getQuantity()));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}

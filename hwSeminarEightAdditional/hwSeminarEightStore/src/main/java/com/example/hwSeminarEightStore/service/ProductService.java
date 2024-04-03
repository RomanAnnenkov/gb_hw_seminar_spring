package com.example.hwSeminarEightStore.service;

import com.example.hwSeminarEightStore.aspects.TrackUserAction;
import com.example.hwSeminarEightStore.model.Product;
import com.example.hwSeminarEightStore.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class ProductService {

    private ProductRepository productRepository;
    @TrackUserAction
    public Product getProduct(Long id) {
        return productRepository.getProduct(id);
    }

    public List<Product> getProducts() {
        return productRepository.getProducts();
    }

    @Transactional
    @TrackUserAction
    public Product getProductFromStore(Long id, int pickUpQuantity) {
        Product product = getProduct(id);
        if (product.getName() != null) {
            int remainingQuantity = product.getQuantity() - pickUpQuantity;
            if (remainingQuantity < 0) {
                throw new RuntimeException("product out of stock");
            } else {
                productRepository.updateProductQuantity(id , remainingQuantity);
                product.setQuantity(pickUpQuantity);
                return product;
            }
        } else {
            throw new IllegalArgumentException("product not found");
        }

    }
}

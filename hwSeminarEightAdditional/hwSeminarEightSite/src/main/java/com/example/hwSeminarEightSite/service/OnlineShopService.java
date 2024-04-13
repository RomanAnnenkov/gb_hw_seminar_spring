package com.example.hwSeminarEightSite.service;

import com.example.hwSeminarEightSite.aspectrs.TrackUserAction;
import com.example.hwSeminarEightSite.configs.ExternalServices;
import com.example.hwSeminarEightSite.model.ChangeAmountRequest;
import com.example.hwSeminarEightSite.model.Product;
import lombok.AllArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.List;


@Service
@AllArgsConstructor
public class OnlineShopService {

    private final ExternalServices services;

    private final RestTemplate restTemplate = new RestTemplate();

    public List<Product> getAvailableProducts() {
        ResponseEntity<List<Product>> response = restTemplate.exchange(
                services.getGatewayUri() + "/store",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<>() {
                });
        return response.getBody();
    }

    public String getAccountAmountById(Long id) {
        ResponseEntity<String> response = restTemplate.exchange(
          services.getGatewayUri() + "/payment/account/" + id,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<>() {
                });
        return response.getBody();
    }

    public Product getProductById(Long id) {
        ResponseEntity<Product> response = restTemplate.exchange(
                services.getGatewayUri() + "/store/" + id,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<>() {
                });
        return response.getBody();
    }

    public Product buyProduct(Product productToPickUp) {
        Product productFromStore = getProductById(productToPickUp.getId());
        int quantity = productToPickUp.getQuantity();
        if (quantity < 0) {
            throw new IllegalArgumentException("Quantity must be positive.");
        }
        if (quantity > productFromStore.getQuantity()) {
            throw new IllegalArgumentException("Quantity more than in stock.");
        }
        BigDecimal amount = productToPickUp.getPrice().multiply(BigDecimal.valueOf(quantity));

        try {
            transferMoney(2L,1L, amount);
        } catch (Exception e) {
            throw new IllegalArgumentException(e.getMessage());
        }

        ResponseEntity<Product> productResult = getProductFromStore(productToPickUp);
        if (!productResult.getStatusCode().is2xxSuccessful()) {
            transferMoney(1L,2L, amount);
            throw new RuntimeException("Transfer error");
        }

        return productResult.getBody();
    }

    private void transferMoney(Long senderId, Long receiverId, BigDecimal amount) {
        ChangeAmountRequest request = new ChangeAmountRequest(senderId, receiverId, amount);
        restTemplate.postForEntity(
                services.getGatewayUri() + "/payment/transfer",
                request,
                String.class
        );
    }

    private ResponseEntity<Product> getProductFromStore(Product product) {
        return restTemplate.postForEntity(
                services.getGatewayUri() + "/store/pick-up",
                product,
                Product.class
        );
    }

}

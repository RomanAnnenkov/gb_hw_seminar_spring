package com.example.hwSeminarEightSite.service;

import com.example.hwSeminarEightSite.configs.ExternalServices;
import com.example.hwSeminarEightSite.model.ChangeAmountRequest;
import com.example.hwSeminarEightSite.model.Product;
import lombok.AllArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.Base64;
import java.util.List;
import java.util.Objects;


@Service
@AllArgsConstructor
public class OnlineShopService {

    private final ExternalServices services;

    private final RestTemplate restTemplate = new RestTemplate();

    public List<Product> getAvailableProducts() {
        ResponseEntity<List<Product>> response = restTemplate.exchange(
                services.getGatewayUri() + "/store",
                HttpMethod.GET,
                new HttpEntity<>(getAuthHeadersForStore()),
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
                new HttpEntity<>(getAuthHeadersForStore()),
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
            transferMoney(2L, 1L, amount);
        } catch (Exception e) {
            throw new IllegalArgumentException(e.getMessage());
        }

        try {
            ResponseEntity<Product> productResult = getProductFromStore(productToPickUp);
            return productResult.getBody();
        } catch (Exception e) {
            transferMoney(1L, 2L, amount);
            throw new RuntimeException("Transfer error");
        }
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
                new HttpEntity<>(product, getAuthHeadersForStore()),
                Product.class
        );
    }

    private HttpHeaders getAuthHeadersForStore() {
        HttpHeaders headers = new HttpHeaders();
        String authBase64 = Base64.getEncoder().encodeToString(Objects.requireNonNull(services).getStoreBasicAuth().getBytes());
        headers.add("Authorization", "Basic " + authBase64);
        return headers;
    }

}

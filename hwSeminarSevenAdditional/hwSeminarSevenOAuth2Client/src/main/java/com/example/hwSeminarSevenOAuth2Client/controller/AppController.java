package com.example.hwSeminarSevenOAuth2Client.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;

import java.security.Principal;
import java.util.Base64;

@Controller
public class AppController {
    @Autowired
    private final OAuth2AuthorizedClientService auth2AuthorizedClientService;

    public AppController(OAuth2AuthorizedClientService auth2AuthorizedClientService) {
        this.auth2AuthorizedClientService = auth2AuthorizedClientService;
    }

    @GetMapping
    public String getMainPage(Model model, Principal principal) {

        RestTemplate template = new RestTemplate();

        String accessToken = auth2AuthorizedClientService
                .loadAuthorizedClient("test-client", principal.getName())
                .getAccessToken()
                .getTokenValue();

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("Authorization", "Bearer " + accessToken);

        HttpEntity<String> httpEntity = new HttpEntity<>(httpHeaders);
        ResponseEntity<byte[]> response = template.exchange("http://localhost:8082/cat", HttpMethod.GET, httpEntity, byte[].class);
        String base64Image = Base64.getEncoder().encodeToString(response.getBody());
        model.addAttribute("cat", base64Image);

        return "index.html";
    }

}

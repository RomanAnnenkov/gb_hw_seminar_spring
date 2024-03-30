package com.example.hwSeminarSixRickAndMortySite.service;

import com.example.hwSeminarSixRickAndMortySite.configuration.ExternalApiProperties;
import com.example.hwSeminarSixRickAndMortySite.model.Characters;
import com.example.hwSeminarSixRickAndMortySite.model.Result;
import lombok.AllArgsConstructor;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
@AllArgsConstructor
public class CharactersService {
    private RestTemplate restTemplate;
    private HttpHeaders httpHeaders;
    private ExternalApiProperties externalApiProperties;

    public Characters getAllCharacters() {
        httpHeaders.setAccept(List.of(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<>(httpHeaders);
        ResponseEntity<Characters> response = restTemplate.exchange(externalApiProperties.getUrl(), HttpMethod.GET, entity, Characters.class);
        return response.getBody();
    }

    public Result getCharacterById(int id) {
        httpHeaders.setAccept(List.of(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<>(httpHeaders);
        ResponseEntity<Result> response = restTemplate.exchange(externalApiProperties.getUrl() + "/" + id, HttpMethod.GET, entity, Result.class);
        return response.getBody();
    }
}

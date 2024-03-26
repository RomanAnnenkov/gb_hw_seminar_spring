package com.example.hwSeminarSixRickAndMortySite.service;

import com.example.hwSeminarSixRickAndMortySite.model.Characters;
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
    private static final String CHARACTER_API = "https://rickandmortyapi.com/api/character";

    public Characters getAllCharacters() {
        httpHeaders.setAccept(List.of(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<>(httpHeaders);
        ResponseEntity<Characters> response = restTemplate.exchange(CHARACTER_API, HttpMethod.GET, entity, Characters.class);
        return response.getBody();
    }
}

package com.example.hwSeminarSixRickAndMortySite.controller;

import com.example.hwSeminarSixRickAndMortySite.model.Characters;
import com.example.hwSeminarSixRickAndMortySite.service.CharactersService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@AllArgsConstructor
public class CharactersController {

    private CharactersService charactersService;
    @GetMapping("/")
    public ResponseEntity<Characters> getCharactersPage() {
        Characters allCharacters = charactersService.getAllCharacters();
        return new ResponseEntity<>(allCharacters, HttpStatus.OK);
    }
}

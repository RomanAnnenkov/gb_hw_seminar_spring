package com.example.hwSeminarSixRickAndMortySite.controller;

import com.example.hwSeminarSixRickAndMortySite.model.Characters;
import com.example.hwSeminarSixRickAndMortySite.model.Result;
import com.example.hwSeminarSixRickAndMortySite.service.CharactersService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@AllArgsConstructor
public class CharactersController {

    private CharactersService charactersService;
    @GetMapping("/")
    public String getCharactersPage(Model model) {
        Characters allCharacters = charactersService.getAllCharacters();
        model.addAttribute("results", allCharacters.getResults());
        return "characters-list";
    }

    @GetMapping("/{id}")
    public String getCharacterPage(@PathVariable int id, Model model) {
        Result character = charactersService.getCharacterById(id);
        model.addAttribute("result", character);
        return "character";
    }
}

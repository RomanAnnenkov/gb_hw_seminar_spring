package com.example.hwSeminarFour.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ShowVariablesController {

    @RequestMapping("/vars")
    public String showVars(Model model) {
        model.addAttribute("name", "Roma");
        model.addAttribute("age", 36);
        return "show-vars.html";
    }
}

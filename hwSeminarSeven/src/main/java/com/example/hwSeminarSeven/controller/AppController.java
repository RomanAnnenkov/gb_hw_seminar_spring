package com.example.hwSeminarSeven.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AppController {

    @GetMapping
    public String getMainPage() {
        return "index.html";
    }

    @GetMapping("/public-data")
    public String getPublic() {
        return "public-data.html";
    }

    @GetMapping("/private-data")
    public String getPrivate() {
        return "private-data.html";
    }

    @GetMapping("/forbidden")
    public String getForbiddenPage() {
        return "forbidden.html";
    }

}

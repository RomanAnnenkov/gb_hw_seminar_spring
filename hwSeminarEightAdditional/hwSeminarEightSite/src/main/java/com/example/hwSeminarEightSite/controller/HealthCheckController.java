package com.example.hwSeminarEightSite.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthCheckController {
    @GetMapping("/health-check")
    ResponseEntity<Void> healthCheck() {
        return ResponseEntity.ok().build();
    }
}

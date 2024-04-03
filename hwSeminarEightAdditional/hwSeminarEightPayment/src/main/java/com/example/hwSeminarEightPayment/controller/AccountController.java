package com.example.hwSeminarEightPayment.controller;

import com.example.hwSeminarEightPayment.controller.request.ChangeAmountRequest;
import com.example.hwSeminarEightPayment.model.Account;
import com.example.hwSeminarEightPayment.service.AccountService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping("/payment")
public class AccountController {

    private AccountService accountService;

    @GetMapping("/accounts")
    public ResponseEntity<List<Account>> getAllAccount() {
        return ResponseEntity.of(Optional.ofNullable(accountService.getAllAccount()));
    }

    @GetMapping("/account/{id}")
    public ResponseEntity<String> getAccountBalance(@PathVariable Long id) {
        return ResponseEntity.ok(accountService.getAccountBalanceById(id));
    }

    @PostMapping("/transfer")
    public ResponseEntity<String> transfer(@RequestBody ChangeAmountRequest request) {
        try {
            accountService.makePayment(request.getSenderId(), request.getReceiverId(), request.getAmount());
            return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}

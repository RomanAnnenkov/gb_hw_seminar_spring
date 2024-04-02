package com.example.hwSeminarEightPayment.model;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class Account {
    private Long id;
    private String name;
    private BigDecimal amount;
}

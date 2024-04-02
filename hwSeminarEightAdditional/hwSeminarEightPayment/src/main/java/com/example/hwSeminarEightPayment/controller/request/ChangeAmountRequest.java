package com.example.hwSeminarEightPayment.controller.request;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ChangeAmountRequest {
    private Long senderId;
    private Long receiverId;
    private BigDecimal amount;
}

package com.example.hwSeminarEightSite.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class ChangeAmountRequest {
    private Long senderId;
    private Long receiverId;
    private BigDecimal amount;
}

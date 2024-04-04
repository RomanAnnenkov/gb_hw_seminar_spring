package com.example.hwSeminarEightPayment.exceptions;

public class NegativeOrZeroAmountException extends RuntimeException {
    public NegativeOrZeroAmountException(String message) {
        super(message);
    }
}

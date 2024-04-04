package com.example.hwSeminarEightPayment.service;

import com.example.hwSeminarEightPayment.exceptions.InsufficientFundsException;
import com.example.hwSeminarEightPayment.exceptions.NegativeOrZeroAmountException;
import com.example.hwSeminarEightPayment.model.Account;
import com.example.hwSeminarEightPayment.repository.AccountRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class AccountServiceMakePaymentTest {
    @Mock
    private AccountRepository repository;
    @InjectMocks
    private AccountService accountService;
    @InjectMocks
    private Account sender;
    @InjectMocks
    private Account receiver;

    @BeforeEach
    void setup() {
        sender.setId(2L);
        sender.setAmount(new BigDecimal(100));
        receiver.setId(1L);
        receiver.setAmount(new BigDecimal(0));
        given(repository.findAccountById(sender.getId())).willReturn(sender);
        given(repository.findAccountById(receiver.getId())).willReturn(receiver);
    }

    @Test
    @DisplayName("Test make payment, all parameters are correct.")
    void makePaymentTestCommon() {
        accountService.makePayment(2, 1, new BigDecimal(10));
        verify(repository).changeAmount(1, new BigDecimal(10));
        verify(repository).changeAmount(2,new BigDecimal(90));
    }

    @Test
    @DisplayName("Test make payment, zero amount")
    void makePaymentZeroAmount() {
        assertThrows(NegativeOrZeroAmountException.class,
                () -> accountService.makePayment(2, 1, new BigDecimal(0)));

        verify(repository, never()).changeAmount(anyLong(), any());
    }

    @Test
    @DisplayName("Test make payment, negative amount")
    void makePaymentNegativeAmount() {
        assertThrows(NegativeOrZeroAmountException.class,
                () -> accountService.makePayment(2, 1, new BigDecimal(-100)));

        verify(repository, never()).changeAmount(anyLong(), any());
    }

    @Test
    @DisplayName("Test make payment, sender does not have enough funds")
    void makePaymentSenderLowBalance() {
        assertThrows(InsufficientFundsException.class,
                () -> accountService.makePayment(2, 1, new BigDecimal(1000)));

        verify(repository, never()).changeAmount(anyLong(), any());
    }


}
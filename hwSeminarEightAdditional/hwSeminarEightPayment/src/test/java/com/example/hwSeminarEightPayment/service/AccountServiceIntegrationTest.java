package com.example.hwSeminarEightPayment.service;

import com.example.hwSeminarEightPayment.exceptions.AccountNotFoundException;
import com.example.hwSeminarEightPayment.model.Account;
import com.example.hwSeminarEightPayment.repository.AccountRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.dao.EmptyResultDataAccessException;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@SpringBootTest(properties = "spring.main.lazy-initialization=true")
public class AccountServiceIntegrationTest {

    @MockBean
    private AccountRepository repository;

    @Autowired
    private AccountService service;

    private final Account sender = new Account();

    private final Account receiver = new Account();

    @BeforeEach
    public void setup() {
        sender.setId(2L);
        sender.setAmount(new BigDecimal(100));

        receiver.setId(1L);
        receiver.setAmount(new BigDecimal(0));

        when(repository.findAccountById(2L)).thenReturn(sender);
        when(repository.findAccountById(1L)).thenReturn(receiver);
        when(repository.findAccountById(5L)).thenThrow(EmptyResultDataAccessException.class);
    }

    @Test
    @DisplayName("Payment test, normal flow")
    public void makePaymentTest() {
        service.makePayment(2L,1L, new BigDecimal(10));

        verify(repository).changeAmount(2L, new BigDecimal(90));
        verify(repository).changeAmount(1L, new BigDecimal(10));
    }

    @Test
    @DisplayName("Payment test, sender not exist")
    public void makePaymentSenderNotExist() {

        assertThrows(AccountNotFoundException.class,
                () -> service.makePayment(5, 1, new BigDecimal(10)));

        verify(repository, never()).changeAmount(anyLong(), any());
    }

    @Test
    @DisplayName("Payment test, receiver not exist")
    public void makePaymentReceiverNotExist() {

        assertThrows(AccountNotFoundException.class,
                () -> service.makePayment(2, 5, new BigDecimal(10)));

        verify(repository, never()).changeAmount(anyLong(), any());
    }
}

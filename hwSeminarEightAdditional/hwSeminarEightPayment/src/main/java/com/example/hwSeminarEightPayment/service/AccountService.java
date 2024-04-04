package com.example.hwSeminarEightPayment.service;

import com.example.hwSeminarEightPayment.aspects.TrackUserAction;
import com.example.hwSeminarEightPayment.exceptions.AccountNotFoundException;
import com.example.hwSeminarEightPayment.exceptions.InsufficientFundsException;
import com.example.hwSeminarEightPayment.exceptions.NegativeOrZeroAmountException;
import com.example.hwSeminarEightPayment.model.Account;
import com.example.hwSeminarEightPayment.repository.AccountRepository;
import lombok.AllArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
@AllArgsConstructor
public class AccountService {

    private AccountRepository accountRepository;

    @TrackUserAction
    public List<Account> getAllAccount() {
        return accountRepository.findAllAccounts();
    }

    public String getAccountBalanceById(Long id) {
        Account account = accountRepository.findAccountById(id);
        return account.getAmount().toString();
    }

    @Transactional
    @TrackUserAction
    public void makePayment(long senderId, long receiverID, BigDecimal amount) {
        try {
            Account sender = accountRepository.findAccountById(senderId);
            Account receiver = accountRepository.findAccountById(receiverID);

            if (amount.compareTo(new BigDecimal(0)) <= 0) {
                throw new NegativeOrZeroAmountException("Amount must be positive and more than zero");
            }

            if (sender.getAmount().compareTo(amount) < 0) {
                throw new InsufficientFundsException("There are insufficient funds in the account.");
            }
            accountRepository.changeAmount(senderId, sender.getAmount().subtract(amount));
            accountRepository.changeAmount(receiverID, receiver.getAmount().add(amount));

        } catch (EmptyResultDataAccessException e) {
            throw new AccountNotFoundException("Account not found");
        }

    }


}

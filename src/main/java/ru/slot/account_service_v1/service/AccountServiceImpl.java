package ru.slot.account_service_v1.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.slot.account_service_v1.entity.Account;
import ru.slot.account_service_v1.repository.AccountRepository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
@Slf4j
@AllArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;

    @Override
    public Long createAccount(String username, String email) {
        return accountRepository.save(new Account(username, email,LocalDateTime.now())).getAccountId() ;
    }

    @Override
    public Account getAccountById(Long accountId) {
        return  accountRepository.findById(accountId)
                .orElseThrow(() -> new RuntimeException("Account with id = {} not found" + accountId));
    }

    @Override
    public Account updateAccount(Long accountId, String email, BigDecimal balance) {
        Account updatedAccount = accountRepository.findById(accountId).orElseThrow(() -> new RuntimeException("Account with id = {} not found" + accountId));
        updatedAccount.setEmail(email);
        updatedAccount.setBalance(balance);
        return accountRepository.save(updatedAccount);
    }
}

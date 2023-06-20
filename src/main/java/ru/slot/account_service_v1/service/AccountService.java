package ru.slot.account_service_v1.service;

import ru.slot.account_service_v1.entity.Account;

import java.math.BigDecimal;
import java.util.Optional;

public interface AccountService {

    Long createAccount(String username, String email);
    Account getAccountById(Long accountId);
    Account updateAccount(Long accountId, String email, BigDecimal balance);
}

package ru.slot.account_service_v1.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.slot.account_service_v1.entity.Account;
import ru.slot.account_service_v1.repository.AccountRepository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
class AccountServiceImplTest {
    @Mock
    private AccountRepository accountRepository;

    @InjectMocks
    private AccountServiceImpl accountService;

    private Account account = new Account();


    @BeforeEach
    public void setup() {
        account.setAccountId(1L);
        account.setUsername("test");
        account.setCreatedAt(LocalDateTime.now());
        account.setEmail("test@test.test");
        account.setBalance(BigDecimal.valueOf(1000));
    }

    @DisplayName("Тест создания аккаунта позитивный")
    @Test
    public void successCreateAccount() {
        Mockito.when(accountRepository.save(any())).thenReturn(account);
        Long accountId = accountService.createAccount(account.getUsername(), account.getEmail());
        Assertions.assertEquals(1L, accountId);
        Assertions.assertNotNull(accountId);
    }

    @DisplayName("Тест получения аккаунта пo id позитивный")
    @Test
    public void successFindById() {
        Mockito.when(accountRepository.findById(any())).thenReturn(Optional.of(account));
        Account account1 = accountService.getAccountById(account.getAccountId());
        Assertions.assertEquals(account, account1);
    }

    @DisplayName("Тест получения аккаунта пo несуществующему id ")
    @Test
    public void accountNotFoundById() {
        Mockito.when(accountRepository.findById(any())).
                thenThrow(new RuntimeException("Account with id = {} not found" + 345L));
        Throwable aThrow = Assertions.assertThrows(RuntimeException.class, () -> accountService.getAccountById(345L),
                "\"Account with id = {} not found\" + 345L");
        Assertions.assertTrue(aThrow.getMessage().contains(("Account with id = {} not found" + 345L)));
    }

    @DisplayName("Тест обновления аккаунта пo id ")
    @Test
    public void updateAccount() {
        Mockito.when(accountRepository.findById(account.getAccountId())).thenReturn(Optional.of(account));
        Optional<Account> updAccount = accountRepository.findById(account.getAccountId());
        accountService.updateAccount(account.getAccountId(), "test", BigDecimal.valueOf(345));
        updAccount.get().setEmail("test1");
        Assertions.assertNotEquals(account, updAccount);
    }

}
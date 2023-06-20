package ru.slot.account_service_v1.dto;

import lombok.Data;
import org.springframework.context.annotation.Lazy;
import org.springframework.lang.Nullable;
import ru.slot.account_service_v1.entity.Account;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class ResponseAccountDto {
    private Long accountId;
    private String username;
    private BigDecimal balance;
    private String email;
    private LocalDateTime createdAt;

    public ResponseAccountDto(Account account) {
    this.username = account.getUsername();
    this.balance = account.getBalance();
    this.email = account.getEmail();
    this.createdAt = account.getCreatedAt();
    }
}

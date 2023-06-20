package ru.slot.account_service_v1.dto;

import lombok.Data;
import org.springframework.lang.Nullable;

import java.math.BigDecimal;


@Data
public class RequestAccountDto {
    private String username;
    @Nullable
    private BigDecimal balance;
    private String email;


}

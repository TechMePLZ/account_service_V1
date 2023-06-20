package ru.slot.account_service_v1.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.slot.account_service_v1.dto.RequestAccountDto;
import ru.slot.account_service_v1.dto.ResponseAccountDto;
import ru.slot.account_service_v1.service.AccountServiceImpl;

@RestController
@RequestMapping("/api/v1/account/")
@AllArgsConstructor
public class AccountController {
    private final AccountServiceImpl accountService;

    @PostMapping
    public Long createAccount(@RequestBody RequestAccountDto accountDto) {
        return accountService.createAccount(accountDto.getUsername(), accountDto.getEmail());
    }

    @GetMapping("/{accountId}")
    public ResponseAccountDto gettingAccountById(@PathVariable("accountId") Long accountId) {
        return new ResponseAccountDto(accountService.getAccountById(accountId));
    }

    @PutMapping("/{accountId}")
    public ResponseAccountDto updateAccountById(@PathVariable("accountId") Long accountId, @RequestBody RequestAccountDto accountDto){
        return new ResponseAccountDto(accountService.updateAccount(accountId, accountDto.getEmail(), accountDto.getBalance()));
    }

}

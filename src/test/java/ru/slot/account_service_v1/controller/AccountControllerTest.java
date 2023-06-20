package ru.slot.account_service_v1.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.slot.account_service_v1.dto.RequestAccountDto;
import ru.slot.account_service_v1.entity.Account;
import ru.slot.account_service_v1.service.AccountService;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.hamcrest.Matchers.notNullValue;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest
class AccountControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private AccountService accountService;

    @MockBean
    private AccountController accountController;

   private static ObjectMapper mapper = new ObjectMapper();
    private Account account = new Account();

    @BeforeEach
    public void setup() {
        account.setAccountId(1L);
        account.setUsername("test");
        account.setCreatedAt(LocalDateTime.now());
        account.setEmail("test@test.test");
        account.setBalance(BigDecimal.valueOf(1000));
    }

    @DisplayName("тест контороллера создания Аккаунта ")
    @Test
    public void createAccountTest() throws Exception {
        RequestAccountDto accountDto = RequestAccountDto.builder()
                .username("test")
                .email("testemail")
                .build();
        Mockito.when(accountService.createAccount(accountDto.getUsername(), accountDto.getEmail())).
                thenReturn(account.getAccountId());
        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post("/api/v1/account/")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(accountDto));
        mockMvc.perform(mockRequest)
                .andExpect(status().isOk());
    }


}
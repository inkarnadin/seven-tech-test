package ru.alarh.seventech.controller;

import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import ru.alarh.seventech.domain.Account;
import ru.alarh.seventech.service.AccountService;

import java.math.BigDecimal;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import static org.hamcrest.Matchers.*;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class AccountControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AccountService accountService;

    private static Account firstAccount;
    private static Account secondAccount;

    @BeforeEach
    void setUp() {
        firstAccount = new Account();
        firstAccount.setBalance(new BigDecimal(100));
        firstAccount.setAccountNumber("S-001");

        secondAccount = new Account();
        secondAccount.setBalance(new BigDecimal(30));
        secondAccount.setAccountNumber("S-002");
    }

    @Test
    @SneakyThrows
    void replenish_success() {
        when(accountService.changeMoneyBalance(firstAccount.getAccountNumber(), 10.0d)).thenReturn(110.0d);
        mockMvc.perform(post("/api/v1/account/replenish")
                    .contentType(MediaType.APPLICATION_JSON_VALUE)
                    .content("{\"accountNumber\":\"S-001\",\"amount\":10}"))
                .andDo(print())
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.currentBalance", is(110.0)))
                .andExpect(jsonPath("$.accountNumber", is("S-001")));
    }

    @Test
    void withdraw_success() throws Exception {
        when(accountService.changeMoneyBalance(firstAccount.getAccountNumber(), -10.0d)).thenReturn(90.0d);
        mockMvc.perform(post("/api/v1/account/withdraw")
                    .contentType(MediaType.APPLICATION_JSON_VALUE)
                    .content("{\"accountNumber\":\"S-001\",\"amount\":10}"))
                .andDo(print())
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.currentBalance", is(90.0)))
                .andExpect(jsonPath("$.accountNumber", is("S-001")));
    }

    @Test
    @SneakyThrows
    void transfer() {
        mockMvc.perform(post("/api/v1/account/withdraw")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content("{\"senderAccountNumber\":\"S-001\",\"recipientAccountNumber\":\"S-002\",\"amount\":10}"))
                .andDo(print())
                .andExpect(status().is2xxSuccessful());
    }

}
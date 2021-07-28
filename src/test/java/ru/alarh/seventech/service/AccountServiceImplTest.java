package ru.alarh.seventech.service;

import static org.mockito.Mockito.when;

import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.alarh.seventech.domain.Account;
import ru.alarh.seventech.exception.NegativeBalanceException;
import ru.alarh.seventech.repository.AccountRepository;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AccountServiceImplTest {

    @Autowired
    private AccountService accountService;

    @MockBean
    private AccountRepository accountRepository;

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
    void increase_money_balance() {
        when(accountRepository.findByAccountNumber(firstAccount.getAccountNumber())).thenReturn(firstAccount);

        double currentBalance = accountService.changeMoneyBalance("S-001", 10.0d);

        assertThat(currentBalance, equalTo(110.0d));
    }

    @Test
    @SneakyThrows
    void decrease_money_balance() {
        when(accountRepository.findByAccountNumber(firstAccount.getAccountNumber())).thenReturn(firstAccount);

        double currentBalance = accountService.changeMoneyBalance("S-001", -10.0d);

        assertThat(currentBalance, equalTo(90.0d));
    }

    @Test
    @SneakyThrows
    void negative_balance_exception() {
        when(accountRepository.findByAccountNumber(firstAccount.getAccountNumber())).thenReturn(firstAccount);

        assertThrows(NegativeBalanceException.class, () -> accountService.changeMoneyBalance("S-001", -200.0d));
    }

    @Test
    @SneakyThrows
    void transfer_money_between_accounts() {
        when(accountRepository.findByAccountNumber(firstAccount.getAccountNumber())).thenReturn(firstAccount);
        when(accountRepository.findByAccountNumber(secondAccount.getAccountNumber())).thenReturn(secondAccount);

        accountService.transferMoneyBetweenAccounts(firstAccount.getAccountNumber(), secondAccount.getAccountNumber(), 10.0d);

        assertThat(firstAccount.getBalance().doubleValue(), equalTo(90.0d));
        assertThat(secondAccount.getBalance().doubleValue(), equalTo(40.0d));
    }

}
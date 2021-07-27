package ru.alarh.seventech.service;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import ru.alarh.seventech.domain.Account;
import ru.alarh.seventech.exception.NegativeBalanceException;
import ru.alarh.seventech.repository.AccountRepository;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;

    @Override
    @SneakyThrows
    public double changeBalance(String accountNumber, Double amount) {
        Account currentAccount = accountRepository.findByAccountNumber(accountNumber);

        double newBalance = currentAccount.changeBalance(amount).doubleValue();
        if (newBalance < 0)
            throw new NegativeBalanceException("There are not enough funds on the account");

        accountRepository.save(currentAccount);

        return newBalance;
    }

    @Override
    @SneakyThrows
    public boolean transfer(String sender, String recipient, Double amount) {
        changeBalance(sender, -1 * amount);
        changeBalance(recipient, amount);

        return true;
    }

}
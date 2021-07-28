package ru.alarh.seventech.service;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.alarh.seventech.domain.Account;
import ru.alarh.seventech.exception.NegativeBalanceException;
import ru.alarh.seventech.repository.AccountRepository;

@Slf4j
@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;

    @Override
    @SneakyThrows
    public double changeMoneyBalance(String accountNumber, Double amount) {
        log.info("Changing balance. Account number: {}, amount: {}", accountNumber, amount);

        Account currentAccount = accountRepository.findByAccountNumber(accountNumber);

        double newBalance = currentAccount.changeBalance(amount).doubleValue();
        if (newBalance < 0)
            throw new NegativeBalanceException("There are not enough funds on the account");

        accountRepository.save(currentAccount);

        log.info("Balance was changed. Account number: {}, currentBalance: {}", accountNumber, newBalance);
        return newBalance;
    }

    @Override
    @SneakyThrows
    public void transferMoneyBetweenAccounts(String sender, String recipient, Double amount) {
        log.info("Transfer money between two accounts. Sender: {}, recipient: {}, amount: {}", sender, recipient, amount);

        changeMoneyBalance(sender, -1 * amount);
        changeMoneyBalance(recipient, amount);

        log.info("Transfer was success");
    }

}
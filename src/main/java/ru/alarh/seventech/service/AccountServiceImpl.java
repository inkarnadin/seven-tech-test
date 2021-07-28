package ru.alarh.seventech.service;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.alarh.seventech.domain.Account;
import ru.alarh.seventech.exception.MissingAccountException;
import ru.alarh.seventech.exception.NegativeBalanceException;
import ru.alarh.seventech.exception.SameAccountCollisionException;
import ru.alarh.seventech.repository.AccountRepository;

import java.util.Objects;

/**
 * Account business logic implementation class
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;

    /**
     * Account balance change method.
     * If there are enough funds on the account, the balance change operation
     * is performed else will be thrown {@link NegativeBalanceException}
     *
     * @param accountNumber unique number of account
     * @param amount balance change amount
     * @return actual balance value
     */
    @Override
    @SneakyThrows
    public double changeMoneyBalance(String accountNumber, Double amount) {
        log.info("Changing balance. Account number: {}, amount: {}", accountNumber, amount);

        Account currentAccount = accountRepository.findByAccountNumber(accountNumber);

        if (Objects.isNull(currentAccount))
            throw new MissingAccountException("Account is missing: " + accountNumber);

        double newBalance = currentAccount.changeBalance(amount).doubleValue();
        if (newBalance < 0)
            throw new NegativeBalanceException("There are not enough funds on the account");

        accountRepository.save(currentAccount);

        log.info("Balance was changed. Account number: {}, currentBalance: {}", accountNumber, newBalance);
        return newBalance;
    }

    /**
     * Method of transferring money from one account to another.
     * The method transfers funds from one account to another,
     * provided that there are enough funds on the sender's account. If the
     * transfer is not feasible, the operation is not carried out.
     *
     * If there are not enough funds on the account will be thrown {@link NegativeBalanceException}
     * If the sender's account is the same as the recipient's account {@link SameAccountCollisionException}
     *
     * @param sender number of sender account
     * @param recipient number of recipient account
     * @param amount transfer amount
     */
    @Override
    @SneakyThrows
    public void transferMoneyBetweenAccounts(String sender, String recipient, Double amount) {
        log.info("Transfer money between two accounts. Sender: {}, recipient: {}, amount: {}", sender, recipient, amount);

        if (sender.equals(recipient))
            throw new SameAccountCollisionException("Accounts for transfer must be different");

        changeMoneyBalance(sender, -1 * amount);
        changeMoneyBalance(recipient, amount);

        log.info("Transfer was success");
    }

}
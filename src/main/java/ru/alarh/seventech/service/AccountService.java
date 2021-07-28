package ru.alarh.seventech.service;

import ru.alarh.seventech.exception.NegativeBalanceException;

public interface AccountService {

    double changeMoneyBalance(String accountNumber, Double amount) throws NegativeBalanceException;
    void transferMoneyBetweenAccounts(String sender, String recipient, Double amount) throws NegativeBalanceException;

}
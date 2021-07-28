package ru.alarh.seventech.service;

import ru.alarh.seventech.exception.NegativeBalanceException;

/**
 * Account business logic layer
 */
public interface AccountService {

    /**
     * Account balance change method
     *
     * @param accountNumber unique number of account
     * @param amount balance change amount
     * @return actual balance value
     * @throws NegativeBalanceException will be throws if there are not enough funds on the account
     */
    double changeMoneyBalance(String accountNumber, Double amount) throws NegativeBalanceException;

    /**
     * Method of transferring money from one account to another
     *
     * @param sender number of sender account
     * @param recipient number of recipient account
     * @param amount transfer amount
     * @throws NegativeBalanceException will be throws if there are not enough funds on the account
     */
    void transferMoneyBetweenAccounts(String sender, String recipient, Double amount) throws NegativeBalanceException;

}
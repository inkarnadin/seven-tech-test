package ru.alarh.seventech.service;

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
     */
    double changeMoneyBalance(String accountNumber, Double amount);

    /**
     * Method of transferring money from one account to another
     *
     * @param sender number of sender account
     * @param recipient number of recipient account
     * @param amount transfer amount
     */
    void transferMoneyBetweenAccounts(String sender, String recipient, Double amount);

}
package ru.alarh.seventech.service;

import ru.alarh.seventech.exception.NegativeBalanceException;

public interface AccountService {

    double changeBalance(String accountNumber, Double amount) throws NegativeBalanceException;
    boolean transfer(String sender, String recipient, Double amount) throws NegativeBalanceException;

}
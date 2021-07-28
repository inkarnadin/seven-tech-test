package ru.alarh.seventech.exception;

public class NegativeBalanceException extends Exception {

    private static final long serialVersionUID = 1;

    public NegativeBalanceException(String s) {
        super(s);
    }

}
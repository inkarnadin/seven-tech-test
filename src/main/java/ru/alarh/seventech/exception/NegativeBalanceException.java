package ru.alarh.seventech.exception;

/**
 * Negative balance exception class
 */
public class NegativeBalanceException extends Exception {

    private static final long serialVersionUID = 1;

    public NegativeBalanceException(String s) {
        super(s);
    }

}
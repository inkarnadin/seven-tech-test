package ru.alarh.seventech.exception;

/**
 * Missing account exception class
 */
public class MissingAccountException extends Exception {

    private static final long serialVersionUID = 1;

    public MissingAccountException(String s) {
        super(s);
    }

}
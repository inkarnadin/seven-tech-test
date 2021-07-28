package ru.alarh.seventech.exception;

/**
 * Same account collision exception class
 */
public class SameAccountCollisionException extends Exception {

    private static final long serialVersionUID = 1;

    public SameAccountCollisionException(String s) {
        super(s);
    }

}
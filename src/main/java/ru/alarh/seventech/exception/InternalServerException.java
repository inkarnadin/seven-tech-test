package ru.alarh.seventech.exception;

public class InternalServerException extends Exception {

    private static final long serialVersionUID = 1;

    public InternalServerException() {
        super();
    }

    public InternalServerException(String s) {
        super(s);
    }

    public InternalServerException(Exception e) {
        super(e);
    }

}
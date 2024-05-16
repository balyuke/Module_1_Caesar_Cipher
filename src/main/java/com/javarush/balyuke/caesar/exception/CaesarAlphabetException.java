package com.javarush.balyuke.caesar.exception;

public class CaesarAlphabetException extends RuntimeException {
    String reason;

    public CaesarAlphabetException(String reason) { this.reason = reason; }

    public CaesarAlphabetException(String reason, Throwable cause) {
        super(cause);

        this.reason = reason;
    }

    public String getReason() { return this.reason; }

}

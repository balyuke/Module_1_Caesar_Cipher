package com.javarush.balyuke.caesar.exception;

public class CaesarCodingException extends RuntimeException  {
    String reason;

    public CaesarCodingException(String reason) { this.reason = reason; }

    public CaesarCodingException(String reason, Throwable cause) {
        super(cause);

        this.reason = reason;
    }

    public String getReason() { return this.reason; }


}

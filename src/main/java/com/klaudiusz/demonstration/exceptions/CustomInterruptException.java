package com.klaudiusz.demonstration.exceptions;

public class CustomInterruptException extends RuntimeException {
    public CustomInterruptException(final String message, final Throwable cause) {
        super(message, cause);
    }
}
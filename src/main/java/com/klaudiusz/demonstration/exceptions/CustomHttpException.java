package com.klaudiusz.demonstration.exceptions;

import org.springframework.http.HttpStatusCode;

public class CustomHttpException extends Exception{
    private final HttpStatusCode status;

    public CustomHttpException(String message, HttpStatusCode status) {
        super(message);
        this.status = status;
        }

    HttpStatusCode getStatus() {
        return status;
    }
}

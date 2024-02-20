package com.klaudiusz.demonstration.exceptions;

import org.springframework.http.HttpStatusCode;

@SuppressWarnings("unused")
public class CustomHttpException extends Exception {

    public CustomHttpException(final String message, final HttpStatusCode status) {
        super(message);
    }
}
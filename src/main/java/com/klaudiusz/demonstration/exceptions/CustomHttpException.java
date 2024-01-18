package com.klaudiusz.demonstration.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatusCode;

@Getter
public class CustomHttpException extends Exception{
    private final HttpStatusCode status;

    public CustomHttpException(String message, HttpStatusCode status) {
        super(message);
        this.status = status;
        }

}

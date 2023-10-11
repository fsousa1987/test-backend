package com.github.fsousa1987.effecti.testbackend.api.exceptionhandler.exceptions;

import java.io.Serial;

public class ServiceUnavailableException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = -6405209033255674364L;

    public ServiceUnavailableException(String message) {
        super(message);
    }
}

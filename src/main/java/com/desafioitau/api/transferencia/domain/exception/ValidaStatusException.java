package com.desafioitau.api.transferencia.domain.exception;

public class ValidaStatusException extends RuntimeException{
    private static final String BAD_REQUEST = "400";

    public ValidaStatusException(final String message) {
        super(message);

    }

    public  String getStatusCode() {
        return BAD_REQUEST;
    }

}

package com.desafioitau.api.transferencia.domain.exception;

public class ValidaSaldoException extends RuntimeException{
    private static final String BAD_REQUEST = "400";

    public ValidaSaldoException(final String message) {
        super(message);

    }

    public  String getStatusCode() {
        return BAD_REQUEST;
    }

}

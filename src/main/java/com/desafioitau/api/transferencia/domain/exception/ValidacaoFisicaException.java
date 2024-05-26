package com.desafioitau.api.transferencia.domain.exception;

import com.desafioitau.api.transferencia.domain.model.erros.ErroItem;

import java.util.List;

public class ValidacaoFisicaException extends RuntimeException{
    private static final String BAD_REQUEST = "400";
    private List<ErroItem> erros;

    public ValidacaoFisicaException(final String message, List<ErroItem> erros) {
        super(message);
        this.erros = erros;
    }

    public  String getStatusCode() {
        return BAD_REQUEST;
    }

    public List<ErroItem> getErros() {
        return erros;
    }
}

package com.desafioitau.api.transferencia.rest.validator;

public interface Validator<T> {
    void validar(final T data);
}

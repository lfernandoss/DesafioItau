package com.desafioitau.api.transferencia.domain.exception.handler;


import com.desafioitau.api.transferencia.domain.exception.ValidaSaldoException;
import com.desafioitau.api.transferencia.domain.exception.ValidaStatusException;
import com.desafioitau.api.transferencia.domain.exception.ValidacaoFisicaException;
import com.desafioitau.api.transferencia.domain.model.erros.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class TransferenciaExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<Object> handleException(final ValidacaoFisicaException ex) {
        final ErrorResponse errorResponse = ErrorResponse.builder()
                .codigo(ex.getStatusCode())
                .mensagem(ex.getMessage())
                .erros(ex.getErros())
                .build();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

    @ExceptionHandler
    public ResponseEntity<Object> handleException(final ValidaSaldoException ex) {
        final ErrorResponse errorResponse = ErrorResponse.builder()
                .codigo(ex.getStatusCode())
                .mensagem(ex.getMessage())
                .build();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

    @ExceptionHandler
    public ResponseEntity<Object> handleException(final ValidaStatusException ex) {
        final ErrorResponse errorResponse = ErrorResponse.builder()
                .codigo(ex.getStatusCode())
                .mensagem(ex.getMessage())
                .build();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

    @ExceptionHandler
    public ResponseEntity<Object> handleException(final RuntimeException ex) {
        final ErrorResponse errorResponse = ErrorResponse.builder()
                .codigo(String.valueOf(HttpStatus.BAD_REQUEST))
                .mensagem(ex.getMessage())
                .build();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }




}

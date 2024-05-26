package com.desafioitau.api.transferencia.rest.validator;

import com.desafioitau.api.transferencia.domain.exception.ValidacaoFisicaException;
import com.desafioitau.api.transferencia.rest.dto.request.ContaTransacaoRequestDTO;
import com.desafioitau.api.transferencia.rest.dto.request.TransferenciaRequestDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;

class TransferenciaRequestValidatorTest {

    @InjectMocks
    private TransferenciaRequestValidator transferenciaRequestValidator;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldValidateWithNoErrors() {
        TransferenciaRequestDTO transferenciaRequest = new TransferenciaRequestDTO();
        transferenciaRequest.setIdCliente("123");
        transferenciaRequest.setValor(100.0);
        ContaTransacaoRequestDTO conta = new ContaTransacaoRequestDTO();
        conta.setIdOrigem("1");
        conta.setIdDestino("2");
        transferenciaRequest.setConta(conta);

        assertDoesNotThrow(() -> transferenciaRequestValidator.validar(transferenciaRequest));
    }

    @Test
    void shouldThrowErrorWhenIdClienteIsNull() {
        TransferenciaRequestDTO transferenciaRequest = new TransferenciaRequestDTO();
        transferenciaRequest.setIdCliente(null);
        transferenciaRequest.setValor(100.0);
        ContaTransacaoRequestDTO conta = new ContaTransacaoRequestDTO();
        conta.setIdOrigem("1");
        conta.setIdDestino("2");
        transferenciaRequest.setConta(conta);

        assertThrows(ValidacaoFisicaException.class, () -> transferenciaRequestValidator.validar(transferenciaRequest));
    }

    @Test
    void shouldThrowErrorWhenValorIsNull() {
        TransferenciaRequestDTO transferenciaRequest = new TransferenciaRequestDTO();
        transferenciaRequest.setIdCliente("123");
        ContaTransacaoRequestDTO conta = new ContaTransacaoRequestDTO();
        conta.setIdOrigem("1");
        conta.setIdDestino("2");
        transferenciaRequest.setConta(conta);

        assertThrows(ValidacaoFisicaException.class, () -> transferenciaRequestValidator.validar(transferenciaRequest));
    }

    @Test
    void shouldThrowErrorWhenIdOrigemIsNull() {
        TransferenciaRequestDTO transferenciaRequest = new TransferenciaRequestDTO();
        transferenciaRequest.setIdCliente("123");
        transferenciaRequest.setValor(100.0);
        ContaTransacaoRequestDTO conta = new ContaTransacaoRequestDTO();
        conta.setIdOrigem(null);
        conta.setIdDestino("2");
        transferenciaRequest.setConta(conta);

        assertThrows(ValidacaoFisicaException.class, () -> transferenciaRequestValidator.validar(transferenciaRequest));
    }

    @Test
    void shouldThrowErrorWhenIdDestinoIsNull() {
        TransferenciaRequestDTO transferenciaRequest = new TransferenciaRequestDTO();
        transferenciaRequest.setIdCliente("123");
        transferenciaRequest.setValor(100.0);
        ContaTransacaoRequestDTO conta = new ContaTransacaoRequestDTO();
        conta.setIdOrigem("1");
        conta.setIdDestino(null);
        transferenciaRequest.setConta(conta);

        assertThrows(ValidacaoFisicaException.class, () -> transferenciaRequestValidator.validar(transferenciaRequest));
    }
}
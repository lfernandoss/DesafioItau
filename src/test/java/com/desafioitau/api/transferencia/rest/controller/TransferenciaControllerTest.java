package com.desafioitau.api.transferencia.rest.controller;

import static org.junit.jupiter.api.Assertions.*;

import com.desafioitau.api.transferencia.domain.model.Comprovante;
import com.desafioitau.api.transferencia.domain.model.Transferencia;
import com.desafioitau.api.transferencia.domain.ports.in.TranferenciaServicePort;
import com.desafioitau.api.transferencia.rest.dto.request.TransferenciaRequestDTO;
import com.desafioitau.api.transferencia.rest.dto.response.TransferenciaResponseDTO;
import com.desafioitau.api.transferencia.rest.mapper.TransacaoMapper;
import com.desafioitau.api.transferencia.rest.validator.TransferenciaRequestValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class TransferenciaControllerTest {

    @InjectMocks
    private TransferenciaController transferenciaController;

    @Mock
    private TranferenciaServicePort tranferenciaServicePort;

    @Mock
    private TransferenciaRequestValidator transferenciaRequestValidator;

    @Mock
    private TransacaoMapper mapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldReturnOkWhenEfetuarTransferenciaIsCalled() {
        TransferenciaRequestDTO request = new TransferenciaRequestDTO();
        Comprovante comprovante = new Comprovante();
        TransferenciaResponseDTO response = new TransferenciaResponseDTO();
        Transferencia transferencia = new Transferencia();

        when(mapper.toDomain(request)).thenReturn(transferencia);
        when(tranferenciaServicePort.processarTransferencia(any())).thenReturn(comprovante);
        when(mapper.toResponse(comprovante)).thenReturn(response);

        ResponseEntity<TransferenciaResponseDTO> result = transferenciaController.efetuarTransferencia(request);

        assertEquals(ResponseEntity.ok().body(response), result);
        verify(transferenciaRequestValidator, times(1)).validar(request);
        verify(tranferenciaServicePort, times(1)).processarTransferencia(any());
    }

    @Test
    void shouldThrowExceptionWhenEfetuarTransferenciaIsCalledAndValidationFails() {
        TransferenciaRequestDTO request = new TransferenciaRequestDTO();

        doThrow(new RuntimeException()).when(transferenciaRequestValidator).validar(request);

        assertThrows(RuntimeException.class, () -> transferenciaController.efetuarTransferencia(request));
        verify(transferenciaRequestValidator, times(1)).validar(request);
        verify(tranferenciaServicePort, times(0)).processarTransferencia(any());
    }
}
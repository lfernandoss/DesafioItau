package com.desafioitau.api.transferencia.integration.adapter;

import static org.junit.jupiter.api.Assertions.*;

import com.desafioitau.api.transferencia.integration.api.BacenAPI;
import com.desafioitau.api.transferencia.integration.api.ClienteAPI;
import com.desafioitau.api.transferencia.integration.api.ContaAPI;
import com.desafioitau.api.transferencia.integration.mapper.IntegrationMapper;
import com.desafioitau.api.transferencia.integration.model.Request.TransacaoRequest;
import com.desafioitau.api.transferencia.integration.model.Response.ClienteResponse;
import com.desafioitau.api.transferencia.integration.model.Response.ContaResponse;
import com.desafioitau.api.transferencia.integration.sqs.SQSSendService;
import com.desafioitau.api.transferencia.domain.model.Cliente;
import com.desafioitau.api.transferencia.domain.model.Conta;
import com.desafioitau.api.transferencia.domain.model.DTO.TransacaoDTO;
import feign.FeignException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;

class TranferenciaAdapterImplTest {

    @InjectMocks
    private TranferenciaAdapterImpl transferenciaAdapter;

    @Mock
    private ClienteAPI clienteAPI;

    @Mock
    private ContaAPI contaAPI;

    @Mock
    private BacenAPI bacenAPI;

    @Mock
    private IntegrationMapper mapper;

    @Mock
    private SQSSendService sqsSendService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldConsultarClienteWithNoErrors() {
        ClienteResponse clienteResponse = new ClienteResponse();
        when(clienteAPI.consultarCliente(anyString())).thenReturn(clienteResponse);
        when(mapper.toDomainClienteResponse(clienteResponse)).thenReturn(new Cliente());

        transferenciaAdapter.consultarCliente("123");

        verify(clienteAPI, times(1)).consultarCliente(anyString());
    }

    @Test
    void shouldConsultarContaWithNoErrors() {
        ContaResponse contaResponse = new ContaResponse();
        when(contaAPI.consultarConta(anyString())).thenReturn(contaResponse);
        when(mapper.toDomainContaResponse(contaResponse)).thenReturn(new Conta());

        transferenciaAdapter.consultarConta("1");

        verify(contaAPI, times(1)).consultarConta(anyString());
    }

    @Test
    void shouldTransferirValorWithNoErrors() {
        TransacaoDTO transacaoDTO = new TransacaoDTO();
        TransacaoRequest transacaoRequest = new TransacaoRequest();
        when(mapper.toTransacaoRequest(transacaoDTO)).thenReturn(transacaoRequest);

        transferenciaAdapter.transferirValor(transacaoDTO);

        verify(contaAPI, times(1)).transferirSaldo(transacaoRequest);
    }

    @Test
    void shouldThrowErrorWhenTransferirValorFails() {
        TransacaoDTO transacaoDTO = new TransacaoDTO();
        TransacaoRequest transacaoRequest = new TransacaoRequest();
        when(mapper.toTransacaoRequest(transacaoDTO)).thenReturn(transacaoRequest);

        doThrow(FeignException.class).when(contaAPI).transferirSaldo(transacaoRequest);


        assertThrows(RuntimeException.class, () -> transferenciaAdapter.transferirValor(transacaoDTO));
    }

    @Test
    void shouldProcessarBacenWithNoErrors() {
        TransacaoDTO transacaoDTO = new TransacaoDTO();
        TransacaoRequest transacaoRequest = new TransacaoRequest();
        when(mapper.toTransacaoRequest(transacaoDTO)).thenReturn(transacaoRequest);

        transferenciaAdapter.processarBacen(transacaoDTO);

        verify(bacenAPI, times(1)).informarTransacao(transacaoRequest);
    }

    @Test
    void shouldThrowErrorWhenProcessarBacenFails() {
        TransacaoDTO transacaoDTO = new TransacaoDTO();
        TransacaoRequest transacaoRequest = new TransacaoRequest();
        when(mapper.toTransacaoRequest(transacaoDTO)).thenReturn(transacaoRequest);
        doThrow(FeignException.class).when(bacenAPI).informarTransacao(transacaoRequest);

        assertThrows(RuntimeException.class, () -> transferenciaAdapter.processarBacen(transacaoDTO));
    }
}
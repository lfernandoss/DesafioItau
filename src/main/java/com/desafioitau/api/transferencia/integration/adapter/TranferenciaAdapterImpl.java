package com.desafioitau.api.transferencia.integration.adapter;

import com.desafioitau.api.transferencia.domain.model.Conta;
import com.desafioitau.api.transferencia.integration.mapper.IntegrationMapper;
import com.desafioitau.api.transferencia.integration.model.Request.TransacaoRequest;
import com.desafioitau.api.transferencia.integration.sqs.SQSSendService;
import feign.FeignException;
import com.desafioitau.api.transferencia.integration.api.BacenAPI;
import com.desafioitau.api.transferencia.integration.api.ClienteAPI;
import com.desafioitau.api.transferencia.integration.api.ContaAPI;
import com.desafioitau.api.transferencia.domain.model.Cliente;
import com.desafioitau.api.transferencia.domain.model.DTO.TransacaoDTO;
import com.desafioitau.api.transferencia.domain.ports.out.TranferenciaAdapterPort;
import com.desafioitau.api.transferencia.integration.model.Response.ClienteResponse;
import com.desafioitau.api.transferencia.integration.model.Response.ContaResponse;

import io.github.resilience4j.retry.annotation.Retry;
import io.micrometer.core.annotation.Timed;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TranferenciaAdapterImpl implements TranferenciaAdapterPort {

    @Autowired
    private ClienteAPI clienteAPI;

    @Autowired
    private ContaAPI contaAPI;

    @Autowired
    private BacenAPI bacenAPI;

    @Autowired
    private IntegrationMapper mapper;

    @Autowired
    private SQSSendService sqsSendService;

    private static final int TOO_MANY_REQUESTS = 429;



    @Override
    @Retry(name = "consultarCliente")
    public void consultarCliente(String idCliente) {
        try {
            ClienteResponse clienteResponse = clienteAPI.consultarCliente(idCliente);
        } catch (FeignException e) {
            throw new RuntimeException("Erro ao consultar cliente");
        }
    }


    @Override
    @Retry(name = "consultarConta")
    public Conta consultarConta(String idConta) {
        try {
            ContaResponse contaResponse = contaAPI.consultarConta(idConta);
            return  mapper.toDomainContaResponse(contaResponse);
        } catch (FeignException e) {
            throw new RuntimeException("Erro ao consultar conta");
        }
    }


    @Override
    @Retry(name = "transferirValor")
    public void transferirValor(TransacaoDTO transacaoDTO) {
        try {
            contaAPI.transferirSaldo(mapper.toTransacaoRequest(transacaoDTO));
        } catch (FeignException  e) {
            throw new RuntimeException("Erro ao transferir saldo");
        }

    }


    @Override
    @Retry(name = "processarBacen")
    public void processarBacen(TransacaoDTO transacaoDTO) {
        try {
            bacenAPI.informarTransacao(mapper.toTransacaoRequest(transacaoDTO));
        }catch (FeignException e) {

            if(e.status() == TOO_MANY_REQUESTS)
            {
                sqsSendService.enviarMsg(transacaoDTO);
                return;
            }

            throw new RuntimeException("Erro ao informar o Bacen");
        }

    }

}

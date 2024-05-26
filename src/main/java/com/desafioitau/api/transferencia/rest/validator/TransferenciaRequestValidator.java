package com.desafioitau.api.transferencia.rest.validator;

import  com.desafioitau.api.transferencia.domain.exception.ValidacaoFisicaException;
import  com.desafioitau.api.transferencia.domain.model.erros.ErroItem;
import com.desafioitau.api.transferencia.rest.dto.request.ContaTransacaoRequestDTO;
import com.desafioitau.api.transferencia.rest.dto.request.TransferenciaRequestDTO;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class TransferenciaRequestValidator implements Validator<TransferenciaRequestDTO> {

    private List<ErroItem> erros;

    @Override
    public void validar(TransferenciaRequestDTO transferenciaRequest) {
        erros = new ArrayList<>();
        validarIdCliente(transferenciaRequest.getIdCliente());
        validarValor(transferenciaRequest.getValor());
        validarConta(transferenciaRequest.getConta());

        if(!erros.isEmpty()) {
            throw new ValidacaoFisicaException("Erro de validação", erros);
        }


    }

    private void validarConta(ContaTransacaoRequestDTO conta) {
        if (conta.getIdOrigem() == null || conta.getIdOrigem().equals("")){
            erros.add(ErroItem.builder().campo("idOrigem").mensagem("O id da conta de origem é obrigatório").build());
        }
        if (conta.getIdDestino() == null || conta.getIdDestino().equals("")){
            erros.add(ErroItem.builder().campo("idDestino").mensagem("O id da conta de destino é obrigatório").build());
        }

    }

    private void validarValor(Double valor) {
        if (valor == null || valor < 1) {
            erros.add(ErroItem.builder().campo("valor").mensagem("O valor da transferência nao pode ser nulo ou menor que 1 ").build());
        }
    }

    private void validarIdCliente(String idCliente) {
        if (idCliente == null || idCliente == "") {
            erros.add(ErroItem.builder().campo("idCliente").mensagem("O id do cliente é obrigatório").build());
        }
    }

}

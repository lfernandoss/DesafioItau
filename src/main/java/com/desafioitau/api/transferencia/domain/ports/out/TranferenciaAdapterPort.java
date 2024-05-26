package com.desafioitau.api.transferencia.domain.ports.out;

import com.desafioitau.api.transferencia.domain.model.Cliente;
import com.desafioitau.api.transferencia.domain.model.Conta;
import com.desafioitau.api.transferencia.domain.model.DTO.TransacaoDTO;

public interface TranferenciaAdapterPort {

   Cliente consultarCliente(String idCliente);
   Conta consultarConta(String idConta);
   void transferirValor(TransacaoDTO transacaoDTO);
   void processarBacen(TransacaoDTO transacaoDTO);

}

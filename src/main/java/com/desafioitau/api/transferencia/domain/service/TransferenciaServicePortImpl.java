package com.desafioitau.api.transferencia.domain.service;

import com.desafioitau.api.transferencia.domain.exception.ValidaSaldoException;
import com.desafioitau.api.transferencia.domain.exception.ValidaStatusException;
import com.desafioitau.api.transferencia.domain.mapper.ComprovanteMapper;
import com.desafioitau.api.transferencia.domain.model.Cliente;
import com.desafioitau.api.transferencia.domain.model.Comprovante;
import com.desafioitau.api.transferencia.domain.model.Conta;
import com.desafioitau.api.transferencia.domain.model.DTO.ContaTransacaoDTO;
import com.desafioitau.api.transferencia.domain.model.DTO.TransacaoDTO;
import com.desafioitau.api.transferencia.domain.model.Transferencia;
import com.desafioitau.api.transferencia.domain.ports.in.TranferenciaServicePort;
import com.desafioitau.api.transferencia.domain.ports.out.TranferenciaAdapterPort;
import com.desafioitau.api.transferencia.repository.ComprovanteRepository;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransferenciaServicePortImpl implements TranferenciaServicePort {

    @Autowired
    private TranferenciaAdapterPort tranferenciaAdapter;

    @Autowired
    private ComprovanteRepository comprovanteRepository;

    @Autowired
    private ComprovanteMapper comprovanteMapper;

    @Override
    public Comprovante processarTransferencia(final Transferencia transferenciaDomain) {
        final Cliente clienteOrigem = tranferenciaAdapter.consultarCliente(transferenciaDomain.getIdCliente());

        final Conta contaCliente = tranferenciaAdapter.consultarConta(transferenciaDomain.getConta().getIdOrigem());

        validarStatusConta(contaCliente);

        validarSaldo(contaCliente , transferenciaDomain.getValor());

        final TransacaoDTO trasacao = criarTransacao(transferenciaDomain);

        tranferenciaAdapter.transferirValor(trasacao);

        tranferenciaAdapter.processarBacen(trasacao);

        return comprovanteTransferencia(transferenciaDomain);
    }

    private void validarStatusConta(Conta contaCliente) {
        if (!contaCliente.isAtivo()){
            throw new ValidaStatusException("Conta inativa");
        }
    }


    private void validarSaldo(Conta contaCliente, Double valor) {
        if (contaCliente.saldoTotal() < 0 || contaCliente.saldoTotal() < valor ){
            throw new ValidaSaldoException("Saldo insuficiente");
        }
    }

    private TransacaoDTO criarTransacao(Transferencia transferenciaDomain) {
        return TransacaoDTO.builder().valor(transferenciaDomain.getValor())
                .conta(ContaTransacaoDTO.builder()
                        .idOrigem(transferenciaDomain.getConta().getIdOrigem())
                        .idDestino(transferenciaDomain.getConta().getIdDestino()).build())
                .build();
    }

    private Comprovante comprovanteTransferencia(Transferencia transferenciaDomain) {

        return comprovanteMapper.toDomain(comprovanteRepository.save(comprovanteMapper.toEntity(Comprovante.builder()
                .idCliente(transferenciaDomain.getIdCliente())
                .dataHoraComprovante(DateTime.now().toDate())
                .conta(transferenciaDomain.getConta())
                .build())));

    }

}

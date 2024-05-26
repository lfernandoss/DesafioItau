package com.desafioitau.api.transferencia.domain.service;


import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.loader.FixtureFactoryLoader;
import com.desafioitau.api.transferencia.domain.exception.ValidaSaldoException;
import com.desafioitau.api.transferencia.domain.exception.ValidaStatusException;
import com.desafioitau.api.transferencia.domain.mapper.ComprovanteMapper;
import com.desafioitau.api.transferencia.domain.model.Cliente;
import com.desafioitau.api.transferencia.domain.model.Conta;
import com.desafioitau.api.transferencia.domain.model.DTO.TransacaoDTO;
import com.desafioitau.api.transferencia.domain.model.Transferencia;
import com.desafioitau.api.transferencia.domain.ports.out.TranferenciaAdapterPort;
import com.desafioitau.api.transferencia.repository.ComprovanteRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;


@ExtendWith(SpringExtension.class)
class TransferenciaServicePortImplTest {

    @InjectMocks
    private TransferenciaServicePortImpl transferenciaService;

    @Mock
    private ComprovanteMapper comprovanteMapper;

    @Mock
    private TranferenciaAdapterPort transferenciaAdapter;

    @Mock
    private ComprovanteRepository comprovanteRepository;

    @BeforeEach
    void setUp() {
        FixtureFactoryLoader.loadTemplates("com.desafioitau.api.transferencia.utils");
        MockitoAnnotations.openMocks(this);
    }


    @Test
    void shouldProcessTransferenciaWithNoErrors() {
        Transferencia transferencia =  Fixture.from(Transferencia.class).gimme("create");
        Cliente cliente =  Fixture.from(Cliente.class).gimme("create");
        Conta contaCliente =  Fixture.from(Conta.class).gimme("create");

        when(transferenciaAdapter.consultarCliente(anyString())).thenReturn(cliente);
        when(transferenciaAdapter.consultarConta(anyString())).thenReturn(contaCliente);

        transferenciaService.processarTransferencia(transferencia);

        verify(transferenciaAdapter, times(1)).transferirValor(any(TransacaoDTO.class));
        verify(transferenciaAdapter, times(1)).processarBacen(any(TransacaoDTO.class));
    }

    @Test
    void shouldThrowErrorWhenContaInativa() {
        Transferencia transferencia =  Fixture.from(Transferencia.class).gimme("create");
        Cliente cliente =  Fixture.from(Cliente.class).gimme("create");
        Conta contaCliente =  Fixture.from(Conta.class).gimme("create");
        contaCliente.setAtivo(false);

        when(transferenciaAdapter.consultarCliente(anyString())).thenReturn(cliente);
        when(transferenciaAdapter.consultarConta(anyString())).thenReturn(contaCliente);

        assertThrows(ValidaStatusException.class, () -> transferenciaService.processarTransferencia(transferencia));
    }

    @Test
    void shouldThrowErrorWhenSaldoInsuficiente() {
        Transferencia transferencia =  Fixture.from(Transferencia.class).gimme("create");
        Cliente cliente =  Fixture.from(Cliente.class).gimme("create");
        Conta contaCliente =  Fixture.from(Conta.class).gimme("create");
        transferencia.setValor(10000.0);


        when(transferenciaAdapter.consultarCliente(anyString())).thenReturn(cliente);
        when(transferenciaAdapter.consultarConta(anyString())).thenReturn(contaCliente);

        assertThrows(ValidaSaldoException.class, () -> transferenciaService.processarTransferencia(transferencia));
    }
}
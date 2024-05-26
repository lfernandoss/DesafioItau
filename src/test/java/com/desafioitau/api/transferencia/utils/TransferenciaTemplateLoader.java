package com.desafioitau.api.transferencia.utils;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;
import com.desafioitau.api.transferencia.domain.model.Cliente;
import com.desafioitau.api.transferencia.domain.model.Conta;
import com.desafioitau.api.transferencia.domain.model.ContaTransacao;
import com.desafioitau.api.transferencia.domain.model.Transferencia;
import com.desafioitau.api.transferencia.rest.dto.request.ContaTransacaoRequestDTO;
import com.desafioitau.api.transferencia.rest.dto.request.TransferenciaRequestDTO;

import java.time.LocalDate;

public class TransferenciaTemplateLoader implements TemplateLoader {

    @Override
    public void load() {

        Fixture.of(Transferencia.class).addTemplate("create", new Rule(){{
            add("idCliente", "23423");
            add("valor", 4.0);
            add("conta", fixture(ContaTransacao.class, "contavalida"));
        }});

        Fixture.of(ContaTransacao.class).addTemplate("contavalida", new Rule(){{
            add("idOrigem", "1");
            add("idDestino", "2");
        }});

        Fixture.of(ContaTransacaoRequestDTO.class).addTemplate("contavalida", new Rule(){{
            add("idOrigem", "d0d32142-74b7-4aca-9c68-838aeacef96b");
            add("idDestino", "41313d7b-bd75-4c75-9dea-1f4be434007f");
        }});

        Fixture.of(Conta.class).addTemplate("create", new Rule(){{
            add("id", null);
            add("saldo", 1000.0);
            add("ativo", true);
            add("limiteDiario", 1000.0);
        }});

        Fixture.of(Cliente.class).addTemplate("create", new Rule(){{
            add("id", "123");
            add("nome", "Teste");
            add("telefone", "123456789");
            add("tipoPessoa", "FISICA");

        }});

        Fixture.of(TransferenciaRequestDTO.class).addTemplate("create", new Rule(){{
            add("idCliente", "2ceb26e9-7b5c-417e-bf75-ffaa66e3a76f");
            add("valor", 400.0);
            add("conta", fixture(ContaTransacaoRequestDTO.class, "contavalida"));


        }});


    }
}

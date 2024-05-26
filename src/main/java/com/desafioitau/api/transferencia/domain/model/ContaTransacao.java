package com.desafioitau.api.transferencia.domain.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ContaTransacao {

    private String idOrigem;

    private String idDestino;
}

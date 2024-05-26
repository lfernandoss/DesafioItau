package com.desafioitau.api.transferencia.rest.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ContaTransacaoRequestDTO {

    private String idOrigem;
    private String idDestino;

}

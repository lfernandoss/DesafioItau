package com.desafioitau.api.transferencia.rest.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TransferenciaRequestDTO {

    private String idCliente;
    private double valor;
    private ContaTransacaoRequestDTO conta;


}

package com.desafioitau.api.transferencia.integration.model.Request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TransacaoRequest {

    @JsonProperty("valor")
    private double valor;
    @JsonProperty("conta")
    private ContaRequest conta;


}

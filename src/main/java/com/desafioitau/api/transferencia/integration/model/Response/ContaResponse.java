package com.desafioitau.api.transferencia.integration.model.Response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ContaResponse {

    private String id;
    private double saldo;
    private boolean ativo;
    private double limiteDiario;

}

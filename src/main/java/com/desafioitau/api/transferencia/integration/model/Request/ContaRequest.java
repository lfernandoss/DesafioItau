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
public class ContaRequest {


    @JsonProperty("idOrigem")
    private String idOrigem;

    @JsonProperty("idDestino")
    private String idDestino;


}

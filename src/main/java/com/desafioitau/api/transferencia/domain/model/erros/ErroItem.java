package com.desafioitau.api.transferencia.domain.model.erros;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class ErroItem {
    @JsonProperty("campo")
    private String campo;
    @JsonProperty("mensagem")
    private String mensagem;

}

package com.desafioitau.api.transferencia.domain.model.DTO;

import lombok.*;


@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ContaTransacaoDTO {

    private String idOrigem;

    private String idDestino;
}

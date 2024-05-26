package com.desafioitau.api.transferencia.domain.model.DTO;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TransacaoDTO {

    private double valor;
    private ContaTransacaoDTO conta;

}

package com.desafioitau.api.transferencia.domain.model.DTO;

import lombok.Data;

@Data
public class ContaClienteDTO {

    private String id;
    private Double saldo;
    private Boolean ativo;
    private Double limiteDiario;



}

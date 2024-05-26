package com.desafioitau.api.transferencia.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Conta  {

    private String id;
    private double saldo;
    private boolean ativo;
    private double limiteDiario;


    public Double saldoTotal() {
        return saldo + limiteDiario;
    }

}

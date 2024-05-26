package com.desafioitau.api.transferencia.domain.model;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Cliente  {

    private String id;
    private String nome;
    private String telefone;
    private String tipoPessoa;

}

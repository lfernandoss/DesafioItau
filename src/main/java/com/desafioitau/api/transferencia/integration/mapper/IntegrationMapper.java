package com.desafioitau.api.transferencia.integration.mapper;

import com.desafioitau.api.transferencia.domain.model.Cliente;
import com.desafioitau.api.transferencia.domain.model.Conta;
import com.desafioitau.api.transferencia.domain.model.DTO.TransacaoDTO;
import com.desafioitau.api.transferencia.integration.model.Request.TransacaoRequest;
import com.desafioitau.api.transferencia.integration.model.Response.ClienteResponse;
import com.desafioitau.api.transferencia.integration.model.Response.ContaResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface IntegrationMapper {


    Cliente toDomainClienteResponse(ClienteResponse request);
    Conta toDomainContaResponse(ContaResponse request);
    TransacaoRequest toTransacaoRequest(TransacaoDTO request);


}

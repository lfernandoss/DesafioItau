package com.desafioitau.api.transferencia.rest.mapper;

import com.desafioitau.api.transferencia.domain.model.Comprovante;
import com.desafioitau.api.transferencia.domain.model.Transferencia;
import com.desafioitau.api.transferencia.rest.dto.request.TransferenciaRequestDTO;
import com.desafioitau.api.transferencia.rest.dto.response.TransferenciaResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TransacaoMapper {

    @Mapping(target = "conta", source = "conta")
    Transferencia toDomain(TransferenciaRequestDTO request);
    TransferenciaResponseDTO toResponse(Comprovante domain);


}

package com.desafioitau.api.transferencia.domain.mapper;

import com.desafioitau.api.transferencia.domain.model.Comprovante;
import com.desafioitau.api.transferencia.repository.entity.ComprovanteEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ComprovanteMapper {

    ComprovanteEntity toEntity(Comprovante comprovante);
    Comprovante toDomain(ComprovanteEntity entity);

}

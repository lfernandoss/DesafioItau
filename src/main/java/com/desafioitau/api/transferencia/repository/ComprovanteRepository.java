package com.desafioitau.api.transferencia.repository;

import com.desafioitau.api.transferencia.repository.entity.ComprovanteEntity;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@EnableScan
@Repository
public interface ComprovanteRepository extends CrudRepository<ComprovanteEntity, String> {

}

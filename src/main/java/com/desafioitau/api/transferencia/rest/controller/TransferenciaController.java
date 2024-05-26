package com.desafioitau.api.transferencia.rest.controller;

import com.desafioitau.api.transferencia.domain.model.Comprovante;
import com.desafioitau.api.transferencia.domain.ports.in.TranferenciaServicePort;
import com.desafioitau.api.transferencia.rest.dto.request.TransferenciaRequestDTO;
import com.desafioitau.api.transferencia.rest.dto.response.TransferenciaResponseDTO;
import com.desafioitau.api.transferencia.rest.mapper.TransacaoMapper;
import com.desafioitau.api.transferencia.rest.validator.TransferenciaRequestValidator;
import io.micrometer.core.annotation.Timed;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/v1/transferencia")
public class TransferenciaController {

    @Autowired
    private TranferenciaServicePort tranferenciaServicePort;

    @Autowired
    private TransferenciaRequestValidator transferenciaRequestValidator;

    @Autowired
    private TransacaoMapper mapper;

    @PostMapping
    @Timed(value = "transferencia.efetuarTransferencia")
    public ResponseEntity<TransferenciaResponseDTO> efetuarTransferencia(@RequestBody TransferenciaRequestDTO request)
    {
        log.info("Validar campos idCLiente {}", request.getIdCliente());

        transferenciaRequestValidator.validar(request);

        log.info("Processar idCLiente {}", request.getIdCliente());

        Comprovante comprovante = tranferenciaServicePort.processarTransferencia(mapper.toDomain(request));

        log.info("Transferencia efetuada com sucesso idCliente {}", request.getIdCliente());

        return ResponseEntity.ok().body(mapper.toResponse(comprovante));
    }
}

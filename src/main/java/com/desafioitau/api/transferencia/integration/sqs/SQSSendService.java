package com.desafioitau.api.transferencia.integration.sqs;

import com.desafioitau.api.transferencia.domain.model.DTO.TransacaoDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.aws.messaging.config.annotation.NotificationMessage;
import org.springframework.cloud.aws.messaging.core.QueueMessagingTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class SQSSendService {

    @Value("${events.queue}")
    private String fila;

    private final QueueMessagingTemplate messagingTemplate;

    public void enviarMsg(TransacaoDTO message) {
        log.info("Notifying queue {}", fila);
        messagingTemplate.convertAndSend(fila, message);
    }
}

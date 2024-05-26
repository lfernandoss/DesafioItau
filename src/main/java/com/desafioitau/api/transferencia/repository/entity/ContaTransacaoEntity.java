package com.desafioitau.api.transferencia.repository.entity;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBDocument;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.joda.time.DateTime;

import java.util.UUID;

@Data
@DynamoDBDocument
@NoArgsConstructor
@AllArgsConstructor
public class ContaTransacaoEntity {

    @DynamoDBAttribute
    private String idOrigem;

    @DynamoDBAttribute
    private String idDestino;

}

#!/bin/bash

# cria a fila
awslocal sqs create-queue --queue-name processarBacen-queue

echo Creating  DynamoDb \'ItemInfo\' table ...
echo $(awslocal dynamodb create-table --cli-input-json '{"TableName":"tb_comprovante", "KeySchema":[{"AttributeName":"id","KeyType":"HASH"}], "AttributeDefinitions":[{"AttributeName":"id","AttributeType":"S"}],"BillingMode":"PAY_PER_REQUEST"}')

echo Listing tables ...
echo $(awslocal dynamodb list-tables)
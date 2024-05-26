package com.desafioitau.api.transferencia.integration.api;


import com.desafioitau.api.transferencia.integration.model.Request.TransacaoRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(url= "http://localhost:9090/" , name = "BacenAPI")
public interface BacenAPI {

    @PostMapping("notificacoes")
    void informarTransacao(@RequestBody TransacaoRequest transacaoRequest);

}

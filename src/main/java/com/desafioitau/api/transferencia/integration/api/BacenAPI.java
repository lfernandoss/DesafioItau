package com.desafioitau.api.transferencia.integration.api;


import com.desafioitau.api.transferencia.integration.model.Request.TransacaoRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(url= "${url.bacen}" , name = "BacenAPI")
public interface BacenAPI {

    @PostMapping("notificacoes")
    void informarTransacao(@RequestBody TransacaoRequest transacaoRequest);

}

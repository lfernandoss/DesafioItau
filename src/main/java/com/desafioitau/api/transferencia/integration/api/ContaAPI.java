package com.desafioitau.api.transferencia.integration.api;


import com.desafioitau.api.transferencia.integration.model.Request.TransacaoRequest;
import com.desafioitau.api.transferencia.integration.model.Response.ContaResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(url= "${url.conta}" , name = "ContaAPI")
public interface ContaAPI {

    @GetMapping("contas/{IdConta}")
    ContaResponse consultarConta(@PathVariable("IdConta") String IdConta);

    @PutMapping("contas/saldos")
    void transferirSaldo(@RequestBody TransacaoRequest transacaoRequest);

}

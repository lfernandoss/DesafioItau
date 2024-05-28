package com.desafioitau.api.transferencia.integration.api;


import com.desafioitau.api.transferencia.integration.model.Response.ClienteResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@FeignClient(url= "${url.cliente}" , name = "ClienteAPI")
public interface ClienteAPI {

    @GetMapping(value = "clientes/{idCliente}" , produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    ClienteResponse consultarCliente(@PathVariable("idCliente") String idCliente);

}

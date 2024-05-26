package com.desafioitau.api.transferencia.domain.ports.in;

import com.desafioitau.api.transferencia.domain.model.Comprovante;
import com.desafioitau.api.transferencia.domain.model.Transferencia;

public interface TranferenciaServicePort {

   Comprovante processarTransferencia(Transferencia transferenciaDomain);

}

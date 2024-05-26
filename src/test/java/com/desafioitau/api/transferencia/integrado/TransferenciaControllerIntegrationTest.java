package com.desafioitau.api.transferencia.integrado;


import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.loader.FixtureFactoryLoader;
import com.desafioitau.api.transferencia.rest.dto.request.TransferenciaRequestDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class TransferenciaControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        FixtureFactoryLoader.loadTemplates("com.desafioitau.api.transferencia.utils");
    }


    @Test
    void shouldReturnOkWhenEfetuarTransferenciaIsCalledWithValidRequest() throws Exception {
        TransferenciaRequestDTO request = Fixture.from(TransferenciaRequestDTO.class).gimme("create");

        mockMvc.perform(post("/v1/transferencia")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk());
    }

    @Test
    void shouldReturnBadRequestWhenEfetuarTransferenciaIsCalledWithInvalidRequest() throws Exception {
        TransferenciaRequestDTO request = Fixture.from(TransferenciaRequestDTO.class).gimme("create");
        request.setIdCliente("");

        mockMvc.perform(post("/v1/transferencia")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest());
    }
}
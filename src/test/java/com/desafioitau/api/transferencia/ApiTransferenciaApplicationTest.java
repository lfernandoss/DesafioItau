package com.desafioitau.api.transferencia;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ApiTransferenciaApplication.class)
@TestPropertySource(locations = "classpath:application.properties")
class ApiTransferenciaApplicationTest {

    @Test
    void contextLoads() {
    }

}
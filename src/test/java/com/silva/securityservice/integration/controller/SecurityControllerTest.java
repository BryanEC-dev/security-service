package com.silva.securityservice.integration.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.silva.securityservice.controller.SecurityController;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(SecurityController.class)
@ActiveProfiles("test")
class SecurityControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper;

    @BeforeEach
    void setUp() {
        System.out.println("Preparando prueba");
    }

    @AfterEach
    void tearDown(){
        System.out.println("Finalizando prueba");
    }

    @BeforeAll
    static void initAll(){
        System.out.println("Inicialización global...");
    }

    @AfterAll
    static void  tearDownAll(){
        System.out.println("Liberando recursos globales...");
    }

    @Test
    void create_user_201() throws Exception {
       mockMvc.perform(post("/api/auth/users//create")
               .contentType(MediaType.APPLICATION_JSON))
               .andExpect(header().doesNotExist("Location"))
               .andExpect(content().contentType(MediaType.APPLICATION_JSON))
               .andExpect(status().isCreated());

    }
}
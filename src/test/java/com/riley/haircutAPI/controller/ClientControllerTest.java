package com.riley.haircutAPI.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.riley.haircutAPI.entity.Client;
import com.riley.haircutAPI.service.ClientService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ClientController.class)
class ClientControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    ClientService service;

    ObjectMapper objectMapper;
    Client client;

    @BeforeEach
    void setUp() {
        client = Client.builder()
                .email("riley@example.com")
                .mobile(123456789)
                .firstName("riley")
                .lastName("mckinley")
                .clientId(1L)
                .build();

        objectMapper = new ObjectMapper();
    }

    @Test
    void saveClient() throws Exception {

        Client requestClient = Client.builder()
                .email("riley@example.com")
                .mobile(123456789)
                .firstName("riley")
                .lastName("mckinley")
                .build();

        Mockito.when(service.save(requestClient))
                .thenReturn(client);

        mockMvc.perform(post("/clients")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestClient)))
                .andExpect(jsonPath("$.clientId").value(1L));
    }

    @Test
    void updateClient() throws Exception {

        Client requestClient = Client.builder()
                .email("mckinley@example.com")
                .mobile(123456789)
                .firstName("riley")
                .lastName("mckinley")
                .build();

        Client responseClient = Client.builder()
                .email("mckinley@example.com")
                .mobile(123456789)
                .firstName("riley")
                .lastName("mckinley")
                .clientId(client.getClientId())
                .build();

        System.out.println(responseClient.toString());

        Mockito.when(service.updateClient(requestClient, client.getClientId()))
                .thenReturn(responseClient);

        mockMvc.perform(put("/clients/{id}", client.getClientId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestClient)))
                .andExpect(jsonPath("$.clientId").value(client.getClientId()));
    }
}
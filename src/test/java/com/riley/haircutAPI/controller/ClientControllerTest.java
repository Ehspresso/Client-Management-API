package com.riley.haircutAPI.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.riley.haircutAPI.ResponseObjects.ClientResponse;
import com.riley.haircutAPI.entity.Client;
import com.riley.haircutAPI.service.ClientService;
import org.assertj.core.util.Arrays;
import org.hamcrest.CoreMatchers;
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
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;

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

    @Autowired
    ObjectMapper objectMapper;

    Client client;

    @BeforeEach
    void setUp() {
        client = Client.builder()
                .email("riley@example.com")
                .mobile(123456789)
                .firstName("riley")
                .lastName("mckinley")
                .build();
    }

    @Test
    void testSaveClient_returnsClient() throws Exception {

        //assemble
        Mockito.when(service.save(client))
                .thenReturn(client);

        //act
        ResultActions response = mockMvc.perform(post("/api/clients")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(client)));

        //assert
        response.andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.email", CoreMatchers.is(client.getEmail())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.mobile", CoreMatchers.is(client.getMobile())));
    }

    @Test
    void testFetchAll_ReturnsListOfClients() throws Exception {

        //assemble
        List<Client> clients = new ArrayList<Client>();
        clients.add(client);
        ClientResponse clientResponse = ClientResponse.builder() //Must mock ClientResponse because Service layer uses it
                .last(true)
                .pageNo(1) //randomly chosen
                .pageSize(2) //randomly chosen
                .content(clients)
                .build();
        when(service.fetchAllClients(1, 2)).thenReturn(clientResponse); //Must match ClientResponse because Service layer is mocked

        //act
        ResultActions response = mockMvc.perform(get("/api/clients")
                .param("pageNo", "1")
                .param("pageSize", "2")
                .contentType(MediaType.APPLICATION_JSON));

        //assert
        response.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.content[0].email", CoreMatchers.is(client.getEmail())));
    }
}
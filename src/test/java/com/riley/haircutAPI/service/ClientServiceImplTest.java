package com.riley.haircutAPI.service;

import com.riley.haircutAPI.ResponseObjects.ClientResponse;
import com.riley.haircutAPI.entity.Client;
import com.riley.haircutAPI.repository.ClientRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

@SpringBootTest
class ClientServiceImplTest {

    @Mock
    private ClientRepository repository;

    @InjectMocks
    private ClientServiceImpl service;

    private Client client;

    @BeforeEach
    void setUp() {
        client = Client.builder()
                .email("riley@example.com")
                .mobile(123456789)
                .firstName("riley")
                .lastName("mckinley")
                .build();
    }

    @AfterEach
    void tearDown() {
        client = null;
    }

    @Test
    void testSave() {

        //assemble
        Mockito.when(repository.save(any(Client.class))).thenReturn(client);

        //act
        Client result = service.save(client);

        //assert
        Mockito.verify(repository, Mockito.times(1)).save(client);
        assertNotNull(result);

    }

    @Test
    void testFetchAll_returnsClientResponse() throws InstantiationException, IllegalAccessException {

        //assemble
        Page<Client> clients = Mockito.mock(Page.class);
        Mockito.when(repository.findAll(Mockito.any(Pageable.class))).thenReturn(clients);

        //act
        ClientResponse response = service.fetchAllClients(1, 10); //Randomly chosen params

        //assert
        Mockito.verify(repository, Mockito.times(1)).findAll(Mockito.any(Pageable.class));
        assertNotNull(response);

    }
}
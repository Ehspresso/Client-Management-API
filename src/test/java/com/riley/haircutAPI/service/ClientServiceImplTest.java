package com.riley.haircutAPI.service;

import com.riley.haircutAPI.entity.Client;
import com.riley.haircutAPI.repository.ClientRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.ArgumentMatchers.any;

@SpringBootTest
class ClientServiceImplTest {

    @MockBean
    private ClientRepository repository;

    @Autowired
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

        client.setClientId(1L);
        Mockito.when(repository.save(any())).thenReturn(client);
        Client result = service.save(client);

        Mockito.verify(repository, Mockito.times(1)).save(client);
        assertEquals(result.getClientId(), client.getClientId());

    }

    @Test
    void testUpdate() {

        client.setClientId(1L);
        Client updatedClient = new Client(
                client.getClientId(),
                client.getFirstName(),
                client.getLastName(),
                client.getMobile(),
                "newemail@example.com",
                client.getPassword(),
                client.getAppointment());
        Mockito.when(repository.save(any())).thenReturn(updatedClient);

        Client result = service.save(updatedClient);
        Mockito.verify(repository, Mockito.times(1)).save(any());
        assertEquals(result.getClientId(), client.getClientId());
        assertEquals(result.getEmail(), updatedClient.getEmail());
    }
}
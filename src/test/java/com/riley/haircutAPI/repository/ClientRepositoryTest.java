package com.riley.haircutAPI.repository;

import com.riley.haircutAPI.entity.Client;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
class ClientRepositoryTest {

    @Autowired
    private ClientRepository repository;

    @Test
    void testSave_inputClient_outputClientWithId() {

        //arrange
        Client client = Client.builder()
                .email("riley@example.com")
                .mobile(123456789)
                .firstName("riley")
                .lastName("mckinley")
                .build();

        //act
        Client savedClient = repository.save(client);

        //assert
        assertNotNull(savedClient);
        assertTrue(savedClient.getClientId() > 0);
    }

    @Test
    void testFindClientByEmail_inputClient_outputClient() {

        //arrange
        Client client = Client.builder()
                .email("riley@example.com")
                .mobile(123456789)
                .firstName("riley")
                .lastName("mckinley")
                .build();
        repository.save(client);

        //act
        Client response = repository.findClientByEmail("riley@example.com");

        //assert
        assertNotNull(response);
        assertTrue(response.getEmail().equals(client.getEmail()));
        assertTrue(response.getClientId() == client.getClientId());
    }
}
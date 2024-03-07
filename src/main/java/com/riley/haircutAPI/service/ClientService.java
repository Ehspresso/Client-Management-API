package com.riley.haircutAPI.service;

import com.riley.haircutAPI.entity.Client;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ClientService {
    List<Client> fetchAllClients();

    Client save(Client client);

    Client fetchClient(Long id);

    void deleteClient(Long id);

    Client updateClient(Client client, Long id);

    Client fetchClientByEmail(String email);
}

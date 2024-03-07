package com.riley.haircutAPI.service;

import com.riley.haircutAPI.entity.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.riley.haircutAPI.repository.ClientRepository;

import java.util.List;

@Service
public class ClientServiceImpl implements ClientService{

    @Autowired
    private ClientRepository clientRepository;

    @Override
    public List<Client> fetchAllClients() {
        return clientRepository.findAll();
    }

    @Override
    public Client save(Client client) {
        return clientRepository.save(client);
    }

    @Override
    public Client fetchClient(Long id) {
        return clientRepository.findById(id).get();
    }

    @Override
    public void deleteClient(Long id) {
        clientRepository.deleteById(id);
    }

    @Override
    public Client updateClient(Client client, Long id) {
        Client newClient = clientRepository.findById(id).get();

        newClient.setEmail(client.getEmail());
        newClient.setMobile(client.getMobile());
        newClient.setFirstName(client.getFirstName());
        newClient.setLastName(client.getLastName());
        newClient.setPassword(client.getPassword());

        return clientRepository.save(newClient);
    }

    @Override
    public Client fetchClientByEmail(String email) {
        return clientRepository.findClientByEmail(email);
    }
}

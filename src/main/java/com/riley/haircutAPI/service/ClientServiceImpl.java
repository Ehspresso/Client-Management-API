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
    //Requires error checking, ie. what to do if findById returns a null response because there is no client with id
    public Client updateClient(Client newClient, Long id) {
        Client client = clientRepository.findById(id).get();

        client.setEmail(newClient.getEmail());
        client.setMobile(newClient.getMobile());
        client.setFirstName(newClient.getFirstName());
        client.setLastName(newClient.getLastName());
        client.setPassword(newClient.getPassword());

        return clientRepository.save(newClient);
    }

    @Override
    public Client fetchClientByEmail(String email) {
        return clientRepository.findClientByEmail(email);
    }
}

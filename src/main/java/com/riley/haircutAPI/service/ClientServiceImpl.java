package com.riley.haircutAPI.service;

import com.riley.haircutAPI.ResponseObjects.ClientResponse;
import com.riley.haircutAPI.entity.Client;
import com.riley.haircutAPI.exception.ClientRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.riley.haircutAPI.repository.ClientRepository;
import java.util.List;

@Service
public class ClientServiceImpl implements ClientService{

    @Autowired
    private ClientRepository clientRepository;

    @Override
    public ClientResponse fetchAllClients(int pageNo, int pageSize) {

        Pageable pageable = PageRequest.of(pageNo, pageSize);
        Page<Client> clients = clientRepository.findAll(pageable);

        ClientResponse response = new ClientResponse();
        response.setContent(clients.getContent());
        response.setPageNo(clients.getNumber());
        response.setPageSize(clients.getSize());
        response.setTotalElements(clients.getTotalElements());
        response.setTotalPages(clients.getTotalPages());
        response.setLast(clients.isLast());

        return response;
    }

    @Override
    public Client save(Client client) {
        return clientRepository.save(client);
    }

    @Override
    public Client fetchClient(Long id) {
        Client client = clientRepository.findById(id).orElseThrow(() -> new ClientRequestException("Client could not be found!"));
        return client;
    }

    @Override
    public void deleteClient(Long id) {
        Client client = clientRepository.findById(id).orElseThrow(() -> new ClientRequestException("Client could not be deleted!"));
        clientRepository.deleteById(id);
    }

    @Override
    //Requires error checking, ie. what to do if findById returns a null response because there is no client with id
    public Client updateClient(Client newClient, Long id) {
        Client client = clientRepository.findById(id).orElseThrow(() -> new ClientRequestException("Client could not be updated!"));

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

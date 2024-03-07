package com.riley.haircutAPI.controller;

import com.riley.haircutAPI.entity.Client;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.riley.haircutAPI.service.ClientService;

import java.util.List;

@RestController
public class ClientController {

    @Autowired
    private ClientService clientService;

    private final Logger LOGGER = LoggerFactory.getLogger(ClientController.class);

    @GetMapping("/clients")
    public List<Client> fetchAllClients() {
        return clientService.fetchAllClients();
    }

    @PostMapping("/clients")
    public Client saveClient(@RequestBody Client client) {
        return clientService.save(client);
    }

    @GetMapping("/clients/{id}")
    public Client fetchClientById(@PathVariable("id") Long id) {
        return clientService.fetchClient(id);
    }

    @DeleteMapping("/clients/{id}")
    public String deleteClient(@PathVariable("id") Long id) {
        clientService.deleteClient(id);
        return "Client account deleted!";
    }

    @PutMapping("/clients/{id}")
    public Client updateClient(@RequestBody Client client, @PathVariable("id") Long id) {
        return clientService.updateClient(client, id);
    }

    @GetMapping("/clients/email/{email}")
    public Client fetchClientByEmail(@PathVariable String email) {
        return clientService.fetchClientByEmail(email);
    }

}

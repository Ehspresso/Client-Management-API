package com.riley.haircutAPI.controller;

import com.riley.haircutAPI.entity.Client;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.riley.haircutAPI.service.ClientService;

import java.util.List;

@RestController
public class ClientController {

    @Autowired
    private ClientService clientService;

    private final Logger LOGGER = LoggerFactory.getLogger(ClientController.class);

    @GetMapping("/clients")
    public ResponseEntity<List<Client>> fetchAllClients() {

        return ResponseEntity.ok(clientService.fetchAllClients());
    }

    @PostMapping("/clients")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Client> saveClient(@RequestBody Client client) {
        return new ResponseEntity<>(clientService.save(client), HttpStatus.CREATED);
    }

    @GetMapping("/clients/{id}")
    public ResponseEntity<Client> fetchClientById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(clientService.fetchClient(id));
    }

    @DeleteMapping("/clients/{id}")
    public String deleteClient(@PathVariable("id") Long id) {
        clientService.deleteClient(id);
        return "Client account deleted!";
    }

    @PutMapping("/clients/{id}")
    public ResponseEntity<Client> updateClient(@RequestBody Client client, @PathVariable("id") Long id) {
        return ResponseEntity.ok(clientService.updateClient(client, id));
    }

    @GetMapping("/clients/email/{email}")
    public ResponseEntity<Client> fetchClientByEmail(@PathVariable String email) {
        return ResponseEntity.ok(clientService.fetchClientByEmail(email));
    }

}

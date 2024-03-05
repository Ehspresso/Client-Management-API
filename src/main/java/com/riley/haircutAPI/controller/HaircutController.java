package com.riley.haircutAPI.controller;

import com.riley.haircutAPI.entity.Client;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.riley.haircutAPI.service.ClientService;

import java.util.List;

@RestController
public class HaircutController {

    @Autowired
    private ClientService clientService;

    private final Logger LOGGER = LoggerFactory.getLogger(HaircutController.class);

    @PostMapping("/client")
    public Client saveClient(@RequestBody Client client) {
        return clientService.save(client);
    }

    @GetMapping("/clients")
    public List<Client> fetchAllClients() {
        return clientService.fetchAllClients();
    }
}

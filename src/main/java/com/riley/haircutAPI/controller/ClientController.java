package com.riley.haircutAPI.controller;

import com.riley.haircutAPI.ResponseObjects.ClientResponse;
import com.riley.haircutAPI.entity.Client;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.riley.haircutAPI.service.ClientService;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ClientController {

    @Autowired
    private ClientService clientService;

    private final Logger LOGGER = LoggerFactory.getLogger(ClientController.class);

    @GetMapping("/clients")
    @Operation(summary = "Fetch all clients")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved all clients"),
            @ApiResponse(responseCode = "400", description = "Unsuccessfully retrieved all clients")
    })
    public ResponseEntity<ClientResponse> fetchAllClients(
            @RequestParam(value = "pageNo", defaultValue = "0", required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize
    ) {

        return new ResponseEntity<>(clientService.fetchAllClients(pageNo, pageSize), HttpStatus.OK);
    }

    @PostMapping("/clients")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Create a new client")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully created new client"),
            @ApiResponse(responseCode = "400", description = "Unsuccessfully created new client")
    })
    public ResponseEntity<Client> saveClient(@RequestBody Client client) {
        return new ResponseEntity<>(clientService.save(client), HttpStatus.CREATED);
    }

    @GetMapping("/clients/{id}")
    @Operation(summary = "Fetch client by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved client"),
            @ApiResponse(responseCode = "400", description = "Unsuccessfully retrieved client")
    })
    public ResponseEntity<Client> fetchClientById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(clientService.fetchClient(id));
    }

    @DeleteMapping("/clients/{id}")
    @Operation(summary = "Delete client by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully deleted client"),
            @ApiResponse(responseCode = "400", description = "Unsuccessfully deleted client")
    })
    public String deleteClient(@PathVariable("id") Long id) {
        clientService.deleteClient(id);
        return "Client account deleted!";
    }

    @PutMapping("/clients/{id}")
    @Operation(summary = "Update client by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully updated client"),
            @ApiResponse(responseCode = "400", description = "Unsuccessfully updated client")
    })
    public ResponseEntity<Client> updateClient(@RequestBody Client client, @PathVariable("id") Long id) {
        return ResponseEntity.ok(clientService.updateClient(client, id));
    }

    @GetMapping("/clients/email/{email}")
    @Operation(summary = "Retrieve client by email")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved client"),
            @ApiResponse(responseCode = "400", description = "Unsuccessfully retrieved client")
    })
    public ResponseEntity<Client> fetchClientByEmail(@PathVariable String email) {
        return ResponseEntity.ok(clientService.fetchClientByEmail(email));
    }

}

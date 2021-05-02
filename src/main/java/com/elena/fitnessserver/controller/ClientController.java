package com.elena.fitnessserver.controller;

import com.elena.fitnessserver.exceptions.ResourceNotFoundException;
import com.elena.fitnessserver.models.Client;
import com.elena.fitnessserver.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ClientController {

    @Autowired
    private final ClientRepository clientRepository;

    public ClientController(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @PostMapping(value = "/clients")
    public Client create(@RequestBody Client client) {
        return clientRepository.save(client);
    }


    @GetMapping(value = "/clients")
    public ResponseEntity<List<Client>> read() {
        List<Client> people = clientRepository.findAll();
        return !people.isEmpty()
                ? new ResponseEntity<>(people, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/clients/{id}")
    public ResponseEntity<Client> read(@PathVariable(name = "id") Long id) {
        Client client = clientRepository.findById(id).orElse(null);

        return client != null
                ? new ResponseEntity<>(client, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping(value = "clients/{id}")
    public Client update(@PathVariable(name = "id") Long clientId, @RequestBody Client clientReq) {
        return clientRepository.findById(clientId).map(
                client -> {
                    client = clientReq;
                    return clientRepository.save(client);
                }
        ).orElseThrow(() -> new ResourceNotFoundException("Client not found with id" + clientId));
    }

    @DeleteMapping(value = "/clients/{id}")
    public ResponseEntity<?> delete(@PathVariable(name = "id") Long clientId) {
        return clientRepository.findById(clientId)
                .map(client -> {
                    clientRepository.delete(client);
                    return ResponseEntity.ok().build();
                }).orElseThrow(() -> new ResourceNotFoundException("Client not found with id" + clientId));
    }

}

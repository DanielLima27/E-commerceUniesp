package com.brasileiras.ecommerce.controller;

import com.brasileiras.ecommerce.model.ClientModel;
import com.brasileiras.ecommerce.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clients")
public class ClientController {

    @Autowired
    private ClientRepository clientRepository;


    @GetMapping
    public List<ClientModel> getAllClients() {
        return clientRepository.findAll();
    }


    @GetMapping("/{id}")
    public ResponseEntity<ClientModel> getClientById(@PathVariable Long id) {
        return clientRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }


    @PostMapping
    public ResponseEntity<ClientModel> createClient(@RequestBody ClientModel client) {
        ClientModel savedClient = clientRepository.save(client);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedClient);
    }


    @PutMapping("/{id}")
    public ResponseEntity<ClientModel> updateClient(@PathVariable Long id, @RequestBody ClientModel client) {
        return clientRepository.findById(id)
                .map(existingClient -> {
                    existingClient.setName(client.getName());
                    existingClient.setCpf(client.getCpf());
                    existingClient.setEmail(client.getEmail());
                    existingClient.setPhone(client.getPhone());
                    existingClient.setAddress(client.getAddress());

                    ClientModel updatedClient = clientRepository.save(existingClient);
                    return ResponseEntity.ok(updatedClient);
                })
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClient(@PathVariable Long id) {
        if (clientRepository.existsById(id)) {
            clientRepository.deleteById(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
}

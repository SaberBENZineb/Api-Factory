package org.factory.apifactory.controller;

import jakarta.validation.Valid;
import org.factory.apifactory.request.Client.ClientCreateRequest;
import org.factory.apifactory.dto.Client.ClientDTO;
import org.factory.apifactory.request.Client.ClientUpdateRequest;
import org.factory.apifactory.service.ClientService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/clients")
public class ClientController {

    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @PostMapping
    public ResponseEntity<ClientDTO> createClient(@Valid @RequestBody ClientCreateRequest request) {
        ClientDTO client = clientService.createClient(request);
        return ResponseEntity.ok(client);
    }

    @GetMapping( "/{clientId}")
    public ResponseEntity<ClientDTO> getClient(@PathVariable Long clientId) {
        ClientDTO client = clientService.getClient(clientId);
        return ResponseEntity.ok(client);
    }

    @PutMapping("/{clientId}")
    public ResponseEntity<ClientDTO> updateClient(@PathVariable Long clientId, @Valid @RequestBody ClientUpdateRequest request) {
        return ResponseEntity.ok(clientService.updateClient(clientId, request));
    }

    @DeleteMapping("/{clientId}")
    public ResponseEntity<Map<String, String>> deleteClient(@PathVariable Long clientId) {
        clientService.deleteClient(clientId);
        Map<String, String> response = Map.of("message", "Client deleted successfully");
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{clientId}/activate")
    public ResponseEntity<ClientDTO> activateClient(@PathVariable Long clientId) {
        return ResponseEntity.ok(clientService.activateClient(clientId));
    }
}

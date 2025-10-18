package org.factory.apifactory.service;

import jakarta.transaction.Transactional;
import org.factory.apifactory.exception.BadRequestException;
import org.factory.apifactory.exception.NotFoundException;
import org.factory.apifactory.mapper.Client.ClientDTOFactoryService;
import org.factory.apifactory.mapper.Client.ClientFactoryService;
import org.factory.apifactory.request.Client.ClientCreateRequest;
import org.factory.apifactory.dto.Client.ClientDTO;
import org.factory.apifactory.model.Client.Client;
import org.factory.apifactory.repository.ClientRepository;
import org.factory.apifactory.request.Client.ClientUpdateRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@Transactional
public class ClientService {
    private final ClientRepository clientRepository;
    private final ClientFactoryService clientFactoryService;
    private final ClientDTOFactoryService clientDTOFactoryService;

    public ClientService(ClientFactoryService clientFactoryService, ClientDTOFactoryService clientDTOFactoryService,ClientRepository clientRepository) {
        this.clientFactoryService = clientFactoryService;
        this.clientDTOFactoryService = clientDTOFactoryService;
        this.clientRepository = clientRepository;
    }

    public ClientDTO createClient(ClientCreateRequest request) {
        if (clientRepository.existsByEmail(request.getEmail())){
            throw new BadRequestException(String.format("Client %s already exist", request.getEmail()));
        }
        Client client = clientFactoryService.createClient(request);
        Client savedClient = clientRepository.save(client);
        return clientDTOFactoryService.createClientDTO(savedClient);
    }

    public ClientDTO getClient(Long clientId) {
        Client client = clientRepository.findById(clientId).orElseThrow(() -> new NotFoundException(String.format("Client %d not found", clientId)));
        return clientDTOFactoryService.createClientDTO(client);
    }

    public ClientDTO updateClient(Long clientId, ClientUpdateRequest updateRequest) {
        Client existingClient = clientRepository.findByIdAndActiveTrue(clientId).orElseThrow(() -> new NotFoundException(String.format("Client %d not found or not active", clientId)));

        // Update the client using factory
        clientFactoryService.updateClient(existingClient, updateRequest);

        // Save updated client
        Client updatedClient = clientRepository.save(existingClient);

        // Convert to DTO and return
        return clientDTOFactoryService.createClientDTO(updatedClient);
    }

    public void deleteClient(Long clientId) {
        Client client = clientRepository.findByIdAndActiveTrue(clientId).orElseThrow(() -> new NotFoundException(String.format("Client %d not found or not active", clientId)));
        //soft client delete
        client.setActive(false);
        client.getContracts().forEach(contract -> {
            if (contract.getEndDate() == null) {
                contract.setEndDate(LocalDate.now());
            }
        });
        clientRepository.save(client);
    }

    public ClientDTO activateClient(Long clientId) {
        Client client = clientRepository.findById(clientId).orElseThrow(() -> new NotFoundException(String.format("Client %d not found or not active", clientId)));
        client.setActive(true);
        Client savedClient=clientRepository.save(client);
        return clientDTOFactoryService.createClientDTO(savedClient);
    }
}

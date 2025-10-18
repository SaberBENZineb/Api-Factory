package org.factory.apifactory.mapper.Client;

import org.factory.apifactory.dto.Client.ClientDTO;
import org.factory.apifactory.dto.Client.ClientDTOFactory;
import org.factory.apifactory.model.Client.Client;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientDTOFactoryService {
    private final List<ClientDTOFactory> factories;

    public ClientDTOFactoryService(List<ClientDTOFactory> factories) {
        this.factories = factories;
    }

    public ClientDTO createClientDTO(Client client) {
        return factories.stream()
                .filter(factory -> factory.supports(client.getClass()))
                .findFirst()
                .map(factory -> factory.createClientDTO(client))
                .orElseThrow(() -> new IllegalArgumentException("Unsupported client type: " + client.getClass().getSimpleName()));
    }
}

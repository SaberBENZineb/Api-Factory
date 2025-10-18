package org.factory.apifactory.mapper.Client;

import org.factory.apifactory.request.Client.ClientCreateRequest;
import org.factory.apifactory.model.Client.Client;
import org.factory.apifactory.model.Client.ClientFactory;
import org.factory.apifactory.request.Client.ClientUpdateRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientFactoryService {
    private final List<ClientFactory> factories;

    public ClientFactoryService(List<ClientFactory> factories) {
        this.factories = factories;
    }

    public Client createClient(ClientCreateRequest request) {
        return factories.stream()
                .filter(factory -> factory.supports(request.getType()))
                .findFirst()
                .map(factory -> factory.createClient(request))
                .orElseThrow(() -> new IllegalArgumentException("Unknown client type: " + request.getType()));
    }

    public void updateClient(Client existingClient, ClientUpdateRequest request) {
        factories.stream()
                .filter(factory -> factory.supports(existingClient.getClass().getSimpleName()))
                .findFirst()
                .ifPresentOrElse(
                        factory -> factory.updateClient(existingClient, request),
                        () -> { throw new IllegalArgumentException("Unknown client type: " + existingClient.getClass().getSimpleName()); }
                );
    }
}

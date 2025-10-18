package org.factory.apifactory.model.Client;

import org.factory.apifactory.request.Client.ClientCreateRequest;
import org.factory.apifactory.request.Client.ClientUpdateRequest;

public interface ClientFactory {
    Client createClient(ClientCreateRequest request);
    boolean supports(String clientType);
    void updateClient(Client existingClient, ClientUpdateRequest request);
}

package org.factory.apifactory.dto.Client;

import org.factory.apifactory.model.Client.Client;

public interface ClientDTOFactory {
    ClientDTO createClientDTO(Client client);
    boolean supports(Class<? extends Client> clientClass);
}

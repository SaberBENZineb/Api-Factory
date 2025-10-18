package org.factory.apifactory.model.Client;

import org.factory.apifactory.request.Client.ClientCreateRequest;
import org.factory.apifactory.enums.ClientType;
import org.factory.apifactory.request.Client.ClientUpdateRequest;
import org.springframework.stereotype.Component;

@Component
public class PersonFactory implements ClientFactory {

    @Override
    public Client createClient(ClientCreateRequest request) {
        return Person.builder()
                .email(request.getEmail())
                .phone(request.getPhone())
                .name(request.getName())
                .birthDate(request.getBirthDate())
                .active(true)
                .build();
    }

    @Override
    public void updateClient(Client existingClient, ClientUpdateRequest request) {
        Person person = (Person) existingClient;
        person.setEmail(request.getEmail());
        person.setPhone(request.getPhone());
        person.setName(request.getName());
    }

    @Override
    public boolean supports(String clientType) {
        return ClientType.PERSON.name().equalsIgnoreCase(clientType);
    }
}

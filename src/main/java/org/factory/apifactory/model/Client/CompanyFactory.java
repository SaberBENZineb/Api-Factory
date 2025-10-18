package org.factory.apifactory.model.Client;

import org.factory.apifactory.request.Client.ClientCreateRequest;
import org.factory.apifactory.enums.ClientType;
import org.factory.apifactory.request.Client.ClientUpdateRequest;
import org.springframework.stereotype.Component;

@Component
public class CompanyFactory implements ClientFactory {

    @Override
    public Client createClient(ClientCreateRequest request) {
        return Company.builder()
                .email(request.getEmail())
                .phone(request.getPhone())
                .name(request.getName())
                .companyIdentifier(request.getCompanyIdentifier())
                .active(true)
                .build();
    }

    @Override
    public boolean supports(String clientType) {
        return ClientType.COMPANY.name().equalsIgnoreCase(clientType);
    }

    @Override
    public void updateClient(Client existingClient, ClientUpdateRequest request) {
        Company company = (Company) existingClient;
        company.setEmail(request.getEmail());
        company.setPhone(request.getPhone());
        company.setName(request.getName());
    }
}
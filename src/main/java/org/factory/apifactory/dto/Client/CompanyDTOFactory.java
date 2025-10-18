package org.factory.apifactory.dto.Client;

import org.factory.apifactory.mapper.Contract.ContractMapper;
import org.factory.apifactory.model.Client.Client;
import org.factory.apifactory.model.Client.Company;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class CompanyDTOFactory implements ClientDTOFactory {
    private final  ContractMapper contractMapper;

    public CompanyDTOFactory(ContractMapper contractMapper) {
        this.contractMapper = contractMapper;
    }

    @Override
    public ClientDTO createClientDTO(Client client) {
        Company company = (Company) client;
        return CompanyDTO.builder()
                .id(company.getId())
                .email(company.getEmail())
                .phone(company.getPhone())
                .name(company.getName())
                .companyIdentifier(company.getCompanyIdentifier())
                .contracts(company.getContracts().stream()
                        .map(contractMapper::toContractDTO)
                        .collect(Collectors.toList()))
                .active(company.isActive())
                .build();
    }

    @Override
    public boolean supports(Class<? extends Client> clientClass) {
        return Company.class.isAssignableFrom(clientClass);
    }
}

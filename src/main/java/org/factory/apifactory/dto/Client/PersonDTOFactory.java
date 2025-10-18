package org.factory.apifactory.dto.Client;

import org.factory.apifactory.mapper.Contract.ContractMapper;
import org.factory.apifactory.model.Client.Client;
import org.factory.apifactory.model.Client.Person;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class PersonDTOFactory implements ClientDTOFactory {
    private final ContractMapper contractMapper;

    public PersonDTOFactory(ContractMapper contractMapper) {
        this.contractMapper = contractMapper;
    }

    @Override
    public ClientDTO createClientDTO(Client client) {
        Person person = (Person) client;
        return PersonDTO.builder()
                .id(person.getId())
                .email(person.getEmail())
                .phone(person.getPhone())
                .name(person.getName())
                .birthdate(person.getBirthDate())
                .contracts(person.getContracts().stream()
                        .map(contractMapper::toContractDTO)
                        .collect(Collectors.toList()))
                .active(person.isActive())
                .build();
    }

    @Override
    public boolean supports(Class<? extends Client> clientClass) {
        return Person.class.isAssignableFrom(clientClass);
    }
}

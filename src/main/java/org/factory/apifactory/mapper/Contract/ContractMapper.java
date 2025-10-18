package org.factory.apifactory.mapper.Contract;

import org.factory.apifactory.dto.Contract.ContractDTO;
import org.factory.apifactory.model.Client.Client;
import org.factory.apifactory.model.Contract.Contract;
import org.factory.apifactory.request.Contract.ContractCreateRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class ContractMapper {

    public Contract toContract(Client client,ContractCreateRequest request) {
        return Contract.builder()
                .client(client)
                .startDate(request.getStartDate() != null ? request.getStartDate() : LocalDate.now())
                .endDate(request.getEndDate())
                .costAmount(request.getCostAmount())
                .updateDate(LocalDate.now())
                .build();
    }

    public ContractDTO toContractDTO(Contract contract) {
        return ContractDTO.builder()
                .id(contract.getId())
                .costAmount(contract.getCostAmount())
                .startDate(contract.getStartDate())
                .endDate(contract.getEndDate())
                .updateDate(contract.getUpdateDate())
                .build();
    }
}

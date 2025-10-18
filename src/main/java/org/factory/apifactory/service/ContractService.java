package org.factory.apifactory.service;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.factory.apifactory.dto.Contract.ContractDTO;
import org.factory.apifactory.exception.NotFoundException;
import org.factory.apifactory.mapper.Contract.ContractMapper;
import org.factory.apifactory.model.Client.Client;
import org.factory.apifactory.model.Contract.Contract;
import org.factory.apifactory.repository.ClientRepository;
import org.factory.apifactory.repository.ContractRepository;
import org.factory.apifactory.request.Contract.ContractCreateRequest;
import org.factory.apifactory.request.Contract.ContractUpdateRequest;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Service
@Transactional
public class ContractService {
    private final ContractMapper contractMapper;
    private final ContractRepository contractRepository;
    private final ClientRepository clientRepository;

    public ContractService(ContractMapper contractMapper, ContractRepository contractRepository, ClientRepository clientRepository) {
        this.contractMapper = contractMapper;
        this.contractRepository = contractRepository;
        this.clientRepository = clientRepository;
    }

    public ContractDTO createContract(ContractCreateRequest request) {
        Client client = clientRepository.findByIdAndActiveTrue(request.getClientId())
                .orElseThrow(() -> new NotFoundException("Client not found or not active with id: " + request.getClientId()));
        Contract contract = contractMapper.toContract(client,request);
        Contract savedContract=contractRepository.save(contract);
        client.getContracts().add(savedContract);
        return contractMapper.toContractDTO(savedContract);
    }

    public ContractDTO updateContract(Long contractId, @Valid ContractUpdateRequest contractUpdateRequest) {
        Contract contract= contractRepository.findById(contractId).orElseThrow(() -> new NotFoundException(String.format("Contact %d not found", contractId)));
        contract.setCostAmount(contractUpdateRequest.getCostAmount());
        Contract savedContract=contractRepository.save(contract);
        return contractMapper.toContractDTO(savedContract);
    }

    public List<ContractDTO> getActiveContractsForClient(Long clientId) {
        clientRepository.findByIdAndActiveTrue(clientId)
                .orElseThrow(() -> new NotFoundException("Client not found or not active with id: " + clientId));
        List<Contract> contracts=contractRepository.findByClientIdAndActive(clientId, LocalDate.now());
        return contracts.stream().map(contractMapper::toContractDTO).toList();
    }

    public BigDecimal getActiveContractsTotalCost(Long clientId) {
        clientRepository.findByIdAndActiveTrue(clientId)
                .orElseThrow(() -> new NotFoundException("Client not found or not active with id: " + clientId));
        return contractRepository.sumActiveContractsCostByClientId(clientId, LocalDate.now());
    }
}

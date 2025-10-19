package org.factory.apifactory.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import org.factory.apifactory.dto.Contract.ContractDTO;
import org.factory.apifactory.request.Contract.ContractCreateRequest;
import org.factory.apifactory.service.ContractService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/contacts")
public class ContractController {
    private final ContractService contractService;

    public ContractController(ContractService contractService) {
        this.contractService = contractService;
    }

    @PostMapping
    public ResponseEntity<ContractDTO> createClient(@Valid @RequestBody ContractCreateRequest request) {
        ContractDTO contract = contractService.createContract(request);
        return ResponseEntity.ok(contract);
    }

    @PutMapping("/{contractId}")
    public ResponseEntity<ContractDTO> updateContract(@PathVariable Long contractId, @Valid @Positive(message = "Cost amount must be positive") @RequestParam BigDecimal costAmount) {
        return ResponseEntity.ok(contractService.updateContract(contractId, costAmount));
    }

    @GetMapping("/{clientId}/active-contracts")
    public ResponseEntity<List<ContractDTO>> getActiveContractsForClient(@PathVariable Long clientId, @RequestParam(required = false) @Valid @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate updatedAt) {
        return ResponseEntity.ok(contractService.getActiveContractsForClient(clientId,updatedAt));
    }

    @GetMapping("/{clientId}/active-contracts/total-cost")
    public ResponseEntity<Map<String, BigDecimal>> getActiveContractsTotalCost(@PathVariable Long clientId) {
        BigDecimal totalCost = contractService.getActiveContractsTotalCost(clientId);
        Map<String, BigDecimal> response = Map.of("totalCost", totalCost);
        return ResponseEntity.ok(response);
    }

}

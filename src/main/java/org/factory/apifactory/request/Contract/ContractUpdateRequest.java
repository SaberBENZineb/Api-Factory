package org.factory.apifactory.request.Contract;

import jakarta.validation.constraints.Positive;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class ContractUpdateRequest {
    @Positive(message = "Cost amount must be positive")
    private BigDecimal costAmount;
}

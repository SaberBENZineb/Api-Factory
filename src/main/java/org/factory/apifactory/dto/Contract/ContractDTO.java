package org.factory.apifactory.dto.Contract;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ContractDTO {
    private Long id;
    private LocalDate startDate;
    private LocalDate endDate;
    private BigDecimal costAmount;
    private LocalDate updateDate;
}

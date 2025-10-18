package org.factory.apifactory.repository;

import org.factory.apifactory.model.Contract.Contract;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public interface ContractRepository extends JpaRepository<Contract, Long> {

    @Query("SELECT c FROM Contract c " +
            "WHERE c.client.id = :clientId AND c.endDate > :currentDate")
    List<Contract> findByClientIdAndActive(@Param("clientId") Long clientId,@Param("currentDate") LocalDate now);

    @Query("SELECT COALESCE(SUM(c.costAmount), 0) FROM Contract c " +
            "WHERE c.client.id = :clientId AND c.endDate > :currentDate")
    BigDecimal sumActiveContractsCostByClientId(@Param("clientId") Long clientId,
                                                @Param("currentDate") LocalDate currentDate);
}

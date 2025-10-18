package org.factory.apifactory.dto.Client;

import lombok.*;
import lombok.experimental.SuperBuilder;
import org.factory.apifactory.dto.Contract.ContractDTO;
import java.util.List;

@Setter
@Getter
@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class ClientDTO {
    private Long id;
    private String name;
    private String phone;
    private String email;
    private List<ContractDTO> contracts;
    private boolean active;
}

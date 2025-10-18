package org.factory.apifactory.dto.Client;

import lombok.*;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = true)
@Setter
@Getter
@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class CompanyDTO extends ClientDTO {
    String companyIdentifier;
}

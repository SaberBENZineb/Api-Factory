package org.factory.apifactory.dto.Client;

import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;

@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class PersonDTO extends ClientDTO {
    private LocalDate birthdate;
}


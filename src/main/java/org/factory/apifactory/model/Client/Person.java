package org.factory.apifactory.model.Client;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@SuperBuilder
@Table(name = "person")
@NoArgsConstructor
public class Person extends Client {
    @Column(name = "birth_date")
    private LocalDate birthDate;
}

package org.factory.apifactory.request.Client;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ClientCreateRequest {
    @NotBlank(message = "Type is required")
    @Pattern(regexp = "PERSON|COMPANY", message = "Type must be either PERSON or COMPANY")
    private String type; // "PERSON" or "COMPANY"
    private String name;
    @Email(message = "Email must be valid")
    @NotBlank(message = "Email is required")
    private String email;

    @Pattern(regexp = "^\\+?[\\d\\s-()]{10,}$", message = "Phone number must be valid")
    private String phone;

    // For PERSON
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate birthDate;

    // For COMPANY
    @Pattern(regexp = "^[a-zA-Z]{3}-\\d{3}$", message = "Company identifier must be in the format: aaa-123")
    private String companyIdentifier;
}
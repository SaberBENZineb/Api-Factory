package org.factory.apifactory.request.Client;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ClientUpdateRequest {
    private String name;
    @Email(message = "Email must be valid")
    private String email;
    @Pattern(regexp = "^\\+?[\\d\\s-()]{10,}$", message = "Phone number must be valid")
    private String phone;
}

package com.example.customer.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class CustomerDto {

    private Long id;

    @NotNull
    @Size(min = 3, max = 50, message = "Name must be between 3 and 50 characters")
    private String name;

    @NotNull
    @Size(min = 3, max = 50, message = "Surname must be between 3 and 50 characters")
    private String surname;

    @NotNull
    @Email(message = "Email is not valid")
    private String email;

    @NotNull
    @Pattern(regexp = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$", message = "Password must be at least 8 characters and contain at least one uppercase, one lowercase, one digit and one special character")
    @Size(min = 3, max = 50, message = "Password must be between 3 and 50 characters")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

}
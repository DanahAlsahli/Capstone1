package org.example.capstone1.Model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class User {

    @NotNull(message = "id can not be null")
    private Integer id;

    @NotNull(message = "username can not be null")
    @Size(min = 6, message = "username must be more than 5 characters")
    private String username;

    @NotNull(message = "password can not be null")
    @Size(min = 7, message = "password must be more than 6 characters")
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d).+$",
            message = "password must contain characters and digits")
    private String password;

    @NotNull(message = "email can not be null")
    @Email(message = "email must be valid")
    private String email;

    @NotNull(message = "role can not be null")
    @Pattern(regexp = "Admin|Customer",
            message = "role must be Admin or Customer")
    private String role;

    @NotNull(message = "balance can not be null")
    @Min(value = 1, message = "balance must be positive")
    private Double balance;
}



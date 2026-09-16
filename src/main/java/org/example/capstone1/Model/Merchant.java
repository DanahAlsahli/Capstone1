package org.example.capstone1.Model;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Merchant {
    @NotNull(message = "ID must not be empty")
    private Integer id;

    @NotNull(message = "Name must not be empty")
    @Size(min = 3, message = "Name must be more than 3 characters")
    private String name;
}

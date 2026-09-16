package org.example.capstone1.Model;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Category {
    @NotNull(message = "ID can not be null")
    private Integer id;

    @NotNull(message = "Name can not be null")
    @Size(min = 4, message = "Name must be more than 3 characters")
    private String name;
}

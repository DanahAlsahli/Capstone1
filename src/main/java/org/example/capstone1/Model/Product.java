package org.example.capstone1.Model;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Product {
    @NotNull(message = "Id can not be null")
    private Integer id;

    @NotNull(message = "Name van not be null")
    @Size(min = 4, message= "Name must be more than 3 characters")
    private String name;

    @NotNull(message = "Price can not be null")
    @DecimalMin(value = "0.0", inclusive = false, message = "price must be positive")
    private Double price;

    @NotNull(message = "Category ID can not be null")
    private Integer categoryID;
}

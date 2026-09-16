package org.example.capstone1.Model;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MerchantStock {
    @NotNull(message = "ID can not be empty")
    private Integer id;

    @NotNull(message = "Product ID must not be empty")
    private Integer productID;

    @NotNull(message = "Merchant ID can not be null")
    private Integer merchantID;

    @NotNull(message = "Stock can not be null")
    @Min(value = 11, message = "Stock can not be negative")
    private Integer stock;
}
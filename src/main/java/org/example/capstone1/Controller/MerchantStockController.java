package org.example.capstone1.Controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.capstone1.Api.ApiResponse;
import org.example.capstone1.Model.MerchantStock;
import org.example.capstone1.Service.MerchantStockService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/merchant-stock")
@RequiredArgsConstructor
public class MerchantStockController {

    private final MerchantStockService merchantStockService;

    @GetMapping("/get")
    public ResponseEntity<?> getMerchantStocks() {
        return ResponseEntity.status(200).body(merchantStockService.getMerchantStocks());
    }

    @PostMapping("/add")
    public ResponseEntity<?> addMerchantStock(@RequestBody @Valid MerchantStock merchantStock) {
        merchantStockService.addMerchantStock(merchantStock);
        return ResponseEntity.status(200).body(new ApiResponse("merchant stock added"));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateMerchantStock(
            @PathVariable int id,
            @RequestBody @Valid MerchantStock merchantStock) {

        boolean updated = merchantStockService.updateMerchantStock(id, merchantStock);

        if (updated) {
            return ResponseEntity.status(200).body(new ApiResponse("merchant stock updated"));
        }

        return ResponseEntity.status(400).body(new ApiResponse("merchant stock not found"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteMerchantStock(@PathVariable int id) {

        boolean deleted = merchantStockService.deleteMerchantStock(id);

        if (deleted) {
            return ResponseEntity.status(200).body(new ApiResponse("merchant stock deleted"));
        }

        return ResponseEntity.status(400).body(new ApiResponse("merchant stock not found"));
    }

    @PutMapping("/add-stock/{productId}/{merchantId}/{amount}")
    public ResponseEntity<?> addStock(
            @PathVariable int productId,
            @PathVariable int merchantId,
            @PathVariable int amount) {

        boolean added = merchantStockService.addStock(productId, merchantId, amount);

        if (added) {
            return ResponseEntity.status(200).body(new ApiResponse("stock added"));
        }

        return ResponseEntity.status(400).body(new ApiResponse("merchant stock not found"));
    }

    @GetMapping("/merchant/{merchantId}")
    public ResponseEntity<?> getMerchantStocksByMerchantId(@PathVariable int merchantId) {

        return ResponseEntity.status(200)
                .body(merchantStockService.getMerchantStocksByMerchantId(merchantId));
    }

    @GetMapping("/low-stock")
    public ResponseEntity<?> getLowStock() {

        return ResponseEntity.status(200)
                .body(merchantStockService.getLowStock());
    }
}

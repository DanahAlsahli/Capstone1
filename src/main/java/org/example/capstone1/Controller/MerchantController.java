package org.example.capstone1.Controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.capstone1.Api.ApiResponse;
import org.example.capstone1.Model.Merchant;
import org.example.capstone1.Service.MerchantService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/merchant")
@RequiredArgsConstructor
public class MerchantController {
    private final MerchantService merchantService;

    @GetMapping("/get")
    public ResponseEntity<?> getMerchants(){
        return ResponseEntity.status(200).body(merchantService.getMerchants());
    }

    @PostMapping("/add")
    public ResponseEntity<?> addMerchant(@RequestBody @Valid Merchant merchant){
        merchantService.addMerchant(merchant);
        return ResponseEntity.status(200).body(new ApiResponse("merchant added"));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateMerchant(@PathVariable int id, @RequestBody @Valid Merchant merchant){
        boolean updated = merchantService.updateMerchant(id, merchant);
        if(updated){
            return ResponseEntity.status(200).body(new ApiResponse("Merchant updated"));
        }
        return ResponseEntity.status(400).body(new ApiResponse("merchant not found"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteMerchant(@PathVariable int id){
        boolean deleted = merchantService.deleteMerchant(id);
        if(deleted){
            return ResponseEntity.status(200).body(new ApiResponse("merchant deleted"));
        }
        return ResponseEntity.status(400).body(new ApiResponse("merchant not found"));
    }
}

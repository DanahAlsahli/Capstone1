package org.example.capstone1.Controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.capstone1.Api.ApiResponse;
import org.example.capstone1.Model.Product;
import org.example.capstone1.Service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/product")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @GetMapping("/get")
    public ResponseEntity<?> getProducts(){
        if(productService.getProducts().isEmpty()){
            return ResponseEntity.status(400).body(new ApiResponse("No products found"));
        }
        return ResponseEntity.status(200)
                .body(productService.getProducts());
    }

    @PostMapping("/add")
    public ResponseEntity<?> addProduct(@RequestBody @Valid Product product){
        boolean added = productService.addProduct(product);
        if(added){
            return ResponseEntity.status(200).body(new ApiResponse("product added"));
        }
        return ResponseEntity.status(400).body(new ApiResponse("product can not be added"));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateProduct(@PathVariable int id, @RequestBody @Valid Product product){
        boolean update = productService.updateProduct(id, product);
        if(update){
            return ResponseEntity.status(200).body(new ApiResponse("Product updated"));
        }
        return ResponseEntity.status(400).body(new ApiResponse("Product not found"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable int id){
        boolean deleted = productService.deleteProduct(id);

        if(deleted){
            return ResponseEntity.status(200).body(new ApiResponse("Product deleted"));
        }
        return ResponseEntity.status(400).body(new ApiResponse("Product not found"));
    }

    @PutMapping("/update-price/{productId}/{merchantId}/{newPrice}")
    public ResponseEntity<?> updatePrice(@PathVariable int productId, @PathVariable int merchantId, @PathVariable double newPrice) {
        String result = productService.updatePrice(productId, merchantId, newPrice);
        if(result.equals("price updated")){
            return ResponseEntity.status(200)
                    .body(new ApiResponse(result));
        }
        return ResponseEntity.status(400)
                .body(new ApiResponse(result));
    }

    @GetMapping("/less-than/{price}")
    public ResponseEntity<?> getProductsLessThanPrice(@PathVariable double price) {

        if(productService.getProductsLessThanPrice(price).isEmpty()){
            return ResponseEntity.status(400).body(new ApiResponse("No products found with this price"));
        }
        return ResponseEntity.status(200)
                .body(productService.getProductsLessThanPrice(price));
    }

    @GetMapping("/best-deal")
    public ResponseEntity<?> getBestDeal() {
        Product product = productService.getBestDeal();

        if(product == null){
            return ResponseEntity.status(400).body(new ApiResponse("no products available"));
        }
        return ResponseEntity.status(200).body(product);
    }
}


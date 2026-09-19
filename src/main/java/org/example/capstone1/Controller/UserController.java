package org.example.capstone1.Controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.capstone1.Api.ApiResponse;
import org.example.capstone1.Model.Product;
import org.example.capstone1.Model.User;
import org.example.capstone1.Service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.example.capstone1.Service.ProductService;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final ProductService productService;

    @GetMapping("/get")
    public ResponseEntity<?> getUsers() {
        if (userService.getUsers().isEmpty()) {
            return ResponseEntity.status(400).body(new ApiResponse("No users found"));
        }
        return ResponseEntity.status(200).body(userService.getUsers());
    }

    @PostMapping("/add")
    public ResponseEntity<?> addUser(@RequestBody @Valid User user) {
        boolean added = userService.addUser(user);
        if (added) {
            return ResponseEntity.status(200).body(new ApiResponse("user added"));
        }
        return ResponseEntity.status(400)
                .body(new ApiResponse("user can not be added"));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateUser(@PathVariable int id, @RequestBody @Valid User user) {
        boolean updated = userService.updateUser(id, user);
        if (updated) {
            return ResponseEntity.status(200).body(new ApiResponse("user updated"));
        }
        return ResponseEntity.status(400).body(new ApiResponse("user not found"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable int id) {
        boolean deleted = userService.deleteUser(id);
        if (deleted) {
            return ResponseEntity.status(200).body(new ApiResponse("user deleted"));
        }
        return ResponseEntity.status(400).body(new ApiResponse("user not found"));
    }

    @PostMapping("/buy/{userId}/{productId}/{merchantId}")
    public ResponseEntity<?> buyProduct(@PathVariable int userId, @PathVariable int productId, @PathVariable int merchantId) {

        String result = userService.buyProduct(userId, productId, merchantId);
        if (result.equals("product bought successfully")) {
            return ResponseEntity.status(200).body(new ApiResponse(result));
        }
        return ResponseEntity.status(400).body(new ApiResponse(result));
    }

    @PutMapping("/add-balance/{userId}/{amount}")
    public ResponseEntity<?> addBalance(@PathVariable int userId, @PathVariable double amount) {
        String result = userService.addBalance(userId, amount);

        if (result.equals("balance added")) {
            return ResponseEntity.status(200).body(new ApiResponse(result));
        }
        return ResponseEntity.status(400).body(new ApiResponse(result));
    }

    @PutMapping("/withdraw-balance/{userId}/{amount}")
    public ResponseEntity<?> withdrawBalance(@PathVariable int userId, @PathVariable double amount) {
        String result = userService.withdrawBalance(userId, amount);

        if (result.equals("balance withdrawn")) {
            return ResponseEntity.status(200).body(new ApiResponse(result));
        }
        return ResponseEntity.status(400).body(new ApiResponse(result));
    }

    @GetMapping("/recommend/{userId}")
    public ResponseEntity<?> recommendProduct(@PathVariable int userId) {
        User user = userService.searchUserById(userId);
        if (user == null) {
            return ResponseEntity.status(400).body(new ApiResponse("user not found"));
        }

        Product product = productService.recommendProduct(user.getBalance());
        if (product == null) {
            return ResponseEntity.status(400).body(new ApiResponse("no product available"));
        }
        return ResponseEntity.status(200).body(product);
    }

    @GetMapping("/luxury-advisor/{userId}")
    public ResponseEntity<?> luxuryAdvisor(@PathVariable int userId) {

        User user = userService.searchUserById(userId);
        if (user == null) {
            return ResponseEntity.status(400).body(new ApiResponse("user not found"));
        }
        Product product = productService.getLuxuryRecommendation(user.getBalance());
        if (product == null) {
            return ResponseEntity.status(400).body(new ApiResponse("no product available"));
        }
        return ResponseEntity.status(200).body(product);
    }
}

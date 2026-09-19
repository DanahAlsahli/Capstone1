package org.example.capstone1.Controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.capstone1.Api.ApiResponse;
import org.example.capstone1.Model.Category;
import org.example.capstone1.Service.CategoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/category")
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;

    @GetMapping("/get")
    public ResponseEntity<?> getCategories(){
        if (categoryService.getCategories().isEmpty()) {
            return ResponseEntity.status(400).body(new ApiResponse("No categories found"));
        }
        return ResponseEntity.status(200).body(categoryService.getCategories());
    }

    @PostMapping("/add")
    public ResponseEntity<?> addCategory(@RequestBody @Valid Category category){
        boolean added = categoryService.addCategory(category);

        if(added){
            return ResponseEntity.status(200).body(new ApiResponse("Category added"));
        }
        return ResponseEntity.status(400).body(new ApiResponse("Category can not be added"));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateCategory(@PathVariable int id, @RequestBody @Valid Category category){
        boolean updated = categoryService.updateCategory(id, category);
        if(updated){
            return ResponseEntity.status(200).body(new ApiResponse("Category updated"));
        }
        return ResponseEntity.status(400).body(new ApiResponse("Category not found"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteCategory(@PathVariable int id){
        boolean deleted = categoryService.deleteCategory(id);
        if(deleted){
            return ResponseEntity.status(200).body(new ApiResponse("Category deleted"));
        }
        return ResponseEntity.status(400).body(new ApiResponse("Category not found"));
    }
}


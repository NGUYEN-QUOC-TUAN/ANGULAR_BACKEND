package org.tool.angular_backend.controller;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.tool.angular_backend.dto.CategoryDTO;


import java.util.List;

@RestController
@RequestMapping("${api.prefix}/categories")
public class CategoryController {

    //Hiển thị tất cả Categories
    @GetMapping("") //http://localhost:8088/api/v1/categories?page=1&limit=10
    public ResponseEntity<String> getAllCategories(@RequestParam("page") int page, @RequestParam("limit") int limit) {
        return ResponseEntity.ok(String.format("getCategories, page = %d, limit = %d", page, limit));
    }


    // Tham số truyền vào là 1 Object thì sao? => Data Transfer Object = Request Object
    @PostMapping("")
    public ResponseEntity<?> insertCategory(@Valid @RequestBody CategoryDTO categoryDTO, BindingResult result) {
        if(result.hasErrors()){
            List<String> errorMessages = result.getFieldErrors()
                    .stream()
                    .map(FieldError::getDefaultMessage)
                    .toList();
            return ResponseEntity.badRequest().body(errorMessages);
        }
        return ResponseEntity.ok("This is insertCategory " + categoryDTO.getName());
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateCategory(@PathVariable(name = "id") Long id) {
        return ResponseEntity.ok("This is updateCategory with id = " + id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCategory(@PathVariable(name = "id") Long id) {
        return ResponseEntity.ok("This is deleteCategory with id = " + id);
    }
}

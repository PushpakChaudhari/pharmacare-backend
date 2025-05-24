package com.jspm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.jspm.model.Category;
import com.jspm.service.CategoryService;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @CrossOrigin(origins = "https://pharmacar.onrender.com", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.OPTIONS})
    @GetMapping
    public List<Category> getAllCategories() {
        return categoryService.getAllCategories();
    }

    @CrossOrigin(origins = "https://pharmacar.onrender.com", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.OPTIONS})
    @PostMapping
    public Category createCategory(@RequestBody Category category) {
        return categoryService.saveCategory(category);
    }
    @CrossOrigin(origins = "https://pharmacar.onrender.com", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.OPTIONS})
    @PutMapping("/{id}")
    public ResponseEntity<Category> updateCategory(@PathVariable Long id, @RequestBody Category updatedCategory) {
        Category category = categoryService.updateCategory(id, updatedCategory);
        if (category != null) {
            return ResponseEntity.ok(category);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @CrossOrigin(origins = "https://pharmacar.onrender.com", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.OPTIONS})
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Long id) {
        boolean deleted = categoryService.deleteCategory(id);
        if (deleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
package com.jspm.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.jspm.model.Category;
import com.jspm.model.Medicine;
import com.jspm.service.CategoryService;
import com.jspm.service.MedicineService;
@RestController
@CrossOrigin(origins = "https://pharmacar.onrender.com", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.OPTIONS})
@RequestMapping("/api/medicines")
public class MedicineController {
    @Autowired
    private MedicineService medicineService;

    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public List<Medicine> getAllMedicines() {
        return medicineService.getAllMedicines();
    }

    @PostMapping
    public Medicine createMedicine(@RequestBody Medicine medicine) {
        return medicineService.saveMedicine(medicine);
    }

    @GetMapping("/categories")
    public List<Category> getAllCategories() {
        return categoryService.getAllCategories();
    }

    @GetMapping("/category/{categoryId}")
    public List<Medicine> getMedicinesByCategory(@PathVariable Long categoryId) {
        return medicineService.getMedicinesByCategory(categoryId);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Medicine> updateMedicine(@PathVariable Long id, @RequestBody Medicine updatedMedicine) {
        // handle update logic
        return ResponseEntity.ok(medicineService.updateMedicine(id, updatedMedicine));
    }



    // ✅ Add DELETE endpoint
    @DeleteMapping("/{id}")
    public void deleteMedicine(@PathVariable Long id) {
        medicineService.deleteMedicine(id);
    }
}

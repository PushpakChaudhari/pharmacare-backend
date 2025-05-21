package com.jspm.service;

import java.util.List;

import com.jspm.model.Medicine;

public interface MedicineService {
    List<Medicine> getAllMedicines();
    Medicine saveMedicine(Medicine medicine);
	List<Medicine> getMedicinesByCategory(Long categoryId);
	Medicine getMedicineById(Long id);
	Medicine updateMedicine(Long id, Medicine updatedMedicine);
	void deleteMedicine(Long id);

}

package com.jspm.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jspm.model.Medicine;
import com.jspm.repository.MedicineRepository;
import com.jspm.service.MedicineService;

import java.util.List;

@Service
public class MedicineServiceImpl implements MedicineService {

    @Autowired
    private MedicineRepository medicineRepository;

    @Override
    public List<Medicine> getAllMedicines() {
        return medicineRepository.findAll();
    }


    @Override
    public Medicine saveMedicine(Medicine medicine) {
    
        return medicineRepository.save(medicine);
    }

    @Override
    public Medicine getMedicineById(Long id) {
        return medicineRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Medicine not found with id: " + id));
    }


	@Override
	public List<Medicine> getMedicinesByCategory(Long categoryId) {
	    return medicineRepository.findByCategoryId(categoryId);
	}

	@Override
	public Medicine updateMedicine(Long id, Medicine updatedMedicine) {
	    Medicine existing = medicineRepository.findById(id)
	            .orElseThrow(() -> new RuntimeException("Medicine not found with id: " + id));

	    existing.setName(updatedMedicine.getName());
	    existing.setBatch(updatedMedicine.getBatch());
	    existing.setExpiryDate(updatedMedicine.getExpiryDate());
	    existing.setQuantity(updatedMedicine.getQuantity());
	    existing.setMrp(updatedMedicine.getMrp());
	    existing.setDiscount(updatedMedicine.getDiscount());
	    existing.setCategory(updatedMedicine.getCategory());

	    return medicineRepository.save(existing);
	}

	@Override
	public void deleteMedicine(Long id) {
	    medicineRepository.deleteById(id);
	}

}
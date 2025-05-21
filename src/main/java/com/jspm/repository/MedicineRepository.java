package com.jspm.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jspm.model.Medicine;

@Repository
public interface MedicineRepository extends JpaRepository<Medicine, Long> {
	List<Medicine> findByCategoryId(Long categoryId);
}


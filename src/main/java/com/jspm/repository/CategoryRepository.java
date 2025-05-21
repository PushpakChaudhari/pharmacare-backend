package com.jspm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jspm.model.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
}
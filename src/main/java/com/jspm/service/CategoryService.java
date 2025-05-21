package com.jspm.service;

import java.util.List;

import com.jspm.model.Category;

public interface CategoryService {
    List<Category> getAllCategories();
    Category saveCategory(Category category);
    public Category updateCategory(Long id, Category updatedCategory);
    public boolean deleteCategory(Long id);

}
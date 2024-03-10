package com.codegym.maccenter.service.impl;

import com.codegym.maccenter.model.Category;
import com.codegym.maccenter.repository.CategoryRepository;
import com.codegym.maccenter.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    CategoryRepository categoryRepository;

    public void updateCategory(Category category) {
        categoryRepository.save(category);
    }

    public void deleteCategoryById(int id) {
        categoryRepository.deleteById(id);
    }

    public Optional<Category> getCategoryById(int id) {
        return categoryRepository.findById(id);
    }

    public List<Category> getAllCategory() {
        return categoryRepository.findAll();
    }
}



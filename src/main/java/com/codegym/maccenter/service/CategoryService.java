package com.codegym.maccenter.service;


import com.codegym.maccenter.model.Category;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface CategoryService {

    public List<Category> getAllCategory();

    public void updateCategory(Category category);

    public void deleteCategoryById(int id);

    public Optional<Category> getCategoryById(int id);





}
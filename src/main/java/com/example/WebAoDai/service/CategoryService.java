package com.example.WebAoDai.service;

import com.example.WebAoDai.entity.Category;

import java.util.List;

public interface CategoryService {
	
	Category saveCategory(Category category);

	Category getCategoryById(int id);

	Category updateCategory(Category category);
	
	List<Category> findAll();

	void deleteCategoryId(int id);
}

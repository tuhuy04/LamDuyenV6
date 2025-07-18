package com.example.WebAoDai.service;

import com.example.WebAoDai.entity.CategorySize;

import java.util.List;

public interface CategorySizeService {
	CategorySize save(CategorySize categorySize);
	CategorySize findById(Integer id);

	List<CategorySize> getAllSizeOfCategoryId(Integer id);
}

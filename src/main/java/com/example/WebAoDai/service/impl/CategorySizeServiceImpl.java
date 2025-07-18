package com.example.WebAoDai.service.impl;

import com.example.WebAoDai.entity.CategorySize;
import com.example.WebAoDai.repository.ProductRepository;
import com.example.WebAoDai.repository.CategorySizeRepository;
import com.example.WebAoDai.service.CategorySizeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategorySizeServiceImpl implements CategorySizeService {

	@Autowired
	ProductRepository productRepository;

	@Autowired
	CategorySizeRepository categorySizeRepository;


	@Override
	public CategorySize save(CategorySize categorySize) {
		return categorySizeRepository.save(categorySize);
	}


	@Override
	public CategorySize findById(Integer id) {
		return categorySizeRepository.findById(id).get();
	}

	@Override
	public List<CategorySize> getAllSizeOfCategoryId(Integer id) {
		return categorySizeRepository.findAllByCategory_Id(id);
	}
}

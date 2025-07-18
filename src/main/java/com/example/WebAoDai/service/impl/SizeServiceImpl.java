package com.example.WebAoDai.service.impl;

import com.example.WebAoDai.entity.Size;
import com.example.WebAoDai.repository.SizeRepository;
import com.example.WebAoDai.service.SizeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SizeServiceImpl implements SizeService {

	@Autowired
	SizeRepository sizeRepository;

	@Override
	public Size findById(Integer id) {
		return sizeRepository.findById(id).get();
	}
}

package com.example.WebAoDai.service.impl;

import java.util.List;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.WebAoDai.entity.Cart;
import com.example.WebAoDai.repository.CartRepository;
import com.example.WebAoDai.service.CartService;

@Service
public class CartServicempl implements CartService{

	@Autowired
	CartRepository cartRepository;
	/**
	 *
	 */
	@Override
	public void deleteById(int id) {
		// TODO Auto-generated method stub
		cartRepository.deleteById(id);
	}
	@Override
	public List<Cart> GetAllCartByUser_id(Long user_id) {
		// TODO Auto-generated method stub
		return cartRepository.findAllByUserId(user_id);
	}
	@Override
	public void saveCart(Cart cart) {
		// TODO Auto-generated method stub
		cartRepository.save(cart);
	}

	@Transactional
	public void deleteAllByUserId(Long userId) {
		cartRepository.deleteAllByUserId(userId);
	}

}

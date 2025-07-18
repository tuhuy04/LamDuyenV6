package com.example.WebAoDai.service;

import com.example.WebAoDai.entity.Cart;

import java.util.List;

public interface CartService {
	
	void deleteById(int id);
	
	List<Cart> GetAllCartByUser_id(Long user_id);
	
	void saveCart(Cart cart);
	public void deleteAllByUserId(Long userId);
}

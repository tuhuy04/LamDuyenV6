package com.example.WebAoDai.service;

import com.example.WebAoDai.entity.Order_Item;

import java.util.List;

public interface Order_ItemService {

	List<Order_Item> getAllByOrder_Id(int id);
	public void saveOrder_Item(Order_Item order_Item);
	void deleteById(int id);
}

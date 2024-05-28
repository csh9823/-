package com.example.demo.service;

import java.util.List;

import com.example.demo.vo.Category;
import com.example.demo.vo.Purchase;

public interface PurchaseService {

	
	public List<Purchase> purchaseList();
	
	public List<Category> category();
	
}

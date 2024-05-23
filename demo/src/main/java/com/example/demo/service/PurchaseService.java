package com.example.demo.service;

import java.util.List;

import com.example.demo.vo.CategoryHistory;
import com.example.demo.vo.Purchase;

public interface PurchaseService {

	
	public List<Purchase> purchaseList();
	
	public List<CategoryHistory> categoryHistory();
	
}

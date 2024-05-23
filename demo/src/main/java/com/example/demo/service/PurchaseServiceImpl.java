package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.PurchaseMapper;
import com.example.demo.vo.CategoryHistory;
import com.example.demo.vo.Purchase;

@Service
public class PurchaseServiceImpl implements PurchaseService{
	
	@Autowired
	private PurchaseMapper mapper;

	@Override
	public List<Purchase> purchaseList() {
		List<Purchase> list = mapper.purchaseList();
		if (list.isEmpty()) {
			return null;
		}
		
		return list;
	}
	
	@Override
	public List<CategoryHistory> categoryHistory() {
		List<CategoryHistory> list = mapper.categoryHistory();
		if(list.isEmpty()) {
			return null;
		}
		
		return list;
	}
	
	
}

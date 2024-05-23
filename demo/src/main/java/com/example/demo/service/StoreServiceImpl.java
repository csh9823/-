package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.StoreMapper;
import com.example.demo.vo.Store;

@Service
public class StoreServiceImpl implements StoreService{
	
	@Autowired
	private StoreMapper mapper;

	@Override
	public List<Store> storeList() {
		List<Store> list = mapper.storeList();
		if (list.isEmpty()) {
			return null;
		}
		
		return list;
		
	}
	
}

package com.example.demo.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.jinPurchaseMapper;

@Service
public class JinPurchaseServiceImpl implements JinPurchaseService {

	@Autowired
	private jinPurchaseMapper mapper;
	
	@Override
	public List<HashMap<String, Object>> jinPurchaseList() {

		return mapper.jinPurchaseList();
	}

	public void purchaseadd (List<HashMap<String, Object>> purchaseData) {
		 for (int i = 0; i < purchaseData.size(); i++) {
		        HashMap<String, Object> account = purchaseData.get(i);
		       if(account.get("price") != null) {
		    	   mapper.purchaseadd(account);
		    	   mapper.storeadd(account);
		       }
		    }
	}
	
}

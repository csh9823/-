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
    	    HashMap<String, Object> category = purchaseData.get(i);
    	    if(category.get("price")!= null) {
     	       System.out.println("CODE: " + category.get("CODE"));
         	   System.out.println("BOXCODE: " + category.get("BOXCODE"));
         	   System.out.println("카테코드: " + category.get("pcatacode"));
         	   System.out.println("거래처코드: " + category.get("paccode"));
         	   System.out.println("주문박스: " + category.get("inputCount"));
         	   System.out.println("입고가: " + category.get("price"));
         	   System.out.println("수량: " + category.get("thisCount"));
               int pirce = (Integer) category.get("price");
               category.put("intpricet", pirce);
               int thisCount = (Integer) category.get("thisCount");
               category.put("intthisCount", thisCount);
               int inputCount = (Integer) category.get("inputCount");
               category.put("intinputCount", inputCount);
         	   mapper.storeadd(category);
    	    }else {
    	    	System.out.println("주문안함");
    	    }
    	}
	}
	
}

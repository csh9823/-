package com.example.demo.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.ProductMapper;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductMapper mapper;
	
	public void productadd(Map<String, Object> productData) {
		System.out.println("등록완료");
		mapper.productadd(productData);
		mapper.insertinventory(productData);
	}
	
	public List<HashMap<String, Object>> productseachlist(Map<String, Object> productData){
		String searchvalue =  (String) productData.get("search");
		System.out.println("서비스 서치"+searchvalue);
		if(searchvalue.equals("1")) {
			return mapper.productseachnamelist(productData);
		}else {
			return mapper.productseachacvaluelist(productData);
		}
	}
	public void productedit(List<HashMap<String, Object>> productData) {
		System.out.println(productData.get(0).get("deletet"));
		for (int i = 0; i < productData.size(); i++) {
    	    HashMap<String, Object> account = productData.get(i);
    	    if(account.get("deletet") != null) {
    	    	System.out.println("삭제완료");
    	    	System.out.println(account.get("CODE"));
    	    	mapper.productdelete(account);
    	    }else if(account.get("edit") != null) {
        	    System.out.println("수정완료");
        	    mapper.productupdate(account);
    	    }
    	}
	}
}

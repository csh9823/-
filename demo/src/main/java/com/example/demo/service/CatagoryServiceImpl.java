package com.example.demo.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.CatagoryMapper;

@Service
public class CatagoryServiceImpl implements CatagoryService {

	@Autowired
	private CatagoryMapper mapper;
	
	// 카테고리 리스트
	@Override
	public List<HashMap<String, Object>> CatagoryList() {		
		return mapper.CatagoryList();
	}
	
	// 카테고리 수정
	public void catagoryedit(List<HashMap<String, Object>> categoryData) {
        // 받은 데이터(categoryData)를 출력
    	for (int i = 0; i < categoryData.size(); i++) {
    	    HashMap<String, Object> category = categoryData.get(i);
    	    if(category.get("deletet") != null) {
        	    System.out.println("code: " + category.get("CODE"));
    	    	System.out.println("삭제완료");
    	    	mapper.deleteCate(category);
    	    }else if(category.get("edit") != null) {
        	    System.out.println("VALUE: " + category.get("VALUE"));
        	    System.out.println("revalue: " + category.get("revalue"));
        	    System.out.println("code: " + category.get("CODE"));
        	    System.out.println("수정완료");
        	    mapper.editCate(category);
    	    }
    	}
	}
	
	// 카테고리 생성
	public void catagoryadd(String cataname,String catacode) {
        Map<String, Object> categoryParams = new HashMap<>();
        categoryParams.put("value", cataname);
        categoryParams.put("catacode", catacode);
        System.out.println(categoryParams.get("value"));
        mapper.insertCategory(categoryParams);       
	}
}

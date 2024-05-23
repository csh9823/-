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
    			mapper.catagorydelete(category);
    			mapper.catagoryupdatecode(category);
    	    	System.out.println("삭제완료");
        	    System.out.println("code: " + category.get("CODE"));
    	    }else if(category.get("edit") != null) {
    	    	mapper.catagoryupdatevalue(category);
    	    	mapper.catagoryupdatecomCdvalue(category);
        	    System.out.println("VALUE: " + category.get("VALUE"));
        	    System.out.println("revalue: " + category.get("revalue"));
        	    System.out.println("code: " + category.get("CODE"));
        	    System.out.println("수정완료");
    	    }
    	}
	}
	
	// 카테고리 생성
	public void catagoryadd(String cataname) {
		 // 첫 번째 인서트 수행 및 시퀀스 값 생성
        Map<String, Object> categoryParams = new HashMap<>();
        mapper.insertCategory(categoryParams);       
        // 삽입된 시퀀스 값을 가져옴
        int categoryCode = (int) categoryParams.get("categoryCode");       
        // 두 번째 인서트 수행
        Map<String, Object> detailParams = new HashMap<>();
        detailParams.put("categoryCode", categoryCode);
        detailParams.put("value", cataname);
        detailParams.put("div", "category");
        detailParams.put("situation", 1);
        mapper.insertCategoryDetail(detailParams);
        // 세 번째 인서트 수행
        mapper.insertCategoryhistory(detailParams);
	}
}

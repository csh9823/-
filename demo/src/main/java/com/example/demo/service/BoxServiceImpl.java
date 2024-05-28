package com.example.demo.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.BoxMapper;

@Service
public class BoxServiceImpl implements BoxService {

	@Autowired
	private BoxMapper mapper;
	
	@Override
	public List<HashMap<String, Object>> BoxList() {

		return mapper.boxList();
	}
	
	public void Boxadd(String boxcode,String boxname,int boxcount) {	
        Map<String, Object> boxParams = new HashMap<>();
        boxParams.put("boxcode", boxcode);
        boxParams.put("boxname", boxname);
        boxParams.put("boxcount", boxcount);
        mapper.boxadd(boxParams);
	}
	
	public void boxedit(List<HashMap<String, Object>> boxedit) {
		 // 받은 데이터(categoryData)를 출력
    	for (int i = 0; i < boxedit.size(); i++) {
    	    HashMap<String, Object> box = boxedit.get(i);
    	    if(box.get("deletet") != null) {
    	    	mapper.Boxdelete(box);
    	    	System.out.println("삭제완료");
    	    }else if(box.get("edit") != null) {
                int boxCount = (Integer) box.get("BOXCOUNT");
                System.out.println("BOXCOUNT as int: " + boxCount);
                box.put("intboxcount", boxCount);
    	    	mapper.Boxupdate(box);
        	    System.out.println("수정완료");
    	    }
    	}
	}
}

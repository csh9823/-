package com.example.demo.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.StoreMapper;


import com.example.demo.vo.Store;
import com.example.demo.vo.StoreHistory;

@Service
public class StoreServiceImpl implements StoreService{
	
	@Autowired
	private StoreMapper mapper;

	@Override
	public List<StoreHistory> storeList() {
		List<StoreHistory> list = mapper.storeList();
		if (list.isEmpty()) {
			return null;
		}
		
		return list;
		
	}
	

	
	@Override
	public List<HashMap<String, Object>> storelist() {
		return mapper.storelist();
	}
	
	public void stoupdate(List<HashMap<String, Object>> stoData) {
	    for (HashMap<String, Object> account : stoData) {
	        if (account.get("noadd") != null && account.get("noadd") instanceof String) {
	            String noaddStr = (String) account.get("noadd");
	            int noadd = Integer.parseInt(noaddStr);
	            int BOXCC = (int) account.get("BOXCC");
	            int boxcount = (int) account.get("BOXCOUNT");
	            int QUANTITY = (int) account.get("QUANTITY");
	            int PRICE = (int) account.get("PRICE");
	            int requ = QUANTITY - noadd * BOXCC;
	            int nobox = boxcount - noadd;
	            account.put("requ", requ);
	            account.put("nobox", nobox);
	            account.put("intprice", PRICE); // intprice 대신 PRICE를 사용하거나 PRICE를 Integer로 캐스팅하세요.
	            mapper.storeupdate(account);
	            
	            // store 테이블에 데이터 삽입
	            mapper.storeinsert(account);	            
	            // 삽입된 데이터의 store_no 가져오기
	            int store_no = (int) account.get("store_no"); // Integer로 캐스팅
	            // store_history 테이블에 데이터 삽입
	            HashMap<String, Object> historyData = new HashMap<>();
	            historyData.put("store_no", store_no); // store_no를 Integer로 변환하지 않고 그대로 사용
	            historyData.put("CATECODE", account.get("CATECODE"));
	            historyData.put("PROCODE", account.get("PROCODE"));
	            historyData.put("ACCCODE", account.get("ACCCODE"));
	            historyData.put("BOXCODE", account.get("BOXCODE"));
	            historyData.put("nobox", nobox);
	            historyData.put("requ", requ);
	            historyData.put("datees", account.get("datees"));
	            historyData.put("PRICE", PRICE); 
	            mapper.storeHistoryInsert(historyData);
	            
	            int sta = mapper.inventoryvalue(account)+requ;
	            account.put("reinqu", sta);
	            mapper.storeinventoryupdate(account);            
	        } else {
	            System.out.println("미지급 없음");
	        }
	    }
	}
}

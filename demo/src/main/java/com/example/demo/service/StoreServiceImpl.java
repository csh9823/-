package com.example.demo.service;

<<<<<<< HEAD
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
	
=======
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.demo.dao.StoreMapper;

@Service
public class StoreServiceImpl implements StoreService {

	@Autowired
	private StoreMapper mapper;
	
	@Override
	public List<HashMap<String, Object>> storelist() {

		return mapper.storelist();
	}
	
	public void stoupdate(List<HashMap<String, Object>> stoData) {
    	for (int i = 0; i < stoData.size(); i++) {
    	    HashMap<String, Object> account = stoData.get(i);
    	    if(account.get("noadd") == null) {
        	    System.out.println(account.get("BOXCOUNT"));
        	    System.out.println(account.get("datees"));
        	    System.out.println(account.get("noadd"));
    	    	mapper.stoupdate(account);
    	    }else {
    	    	int countst = mapper.stocountdate(account);
    	    	int noadd = (Integer) account.get("BOXCOUNT");
    	    	int smd = countst - noadd ;

    	    	account.put("updatecount", smd);
        	    System.out.println(account.get("BOXCOUNT"));
        	    System.out.println(account.get("datees"));
        	    System.out.println(account.get("noadd"));
        	    System.out.println(account.get("updatecount"));
        	    System.out.println(account.get("CODE"));
    	    	mapper.stoupdatecount(account);
    	    }
    	}
	}
>>>>>>> branch 'master' of https://github.com/csh9823/demo.git
}

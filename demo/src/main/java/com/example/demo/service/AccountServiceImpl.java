package com.example.demo.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.AccountMapper;
import com.example.demo.dao.BoxMapper;

@Service
public class AccountServiceImpl implements AccountService {

	@Autowired
	private AccountMapper mapper;
	
	@Override
	public List<HashMap<String, Object>> AccountList() {

		return mapper.AccountList();
	}

	@Override
	public void Accountedit(List<HashMap<String, Object>> accountedit) {
        // 받은 데이터(categoryData)를 출력
    	for (int i = 0; i < accountedit.size(); i++) {
    	    HashMap<String, Object> account = accountedit.get(i);
    	    if(account.get("deletet") != null) {
        	    System.out.println("code: " + account.get("CODE"));
        	    System.out.println("VALUE: " + account.get("VALUE"));
        	    System.out.println("HP: " + account.get("HP"));
        	    System.out.println("FAX: " + account.get("FAX"));
        	    System.out.println("ADDRESS: " + account.get("ADDRESS"));
        	    mapper.deleteAccount(account);
    	    	System.out.println("삭제완료");
    	    }else if(account.get("edit") != null) {
        	    System.out.println("CODE: " + account.get("CODE"));
        	    System.out.println("VALUE: " + account.get("VALUE"));
        	    System.out.println("HP: " + account.get("HP"));
        	    System.out.println("FAX: " + account.get("FAX"));
        	    System.out.println("ADDRESS: " + account.get("ADDRESS"));
        	    mapper.updateAccount(account);
        	    System.out.println("수정완료");
    	    }
    	}
	}

	@Override
	public void Accountadd(Map<String, Object> acdate) {
		mapper.insertAccount(acdate);
	}	
	
}

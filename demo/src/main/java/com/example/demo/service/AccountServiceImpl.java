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
    	    	mapper.Accountdelete(account);
    	    	mapper.Accountupdatecode(account);
    	    	System.out.println("삭제완료");
        	    System.out.println("code: " + account.get("CODE"));
    	    }else if(account.get("edit") != null) {
    	    	mapper.Accountupdatevalue(account);
    	    	mapper.AccountupdatecomCdvalue(account);
        	    System.out.println("VALUE: " + account.get("VALUE"));
        	    System.out.println("revalue: " + account.get("revalue"));
        	    System.out.println("code: " + account.get("CODE"));
        	    System.out.println("수정완료");
    	    }
    	}
	}

	@Override
	public void Accountadd(String accountname) {
		 // 첫 번째 인서트 수행 및 시퀀스 값 생성
        Map<String, Object> accountParams = new HashMap<>();
        mapper.insertAccount(accountParams);       
        // 삽입된 시퀀스 값을 가져옴
        int accountCode = (int) accountParams.get("accountCode");  
        System.out.println(accountCode);
        // 두 번째 인서트 수행
        Map<String, Object> detailParams = new HashMap<>();
        detailParams.put("accountCode", accountCode);
        detailParams.put("value", accountname);
        detailParams.put("div", "account");
        detailParams.put("situation", 1);
        mapper.AccountCategoryDetail(detailParams);
        // 세 번째 인서트 수행
        mapper.Accounthistory(detailParams);
		
	}	
	
}

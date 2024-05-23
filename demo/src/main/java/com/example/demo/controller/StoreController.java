package com.example.demo.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.service.StoreService;
import com.example.demo.vo.Store;

@RestController
public class StoreController {
	
	@Autowired
	private StoreService ss;

	@PostMapping("/api/storeList")
	public Map<String, Object> storeList() {
		System.out.println();
		Map<String, Object> map = new HashMap<>();
		
		List<Store> list = ss.storeList();
		
		System.out.println(list);
		
		if (list.isEmpty()) {
			map.put("res", "fail");
			
		} else {
			map.put("res", "success");
			map.put("data", list);
		}
		
		
		return map;
	}
	
}

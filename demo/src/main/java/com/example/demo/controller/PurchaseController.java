package com.example.demo.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.service.PurchaseService;
import com.example.demo.vo.Purchase;

@RestController
public class PurchaseController {
	
	@Autowired
	private PurchaseService ps;

	
	@PostMapping("/api/purchaseList")
	public Map<String, Object> purchaseList () {
		System.out.println();
		Map<String, Object> map = new HashMap<>();
		
		List<Purchase> list = ps.purchaseList();
		
		if (list.isEmpty()) {
			map.put("res", "fail");
			
		} else {
			map.put("res", "success");
			map.put("data", list);
		}
		
		
		return map;
	}
}

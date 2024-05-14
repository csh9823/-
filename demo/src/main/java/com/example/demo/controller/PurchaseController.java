package com.example.demo.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PurchaseController {

	
	@PostMapping("/api/purchaseList")
	public Map<String, Object> purchaseList () {
		System.out.println();
		Map<String, Object> map = new HashMap<>();
		
		
		
		return map;
	}
}

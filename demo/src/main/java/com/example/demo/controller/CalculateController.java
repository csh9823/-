package com.example.demo.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.service.CalculateService;
import com.example.demo.vo.Calculate;

@RestController
public class CalculateController {
	
	@Autowired
	private CalculateService cs;

	
	@PostMapping("/api/calculate")
	public Map<String, Object> calculate () {
		Map<String, Object> map = new HashMap<>();
		
		List<Calculate> list = cs.calculate();
		
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

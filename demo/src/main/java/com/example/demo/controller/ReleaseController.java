package com.example.demo.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.service.ReleaseService;
import com.example.demo.vo.Release;



@RestController
public class ReleaseController {

	@Autowired
	private ReleaseService rs;
	
	@PostMapping("/api/releaseList")
	public Map<String, Object> releaseList() {
		
		Map<String, Object> map = new HashMap<>();
				
		List<Release> list = rs.releaseList();
		
		if (list.isEmpty()) {
			map.put("res", "fail");
		} else {
			map.put("res", "success");
			map.put("data", list);
		}
		
		return map;
	}
}

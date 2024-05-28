package com.example.demo.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.service.ReleaseService;
import com.example.demo.vo.Department;
import com.example.demo.vo.Release;
import com.example.demo.vo.ReleaseList;



@RestController
public class ReleaseController {

	@Autowired
	private ReleaseService rs;
	
	@PostMapping("/api/releaseList")
	public Map<String, Object> releaseList() {
		
		Map<String, Object> map = new HashMap<>();
				
		List<ReleaseList> list = rs.releaseList();
		
		if (list.isEmpty()) {
			map.put("res", "fail");
		} else {
			map.put("res", "success");
			map.put("data", list);
		}
		
		return map;
	}
	
	@PostMapping("/api/releasePage")
	public Map<String, Object> releasePage() {
		
		Map<String, Object> map = rs.departmentList();
		
		
		
		
		return map;
	}
	
	@PostMapping("/api/releaseSave")
	public Map<String, Object> releaseSave(@RequestBody List<Release> list){
		System.out.println(list);
		
		Map<String, Object> map = rs.releaseSave(list);
		
		return map;
	}
}

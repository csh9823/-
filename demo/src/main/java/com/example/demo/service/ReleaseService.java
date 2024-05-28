package com.example.demo.service;

import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.RequestBody;

import com.example.demo.vo.Department;
import com.example.demo.vo.Release;
import com.example.demo.vo.ReleaseList;

public interface ReleaseService {

	public List<ReleaseList> releaseList();
	
	public Map<String, Object> departmentList();
	
	public Map<String, Object> releaseSave(List<Release> list);
	
}

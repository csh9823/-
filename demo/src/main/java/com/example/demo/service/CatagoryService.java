package com.example.demo.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.web.bind.annotation.RequestBody;

import com.example.demo.domain.Catagory;

public interface CatagoryService {
	public List<HashMap<String, Object>> CatagoryList();
	public void catagoryedit(List<HashMap<String, Object>> categoryData);
	public void catagoryadd(String cataname);
}

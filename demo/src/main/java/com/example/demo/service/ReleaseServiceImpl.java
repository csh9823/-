package com.example.demo.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.ReleaseMapper;
import com.example.demo.vo.Department;
import com.example.demo.vo.Product;
import com.example.demo.vo.Release;
import com.example.demo.vo.ReleaseList;
import com.example.demo.vo.ReleasePage;

@Service
public class ReleaseServiceImpl implements ReleaseService{
	
	@Autowired
	private ReleaseMapper mapper;

	@Override
	public List<ReleaseList> releaseList() {
		
		
		return mapper.releaseList();
	}
	
	@Override
	public Map<String, Object> departmentList() {
		
		Map<String, Object> map = new HashMap<>();
		
		List<Department> deptList = mapper.departmentList();
		
		if (deptList.isEmpty()) {
			map.put("res", "deptFail");
			return map;
		}
		map.put("dept", deptList);
		
		List<Product> productList = mapper.productList();
		
		if (productList.isEmpty()) {
			map.put("res", "productFail");
			return map;
		}
		
		map.put("product", productList);
		
		List<ReleasePage> releasePage = mapper.ReleasePageList();
		
		if (releasePage.isEmpty()) {
			map.put("res", "releasePageFail");
			return map;
		}
		
		map.put("releasePage", releasePage);
		
		
		return map;
	}
	
	@Override
	public Map<String, Object> releaseSave(List<Release> list) {
		
		int su = 0;
		Map<String, Object> map = new HashMap<>();
		
		
		for (Release release : list) {
            su += mapper.releaseSave(release);
            mapper.releaseInventory(release);
        }
		System.out.println(su);
		
		if (su == 0) {
			map.put("res", "fail");
			return map;
		}
		
		map.put("res", "success");
		return map;
	}
	
	
}

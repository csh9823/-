package com.example.demo.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.DepartmentMapper;

@Service
public class DepartmentServiceImpl implements DepartmentService {

	@Autowired
	private DepartmentMapper mapper;
	
	@Override
	public List<HashMap<String, Object>> DepartmentList() {

		return mapper.DepartmentList();
	}

	@Override
	public void Departmentedit(List<HashMap<String, Object>> departmentedit) {
        // 받은 데이터(categoryData)를 출력
    	for (int i = 0; i < departmentedit.size(); i++) {
    	    HashMap<String, Object> department = departmentedit.get(i);
    	    if(department.get("deletet") != null) {
    	    	System.out.println("삭제완료");
        	    System.out.println("code: " + department.get("CODE"));
        	    mapper.deleteDepartment(department);
    	    }else if(department.get("edit") != null) {
        	    System.out.println("VALUE: " + department.get("VALUE"));
        	    System.out.println("HP: " + department.get("HP"));
        	    System.out.println("FAX: " + department.get("FAX"));
        	    System.out.println("code: " + department.get("CODE"));
        	    System.out.println("수정완료");
        	    mapper.updateDepartment(department);
    	    }
    	}
	}

	@Override
	public void Departmentadd(Map<String, Object> department) {
		System.out.println(department.get("departname"));
		System.out.println(department.get("departcode"));
		System.out.println(department.get("departhp"));
		System.out.println(department.get("departfax"));
		mapper.insertDepartment(department);
	}

}

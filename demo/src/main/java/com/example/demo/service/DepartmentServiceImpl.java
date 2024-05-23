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
    	    	mapper.Departmentdelete(department);
    	    	mapper.Departmentupdatecode(department);
    	    	System.out.println("삭제완료");
        	    System.out.println("code: " + department.get("CODE"));
    	    }else if(department.get("edit") != null) {
    	    	mapper.Departmentupdatevalue(department);
    	    	mapper.DepartmentupdatecomCdvalue(department);
        	    System.out.println("VALUE: " + department.get("VALUE"));
        	    System.out.println("revalue: " + department.get("revalue"));
        	    System.out.println("code: " + department.get("CODE"));
        	    System.out.println("수정완료");
    	    }
    	}
	}

	@Override
	public void Departmentadd(String departname) {

		 // 첫 번째 인서트 수행 및 시퀀스 값 생성
        Map<String, Object> DepartmentParams = new HashMap<>();
        mapper.insertDepartment(DepartmentParams);       
        // 삽입된 시퀀스 값을 가져옴
        int departmentCode = (int) DepartmentParams.get("departmentCode");  
        System.out.println(departmentCode);
        // 두 번째 인서트 수행
        Map<String, Object> detailParams = new HashMap<>();
        detailParams.put("departmentCode", departmentCode);
        detailParams.put("value", departname);
        detailParams.put("div", "department");
        detailParams.put("situation", 1);
        mapper.DepartmentCategoryDetail(detailParams);
        // 세 번째 인서트 수행
        mapper.Departmenthistory(detailParams);
		

	}

}

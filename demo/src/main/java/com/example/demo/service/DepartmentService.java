package com.example.demo.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface DepartmentService {
	public List<HashMap<String, Object>> DepartmentList();
	public void Departmentedit(List<HashMap<String, Object>> departmentedit);
	public void Departmentadd(Map<String, Object> department);
}

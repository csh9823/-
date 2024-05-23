package com.example.demo.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface DepartmentMapper {
	public List<HashMap<String, Object>> DepartmentList();
	public void Departmentdelete(HashMap<String, Object> departmentedit);
	public void Departmentupdatecode(HashMap<String, Object> departmentedit);
	public void Departmentupdatevalue(HashMap<String, Object> departmentedit);
	public void DepartmentupdatecomCdvalue(HashMap<String, Object> departmentedit);
    public void insertDepartment(Map<String, Object> departmentParams);
    public void DepartmentCategoryDetail(Map<String, Object> detailParams);
    public void Departmenthistory(Map<String, Object> detailParams);
}

package com.example.demo.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface DepartmentMapper {
	public List<HashMap<String, Object>> DepartmentList();
    public void insertDepartment(Map<String, Object> departmentParams);
    public void updateDepartment(HashMap<String, Object> departmentParams);
    public void deleteDepartment(HashMap<String, Object> departmentParams);
}

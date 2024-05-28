package com.example.demo.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CatagoryMapper {
	public List<HashMap<String, Object>> CatagoryList();
    public void insertCategory(Map<String, Object> categoryParams);
    public void deleteCate(HashMap<String, Object> category);
    public void editCate(HashMap<String, Object> category);

}

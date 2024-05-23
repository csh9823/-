package com.example.demo.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CatagoryMapper {
	public List<HashMap<String, Object>> CatagoryList();
	public void catagorydelete(HashMap<String, Object> categoryData);
	public void catagoryupdatecode(HashMap<String, Object> categoryData);
	public void catagoryupdatevalue(HashMap<String, Object> categoryData);
	public void catagoryupdatecomCdvalue(HashMap<String, Object> categoryData);
    public void insertCategory(Map<String, Object> categoryParams);
    public void insertCategoryDetail(Map<String, Object> detailParams);
    public void insertCategoryhistory(Map<String, Object> detailParams);
}

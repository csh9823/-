package com.example.demo.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ProductMapper {
	public void productadd(Map<String, Object> productData);
	public List<HashMap<String, Object>> productseachnamelist(Map<String, Object> productData);
	public List<HashMap<String, Object>> productseachacvaluelist(Map<String, Object> productData);
	public void productdelete(HashMap<String, Object> productData);
	public void productupdate(HashMap<String, Object> productData);
	public void insertinventory(Map<String, Object> productData);
}

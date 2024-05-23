package com.example.demo.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BoxMapper {
	public List<HashMap<String, Object>> boxList();
	public void boxadd(Map<String, Object> boxParams);
	public void Boxdelete(HashMap<String, Object> boxedit);
	public void Boxupdate(HashMap<String, Object> boxedit);
}

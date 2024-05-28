package com.example.demo.dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface StoreMapper {
	public List<HashMap<String, Object>> storelist();
	public void storeupdate(HashMap<String, Object> store);
	public void storeinsert(HashMap<String, Object> store);
	public void storeHistoryInsert(HashMap<String, Object> store);
	public void storeinventoryupdate(HashMap<String, Object> store);
	public int inventoryvalue(HashMap<String, Object> store);
}

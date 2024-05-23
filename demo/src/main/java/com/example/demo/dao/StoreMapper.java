package com.example.demo.dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.vo.Store;

@Mapper
public interface StoreMapper {

	public List<Store> storeList();
	
	public List<HashMap<String, Object>> storelist();
	public void stoupdate(HashMap<String, Object> stoData);
	public void stoupdatecount(HashMap<String, Object> stoData);
	public int stocountdate(HashMap<String, Object> stoData);
}

package com.example.demo.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.vo.Store;

@Mapper
public interface StoreMapper {

	public List<Store> storeList();
	
}

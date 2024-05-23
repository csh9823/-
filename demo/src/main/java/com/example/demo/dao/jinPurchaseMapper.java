package com.example.demo.dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface jinPurchaseMapper {
	public List<HashMap<String, Object>> jinPurchaseList();
	public void storeadd (HashMap<String, Object>  purchaseData);
	public void purchaseadd (HashMap<String, Object>  purchaseData);
}

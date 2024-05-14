package com.example.demo.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.vo.Purchase;

@Mapper
public interface PurchaseMapper {
	
	
	public List<Purchase> purchaseList();

}

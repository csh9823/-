package com.example.demo.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.vo.Calculate;
import com.example.demo.vo.PayMent;

@Mapper
public interface CalculateMapper {

	public List<Calculate> calculate();
	
	public List<PayMent> storePayMent();
	
	public List<PayMent> releasePayMent();
}

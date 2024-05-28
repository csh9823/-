package com.example.demo.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.CalculateMapper;
import com.example.demo.vo.Calculate;
import com.example.demo.vo.PayMent;

@Service
public class CalculateServiceImpl implements CalculateService{

	@Autowired
	private CalculateMapper mapper;
	
	@Override
	public List<Calculate> calculate() {
		
		return mapper.calculate();
	}
	
	
	@Override
	public Map<String, Object> payMent() {
		Map<String, Object> map = new HashMap<>();
		
		List<PayMent> list = new ArrayList<>();
		
		list.addAll(mapper.storePayMent());
		list.addAll(mapper.releasePayMent());
		
		map.put("data", list);
		
		
		return map;
	}
}

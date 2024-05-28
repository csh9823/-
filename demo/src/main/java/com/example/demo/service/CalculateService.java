package com.example.demo.service;

import java.util.List;
import java.util.Map;

import com.example.demo.vo.Calculate;

public interface CalculateService {

	public List<Calculate> calculate();
	
	public Map<String, Object> payMent();
}

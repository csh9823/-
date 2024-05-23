package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.CalculateMapper;
import com.example.demo.vo.Calculate;

@Service
public class CalculateServiceImpl implements CalculateService{

	@Autowired
	private CalculateMapper mapper;
	
	@Override
	public List<Calculate> calculate() {
		
		return mapper.calculate();
	}
}

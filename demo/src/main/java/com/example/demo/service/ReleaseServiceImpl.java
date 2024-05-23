package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.ReleaseMapper;
import com.example.demo.vo.Release;

@Service
public class ReleaseServiceImpl implements ReleaseService{
	
	@Autowired
	private ReleaseMapper mapper;

	@Override
	public List<Release> releaseList() {
		
		
		return mapper.releaseList();
	}
	
	
}

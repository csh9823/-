package com.example.demo.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.vo.Calculate;

@Mapper
public interface CalculateMapper {

	public List<Calculate> calculate();
}

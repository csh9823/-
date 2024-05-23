package com.example.demo.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.vo.Release;

@Mapper
public interface ReleaseMapper {

	public List<Release> releaseList();
}

package com.example.demo.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.vo.Department;
import com.example.demo.vo.Product;
import com.example.demo.vo.Release;
import com.example.demo.vo.ReleaseList;
import com.example.demo.vo.ReleasePage;

@Mapper
public interface ReleaseMapper {

	public List<ReleaseList> releaseList();
	
	public List<Department> departmentList();
	
	public List<ReleasePage> ReleasePageList();
	
	public List<Product> productList();
	
	public int releaseSave(Release release);
	
	public int releaseInventory(Release release);
}

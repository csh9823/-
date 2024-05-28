package com.example.demo.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AccountMapper {
	public List<HashMap<String, Object>> AccountList();
    public void insertAccount(Map<String, Object> acdate);
    public void updateAccount(HashMap<String, Object> acdate);
    public void deleteAccount(HashMap<String, Object> acdate);
}

package com.example.demo.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AccountMapper {
	public List<HashMap<String, Object>> AccountList();
	public void Accountdelete(HashMap<String, Object> departmentedit);
	public void Accountupdatecode(HashMap<String, Object> departmentedit);
	public void Accountupdatevalue(HashMap<String, Object> departmentedit);
	public void AccountupdatecomCdvalue(HashMap<String, Object> departmentedit);
    public void insertAccount(Map<String, Object> departmentParams);
    public void AccountCategoryDetail(Map<String, Object> detailParams);
    public void Accounthistory(Map<String, Object> detailParams);
}

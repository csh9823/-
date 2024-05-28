package com.example.demo.dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

<<<<<<< HEAD
import com.example.demo.vo.Store;
import com.example.demo.vo.StoreHistory;

=======
>>>>>>> branch 'master' of https://github.com/csh9823/demo.git
@Mapper
public interface StoreMapper {
<<<<<<< HEAD

	public List<StoreHistory> storeList();
	
=======
>>>>>>> branch 'master' of https://github.com/csh9823/demo.git
	public List<HashMap<String, Object>> storelist();
	public void storeupdate(HashMap<String, Object> store);
	public void storeinsert(HashMap<String, Object> store);
	public void storeHistoryInsert(HashMap<String, Object> store);
	public void storeinventoryupdate(HashMap<String, Object> store);
	public int inventoryvalue(HashMap<String, Object> store);
}

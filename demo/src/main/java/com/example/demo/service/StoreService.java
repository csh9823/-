package com.example.demo.service;

<<<<<<< HEAD
import java.util.List;

import com.example.demo.vo.Store;

public interface StoreService {

	public List<Store> storeList();
=======
import java.util.HashMap;
import java.util.List;

import org.springframework.web.bind.annotation.RequestBody;

public interface StoreService {
	public List<HashMap<String, Object>> storelist();
	public void stoupdate(List<HashMap<String, Object>> stoData);
>>>>>>> branch 'master' of https://github.com/csh9823/demo.git
}

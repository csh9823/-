package com.example.demo.service;

import java.util.HashMap;
import java.util.List;

<<<<<<< HEAD
import com.example.demo.vo.Store;
import com.example.demo.vo.StoreHistory;

import java.util.HashMap;
import java.util.List;

import org.springframework.web.bind.annotation.RequestBody;

=======
>>>>>>> branch 'master' of https://github.com/csh9823/demo.git
public interface StoreService {
<<<<<<< HEAD
	public List<StoreHistory> storeList();
=======
>>>>>>> branch 'master' of https://github.com/csh9823/demo.git
	public List<HashMap<String, Object>> storelist();
	public void stoupdate(List<HashMap<String, Object>> stoData);
}

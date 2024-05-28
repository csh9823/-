package com.example.demo.service;

import java.util.HashMap;
import java.util.List;

public interface StoreService {
	public List<HashMap<String, Object>> storelist();
	public void stoupdate(List<HashMap<String, Object>> stoData);
}

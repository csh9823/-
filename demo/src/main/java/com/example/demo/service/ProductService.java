package com.example.demo.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface ProductService {
	public void productadd(Map<String, Object> productData);
	public List<HashMap<String, Object>> productseachlist(Map<String, Object> productData);
	public void productedit(List<HashMap<String, Object>> productData);
}

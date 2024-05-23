package com.example.demo.service;

import java.util.HashMap;
import java.util.List;

public interface BoxService {
	public List<HashMap<String, Object>> BoxList();
	public void Boxadd(String boxcode,String boxname,int boxcount);
	public void boxedit(List<HashMap<String, Object>> boxedit);
}

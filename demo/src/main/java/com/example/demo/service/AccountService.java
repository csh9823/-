package com.example.demo.service;

import java.util.HashMap;
import java.util.List;

public interface AccountService {
	public List<HashMap<String, Object>> AccountList();
	public void Accountedit(List<HashMap<String, Object>> departmentedit);
	public void Accountadd(String cataname);
}

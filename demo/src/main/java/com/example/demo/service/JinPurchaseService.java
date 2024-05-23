package com.example.demo.service;

import java.util.HashMap;
import java.util.List;

public interface JinPurchaseService {
	public List<HashMap<String, Object>> jinPurchaseList();
	public void purchaseadd (List<HashMap<String, Object>> purchaseData);
}

package com.example.demo.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.service.AccountService;
import com.example.demo.service.BoxService;
import com.example.demo.service.CatagoryService;
import com.example.demo.service.DepartmentService;
import com.example.demo.service.JinPurchaseService;
import com.example.demo.service.ProductService;
import com.example.demo.service.StoreService;

@RestController
public class VueController {
	
	@Autowired
	CatagoryService cataser;
	
	@Autowired
	DepartmentService Deparser;
	
	@Autowired
	AccountService Accser;
	
	@Autowired
	BoxService boxser;
	
	@Autowired
	ProductService proser;
	
	@Autowired
	JinPurchaseService jinPur;
	
	@Autowired
	StoreService stoser;
	//대분류 등록
	@PostMapping("/api/catagoryadd")
	public void catagoreadd(@RequestParam("cataname") String cataname) {
		cataser.catagoryadd(cataname);
		System.out.println(cataname);
	}
	// 대분류 리스트 가져오기
	@GetMapping("/api/catagoryList")
	public List<HashMap<String, Object>> catagory() {
		
		return cataser.CatagoryList();
	}	
	// 대분류 수정
    @PostMapping("/api/catagoryedit")
    public void catagoryedit(@RequestBody List<HashMap<String, Object>> categoryData) {
    	cataser.catagoryedit(categoryData);
    }
  
	//부서 등록
	@PostMapping("/api/department")
	public void departmentadd(@RequestParam("departname") String departname) {
		Deparser.Departmentadd(departname);
		System.out.println(departname);
	}    
    
	// 부서 리스트 가져오기
	@GetMapping("/api/departmentList")
	public List<HashMap<String, Object>> departmentList() {
		
		return Deparser.DepartmentList();
	}
	
	// 부서 수정
    @PostMapping("/api/departmentedit")
    public void departmentedit(@RequestBody List<HashMap<String, Object>> departmentedit) {
    	Deparser.Departmentedit(departmentedit);
    }
    
    // 거래처 리스트
	@GetMapping("/api/accountList")
	public List<HashMap<String, Object>> accountList() {
		
		return Accser.AccountList();
	}
	
	// 거래처 수정
    @PostMapping("/api/accountedit")
    public void accountedit(@RequestBody List<HashMap<String, Object>> departmentedit) {
    	Accser.Accountedit(departmentedit);
    }	
    
	//거래처 등록
	@PostMapping("/api/accountadd")
	public void accountadd(@RequestParam("accountname") String accountname) {
		Accser.Accountadd(accountname);
		System.out.println(accountname);
	}
	
	// 박스 코드 리스트
	@GetMapping("/api/boxList")
	public List<HashMap<String, Object>> boxList() {
		
		return boxser.BoxList();
	}
	
	// 박스 코드 등록
	@PostMapping("/api/boxadd")
	public void boxadd(@RequestParam("boxcode") String boxcode,@RequestParam("boxname") String boxname,@RequestParam("boxcount") int boxcount) {
		System.out.println(boxcode);
		System.out.println(boxname);
		System.out.println(boxcount);
		boxser.Boxadd(boxcode, boxname, boxcount);
	}
	
	// 박스 수정
    @PostMapping("/api/boxedit")
    public void boxedit(@RequestBody List<HashMap<String, Object>>boxeditedit) {
    	boxser.boxedit(boxeditedit);
    }
    
    //제품 마스터 등록
    @PostMapping("/api/masteradd")
    public void addProduct(@RequestBody Map<String, Object> productData) {      
        proser.productadd(productData);
    }
    
    // 제품마스터 리스트
    @PostMapping("/api/searchlist")
    public List<HashMap<String, Object>> searchlist(@RequestBody Map<String, Object> search) {      
    	
    	return proser.productseachlist(search);
    }
    
    // 제품마스터 수정삭제
    @PostMapping("/api/productedit")
	public void productedit(@RequestBody List<HashMap<String, Object>> productData) {
    	proser.productedit(productData);
	}
    
	// 박스 코드 리스트
	@GetMapping("/api/jinPurchaseList")
	public List<HashMap<String, Object>> jinPurchaseList() {
		
		return jinPur.jinPurchaseList();
	}
	
	// 구매,입고 인설트
	@PostMapping("/api/jinPurchaseadd")
	public void jinPurchaseadd(@RequestBody List<HashMap<String, Object>> purchaseData) {
		jinPur.purchaseadd(purchaseData);
	}
	
	// 입고 중 리스트
	@PostMapping("/api/stolist")
	public List<HashMap<String, Object>> stolist() {
		return stoser.storelist();
	}
	
	// 입고 업데이트
	@PostMapping("/api/stoupdate")
	public void stoupdate(@RequestBody List<HashMap<String, Object>> stoData) {
		stoser.stoupdate(stoData);
	}
}


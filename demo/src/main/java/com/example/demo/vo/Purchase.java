package com.example.demo.vo;

import java.util.Date;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@NoArgsConstructor
@ToString
public class Purchase {
	
	
//	-- 구매 테이블 --
//	CREATE TABLE purchase(
//	    purchase_no NUMBER PRIMARY KEY,
//	    category_code VARCHAR2(100) NOT NULL,
//	    product_code VARCHAR2(100) NOT NULL,
//	    account_code VARCHAR2(100) NOT NULL,
//	    box_code VARCHAR2(100) NOT NULL,
//	    purchase_box NUMBER NOT NULL,
//	    purchase_quantity NUMBER NOT NULL,
//	    store_price NUMBER NOT NULL,
//	    purchase_date date NOT NULL,
//	    purchase_situation NUMBER NOT NULL
//	);
	
	private int purchase_no;
	private String category_code;
	private String product_code;
	private String account_code;
	private String box_code;
	private int purchase_box;		// 구매한 박스
	private int purchase_quantity;	// 박스당 수량 x 박스 수량
	private int store_price;
	private Date purchase_date;
	private int purchase_situation;
	
	// 박스
	private String box_name;
	private int box_count;
	
	
	// 마스터
	private String product_name;
	
	// 카테고리
	private String category_name;
	
	// 거래처
	private String account_name;
	
}

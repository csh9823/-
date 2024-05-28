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
public class StoreHistory {
	
	
	// store table
	private int store_no;
	private String category_code;
	private String product_code;
	private String account_code;
	private String box_code;
	private int store_box;
	private int store_quantity;
	private Date store_date;
	private int store_price;
	
	// box quantity table
	private String box_name;
	private int box_count;
	
	
	// 마스터
	private String product_name;
	
	// 카테고리
	private String category_name;
	
	// 거래처
	private String account_name;

}

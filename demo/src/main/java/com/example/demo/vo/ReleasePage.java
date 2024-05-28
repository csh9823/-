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
public class ReleasePage {

	
	// 입고 테이블에서 가져옴
	private int store_no;
	private String category_code;
	private String product_code;
	private String account_code;
	private String box_code;
	private int store_box;
	private int store_quantity;
	private Date store_date;
	private int store_price;
	
	// 제품
	private String product_name;
	
	// box
	private String box_name;
	private int box_count;
	
}

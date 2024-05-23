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
public class Release {

	private String category_value;
	private String product_name;
	private String department_value;
	private String boxcode;
	private int release_quantity;
	private Date release_date;
	private int store_price;
	private int release_price;
	
	// 입고일, 박스 단위, 입고 수량(재고)
	
	private String boxname;
	private int boxcount;
	
	// 입고테이블
	private Date store_date;
	private int store_quantity;
	
}

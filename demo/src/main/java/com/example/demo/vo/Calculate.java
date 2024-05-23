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
public class Calculate {

//	  <th>입고가</th>
//      <th>출고부서</th>
//      <th>출고가격</th>
//      <th>수량</th>
//      <th>수익</th>
	
	// 입고
	private String product_name;
	private String boxcode;
	private String account_value;
	private int store_quantity;
	private int store_price;
	private Date store_date;
	
	// 출고
	private String department_value;
	private int release_quantity;
	private int release_price;
	private Date release_date;
	
	// box
	private int boxcount;
}

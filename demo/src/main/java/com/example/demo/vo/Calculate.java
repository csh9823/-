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
	private String product_code;
	private String box_code;
	private String account_code;
	private int store_quantity;
	private int store_price;
	private Date store_date;
	private int store_box;
	
	// 출고
	private String department_code;
	private int release_quantity;
	private int release_price;
	private Date release_date;
	private int release_box;
	
	// box
	private int box_count;
	
	// 부서
	private String department_name;
	
	// 거래처
	private String account_name;
	
	// 제품
	private String product_name;
	
}

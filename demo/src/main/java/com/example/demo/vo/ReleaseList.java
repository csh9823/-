package com.example.demo.vo;

import java.util.Date;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

// 리스트 페이지 vo
@Setter
@Getter
@NoArgsConstructor
@ToString
public class ReleaseList {

	private int release_no;
	private int store_no;
	private String category_code;
	private String product_code;
	private String department_code;
	private String box_code;
	private int release_box;
	private int release_quantity;
	private Date release_date;
	private int store_price;
	private int release_price;
	
	// 입고일, 박스 단위, 입고 수량(재고)
	
	private String box_name;
	private int box_count;
	
	// 입고테이블
	private Date store_date;
	private int store_quantity;
	
	// 마스터
	private String product_name;
	
	// 카테고리
	private String category_name;
	
	// 부서
	private String department_name;

	
	
	
}

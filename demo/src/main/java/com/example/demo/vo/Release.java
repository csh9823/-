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
	
}

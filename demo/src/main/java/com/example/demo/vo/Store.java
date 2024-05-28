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
public class Store {
	

	// store table
	private int store_no;
	private String category_code;
	private String product_code;
	private String account_code;
	private String box_code;
	private int store_quantity;
	private String store_situation;
	private Date store_date;
	private int store_price;
	
	// box quantity table
	private String boxname;
	private int boxcount;
}

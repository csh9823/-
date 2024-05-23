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
	private String category_value;
	private String product_name;
	private String account_value;
	private String boxcode;
	private int store_quantity;
	private String store_situation;
	private Date store_date;
	private int store_price;
	
	// box quantity table
	private String boxname;
	private int boxcount;
}

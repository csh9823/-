package com.example.demo.vo;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@NoArgsConstructor
@ToString
public class Product {

	private String product_code;
	private String product_name;
	private int app_quantity;
	private String category_code;
	private String account_code;
}

package com.example.demo.vo;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@NoArgsConstructor
@ToString
public class Category {

	public String category_code;
	public String category_name;
	public int category_state;
	
}

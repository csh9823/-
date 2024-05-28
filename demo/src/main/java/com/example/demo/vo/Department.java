package com.example.demo.vo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@NoArgsConstructor
@ToString
public class Department {

	
	private String department_code;
	private String department_hp;
	private String department_name;
	private String department_fax;
	private int department_state;
}

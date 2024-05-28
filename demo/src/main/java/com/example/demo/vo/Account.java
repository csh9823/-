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
public class Account {

	private String account_code;
	private String account_hp;
	private String account_address;
	private String account_name;
	private int account_state;
	private String account_fax;
}

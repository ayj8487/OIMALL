package com.oi.oimall.model;

import java.util.Date;

import lombok.Data;

@Data
public class Member {

	private String user_id;
	private String user_pw;
	private String user_name;
	private String user_sex;
	private Date user_birth;
	private int tell;
	private String user_addr;
	private String user_image;
	private int auth;
	private int status;
	private int grade;
	private Date regdate;
	private String marketing_yn;
	private String push_yn;
	
	
	
	
	
}

package com.oi.oimall.model;

import java.util.Date;

import lombok.Data;

@Data
public class Item {
	private int item_num;
	private String user_id;
	private String cate_code;
	private String item_title;
	private int item_price;
	private String item_image;
	private int item_readcount;
	private Date item_regdate;
	private int item_status;
}

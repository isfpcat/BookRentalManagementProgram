package com.fastcampus.app;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Book {
	private int id;
	private int userId;
	private String code;
	private int price;
	private Date rentDate;
	
	Book(){
		this(0, 0, "test", 0, new Date());
	}
	
	Book(int id, int userId, String code, int price, Date rentDate){
		this.id = id;
		this.userId = userId;
		this.code = code;
		this.price = price;
		this.rentDate = rentDate;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public Date getRentDate() {
		return rentDate;
	}
	public void setRentDate(Date rentDate) {
		this.rentDate = rentDate;
	}
	
	@Override
	public String toString() {
		return "Book [id=" + id + ", userId=" + userId + ", code=" + code + ", price=" + price + ", rentDate="
				+ rentDate + "]";
	}
    public String formatDate(Date date) {
    	return new SimpleDateFormat("yyyy-MM-dd").format(date);
    }
}

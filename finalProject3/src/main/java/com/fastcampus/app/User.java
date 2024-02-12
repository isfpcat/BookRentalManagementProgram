package com.fastcampus.app;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

public class User {
	private int id;
	private String name;
	private String phone;
	private Date joinDate;
	private String email;
	private String grade;
	private int loans;
	
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id 
        	&& Objects.equals(name, user.name) 
        	&& Objects.equals(phone, user.phone) 
        	&& Objects.equals(joinDate, user.joinDate)
        	&& Objects.equals(email, user.email) 
        	&& Objects.equals(grade, user.grade);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, phone, joinDate, email, grade);
    }
    
    public String formatDate(Date date) {
    	return new SimpleDateFormat("yyyy-MM-dd").format(date);
    }
    
	User(){
		this(0, "test", "010-1234-5678", "email@naver.com", "P");
	}
	
	User(int id, String name, String phone, String email, String grade){
		this.id = id;
		this.name = name;
		this.phone = phone;
		this.joinDate = new Date();
		this.email = email;
		this.grade = grade;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public Date getJoinDate() {
		return this.joinDate;
	}
	public void setJoinDate(Date joinDate) {
		this.joinDate = joinDate;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getGrade() {
		return grade;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}
	public int getLoans() {
		return this.loans;
	}
	public void setLoans(int loans) {
		this.loans = loans;
	}
	
	@Override
	public String toString() {
		return "Member [id=" + id + ", name=" + name + ", phone=" + phone + ", joinDate=" + joinDate + ", email="
				+ email + ", grade=" + grade + ", loans=" + loans + "]";
	}
	
}

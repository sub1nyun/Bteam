package com.example.project04_lastproject.customer;

import java.io.Serializable;

public class CustomerVO implements Serializable {
	//통신간에는 데이터가 메모리에 여러부분에 나눠져있으면 전송이 힘듬
	// id = 1, no 3, name 10
	// 11, 12, 13 줄을 맞춰서 보냄
	//intent -> 컴포넌트 간의 통신 Serializable을 사용->강제로 직렬화를 해서 보냄

	private int id, no;
	private String name, gender, email, phone;
	
	
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
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
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	
}

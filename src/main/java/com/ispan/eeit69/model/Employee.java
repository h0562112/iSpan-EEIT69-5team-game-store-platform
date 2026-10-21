package com.ispan.eeit69.model;

import java.io.Serializable;
import java.sql.Clob;
import java.sql.Date;
import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ispan.eeit69.utils.SystemService;

@Entity
@Table(name="employees")
public class Employee implements Serializable{
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)	
	private Integer id;
	private String employeeId; 
	private String name;
	private String account;
	private String password;
	private Date birthday;
	private String email;
	private String gender;
	private Date startDay;
	
	@JsonIgnore
	private Clob picture;
    @Transient  //忽略屬性不會映射到table
    private String image;
    @Transient
    private String filename;
	private Timestamp created_at;
	
	
	public Employee() {
		super();
	}
	
	public Employee(Integer id , String employeeId , String name, String account,
			String password , Date birthday , String email, String gender, Date startDay) {
		this.id = id;
		this.employeeId = employeeId;
		this.name = name;
		this.account = account;
		this.password = password;
		this.birthday = birthday;
		this.email = email;
		this.gender = gender;
		this.startDay = startDay;

	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAccount() {
		return account;
	}

	
	public void setCreated_at(Timestamp created_at) {
		this.created_at = created_at;
	}
	
	public void setAccount(String account) {
		this.account = account;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Date getStartDay() {
		return startDay;
	}

	public void setStartDay(Date startDay) {
		this.startDay = startDay;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Clob getPicture() {
		return picture;
	}

	public void setPicture(Clob picture) {
		this.picture = picture;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}
	
	public String getDataUri() throws Exception {
		return SystemService.clobToString(picture);
	}

	@Override
	public String toString() {
		return "Employee [id=" + id + ", employeeId=" + employeeId + ", name=" + name + ",account" + account
				+ ",password" + password + ", birthday=" + birthday + ", email=" + email + ",gender" + gender  
				+ ",startDay" + startDay + "]";
		
	}
	

	
}

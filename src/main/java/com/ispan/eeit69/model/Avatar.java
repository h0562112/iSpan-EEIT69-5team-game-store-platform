package com.ispan.eeit69.model;

import java.io.Serializable;
import java.sql.Clob;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ispan.eeit69.utils.SystemService;

@Entity
@Table(name = "avatar")
public class Avatar implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
    @JsonIgnore
    private Clob  picture;
    @OneToOne
    @JoinColumn(name = "user_id")  // 這裡指定了外鍵名稱
    private User user;  // 關聯到 User 實體

  
    
    
	public Avatar() {
		super();

	}




	public Avatar(Integer id, Clob picture, User user) {
		super();
		this.id = id;
		this.picture = picture;
		this.user = user;
	}




	public String getDataUri() throws Exception {
		return SystemService.clobToString(picture);
	}




	public Integer getId() {
		return id;
	}




	public void setId(Integer id) {
		this.id = id;
	}




	public Clob getPicture() {
		return picture;
	}




	public void setPicture(Clob picture) {
		this.picture = picture;
	}




	public User getUser() {
		return user;
	}




	public void setUser(User user) {
		this.user = user;
	}




	public static long getSerialversionuid() {
		return serialVersionUID;
	}




	@Override
	public String toString() {
		return "Avatar [id=" + id + ", picture=" + picture + ", user=" + user + "]";
	}









	

}

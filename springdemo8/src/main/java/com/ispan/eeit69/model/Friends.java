package com.ispan.eeit69.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "friends")
public class Friends implements Serializable {

	private static final long serialVersionUID = 1L;
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "friendsID")
    private Integer friendsID;

    @Column(name = "userID1")
    private Integer userID1;

    @Column(name = "userID2")
    private Integer userID2;

    @Column(name = "status")
    private String status;

    // 建構函式、Getter 和 Setter 方法

    public Friends() {
    	super();
    }
  
	public Friends(Integer friendsID, Integer userID1, Integer userID2, String status) {
		super();
		this.friendsID = friendsID;
		this.userID1 = userID1;
		this.userID2 = userID2;
		this.status = status;
	}


	public Integer getFriendsID() {
        return friendsID;
    }

    public void setFriendsID(Integer friendsID) {
        this.friendsID = friendsID;
    }

    public Integer getUserID1() {
        return userID1;
    }

    public void setUserID1(Integer userID1) {
        this.userID1 = userID1;
    }

    public Integer getUserID2() {
        return userID2;
    }

    public void setUserID2(Integer userID2) {
        this.userID2 = userID2;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    
}

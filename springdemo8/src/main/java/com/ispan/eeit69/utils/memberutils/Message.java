package com.ispan.eeit69.utils.memberutils;

import java.io.Serializable;
import java.util.concurrent.atomic.AtomicInteger;



public class Message implements Serializable {

    private static final long serialVersionUID = 1L;

    //发送给指定的uuid,
    private String uuid;
    private String user;
    private String message;
    private String date;
    private String ip;
    private AtomicInteger nums;
    private String currentGroup;
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public AtomicInteger getNums() {
		return nums;
	}
	public void setNums(AtomicInteger nums) {
		this.nums = nums;
	}
	public String getCurrentGroup() {
		return currentGroup;
	}
	public void setCurrentGroup(String currentGroup) {
		this.currentGroup = currentGroup;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}

package com.ispan.eeit69.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Timestamp;

@Entity
@Table(name = "friendsMessages")
public class FriendsMessages {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int messageID;

    private int senderUserid;
    private int receiverUserid;
    private String content;
    private Timestamp timeStamp;
    
    
	public FriendsMessages() {
		super();
	}


	public FriendsMessages(int messageID, int senderUserid, int receiverUserid, String content, Timestamp timeStamp) {
		super();
		this.messageID = messageID;
		this.senderUserid = senderUserid;
		this.receiverUserid = receiverUserid;
		this.content = content;
		this.timeStamp = timeStamp;
	}


	public int getMessageID() {
		return messageID;
	}


	public void setMessageID(int messageID) {
		this.messageID = messageID;
	}


	public int getSenderUserid() {
		return senderUserid;
	}


	public void setSenderUserid(int senderUserid) {
		this.senderUserid = senderUserid;
	}


	public int getReceiverUserid() {
		return receiverUserid;
	}


	public void setReceiverUserid(int receiverUserid) {
		this.receiverUserid = receiverUserid;
	}


	public String getContent() {
		return content;
	}


	public void setContent(String content) {
		this.content = content;
	}


	public Timestamp getTimeStamp() {
		return timeStamp;
	}


	public void setTimeStamp(Timestamp timeStamp) {
		this.timeStamp = timeStamp;
	}

	@Override
	public String toString() {
		return "FriendsMessages [messageID=" + messageID + ", senderUserid=" + senderUserid + ", receiverUserid="
				+ receiverUserid + ", content=" + content + ", timeStamp=" + timeStamp + "]";
	}

}

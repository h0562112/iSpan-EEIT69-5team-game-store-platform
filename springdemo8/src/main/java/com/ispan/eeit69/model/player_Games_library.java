package com.ispan.eeit69.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name="player_Games_library")
public class player_Games_library implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)	
	private Integer playerGameID;
    private Integer  playerID;
    private Integer  gameID;     
    
   
    
	public player_Games_library() {
		super();
	}

	
	

	
	public player_Games_library(Integer playerGameID, Integer playerID, Integer gameID) {
		super();
		this.playerGameID = playerGameID;
		this.playerID = playerID;
		this.gameID = gameID;
	}





	public Integer getPlayerGameID() {
		return playerGameID;
	}





	public void setPlayerGameID(Integer playerGameID) {
		this.playerGameID = playerGameID;
	}





	public Integer getPlayerID() {
		return playerID;
	}





	public void setPlayerID(Integer playerID) {
		this.playerID = playerID;
	}





	public Integer getGameID() {
		return gameID;
	}





	public void setGameID(Integer gameID) {
		this.gameID = gameID;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}


	@Override
	public String toString() {
		return "player_Games_library [playerGameID=" + playerGameID + ", playerID=" + playerID + ", gameID=" + gameID
				+ "]";
	}    
}

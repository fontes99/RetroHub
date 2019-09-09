package br.edu.insper.model;

import java.sql.Timestamp;

public class Message {
	
	private String msg;
	private int idUser;
	private Timestamp time;
	
	public Message() {
		
	}
	
	public String getMsg() {
		return this.msg;
	}
	
	public void setMsg(String msg) {
		this.msg = msg;
	}
	
	public int getIdUser() {
		return this.idUser;
	}
	
	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}
	
	public Timestamp getTime() {
		return this.time;
	}
	
	public void setTime(Timestamp time) {
		this.time = time;
	}
}

package br.edu.insper.model;

public class Subject {
	
	private int id;
	private String subject;
	private String url;
	
	public Subject() {
	}
	
	public int getId() {
		return this.id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getSubject() {
		return this.subject;
	}
	
	public void setURL(String url) {
		if (url.contains(" ")) {
			url = url.replace(" ", "_");
		}
		this.url = url;
	}
	
	public String getURL() {
		return this.url;
	}
	
	public void setSubject(String subject) {
		this.subject = subject;
	}

}

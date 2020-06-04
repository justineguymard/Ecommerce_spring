package fr.adaming.entities;

import java.io.Serializable;

public class Admin implements Serializable{
	
	private int id;
	
	private String mail;
	
	private String mdp;

	public Admin() {
		super();
	}

	public Admin(String mail, String mdp) {
		super();
		this.mail = mail;
		this.mdp = mdp;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getMdp() {
		return mdp;
	}

	public void setMdp(String mdp) {
		this.mdp = mdp;
	}

	@Override
	public String toString() {
		return "Admin [id=" + id + ", mail=" + mail + ", mdp=" + mdp + "]";
	} 
	
	
	

}

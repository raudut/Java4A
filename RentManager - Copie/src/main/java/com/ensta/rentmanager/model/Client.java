package com.ensta.rentmanager.model;

import java.sql.Date ;

public class Client {
	private int id;
	private String nom;
	private String prenom;
	private String email;
	private Date naissance;
	
	public Client() {
	}
	
	public Client(int id, String nom, String prenom, String email, Date naissance) {
		this.id=id;
		this.nom=nom;
		this.prenom=prenom;
		this.email=email;
		this.naissance=naissance;
	}
	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getNaissance() {
		return naissance;
	}

	public void setNaissance(Date naissance) {
		this.naissance = naissance;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	
	
	public int getId() {
		return id;
	}

	@Override
	public String toString() {
		return "Client id=" + id + ", nom=" + nom + ", prenom=" + prenom + ", email=" + email + ", naissance="
				+ naissance;
	}
	

	
}

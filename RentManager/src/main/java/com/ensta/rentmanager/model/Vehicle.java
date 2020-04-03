package com.ensta.rentmanager.model;

public class Vehicle {
	
	public Vehicle() {
		
	}

	public Vehicle (int id, String manufacturer, String modele, int seats){
		this.id=id;
		this.manufacturer=manufacturer;
		this.modele=modele;
		this.seats=seats;
	}
	
	private int id;
	private String manufacturer;
	private String modele;
	private int seats;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getManufacturer() {
		return manufacturer;
	}
	public void setManufacturer(String constructeur) {
		this.manufacturer = constructeur;
	}
	public String getModele() {
		return modele;
	}
	public void setModele(String modele) {
		this.modele = modele;
	}
	public int getSeats() {
		return seats;
	}
	public void setSeats(int seats) {
		this.seats = seats;
	}
	@Override
	public String toString() {
		return "Vehicle [id=" + id + ", constructeur=" + manufacturer + ", modele="+ modele+", nb_places="
				+ seats + "]";
	}
	
	
	
}

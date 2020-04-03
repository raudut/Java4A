package com.ensta.rentmanager.controllerVehicle;

import java.util.List;
import java.util.Scanner;

import com.ensta.rentmanager.exception.DaoException;
import com.ensta.rentmanager.exception.ServiceException;
import com.ensta.rentmanager.model.Vehicle;
import com.ensta.rentmanager.service.VehicleService;

public class VehController {
	private VehicleService vehicleService= VehicleService.getInstance();
	
	public static void main (String[] args){
		VehController veh = new VehController();
		Scanner sc=new Scanner(System.in);
		boolean done=false;
		
		while(!done) 
	{
			
			System.out.println("Liste des opérations");
			System.out.println("0- Quitter");
			System.out.println("1- Afficher la liste des véhicules");
			System.out.println("2- Ajouter un vehicule");
			System.out.println("3- Supprimer un véhicule");
			System.out.println("4- Trouver un vehicule");
			System.out.println("5- Trouver un vehicule du nom de:");
			
			
			int choix = sc.nextInt();
			sc.nextLine();
			
			switch(choix) {
			
			case 1:
				printAllVehicle(veh);				
				break;
			case 2:
				ajouterVehicule(veh, sc);
				break;
			case 3:
				supprimerVehicule(veh, sc);
				break;
			case 4:
				findVehicule(veh, sc);
				break;
			case 5:
				findVehiculeByName(veh, sc);
				break;
			
			default: 
				System.out.println("Pas le bon choix");
			}
		} sc.close();
	}
	private static void findVehicule(VehController veh, Scanner sc) {
		System.out.println("Trouver le véhicule dont l'id est:");
		try {
			int id=sc.nextInt();
			Vehicle v=veh.vehicleService.findById(id);
			System.out.println(v.toString());
		}catch(ServiceException | DaoException e)
		{
			e.printStackTrace();
		};
	}
	
	private static void findVehiculeByName(VehController veh, Scanner sc) {
		System.out.println("Trouver le véhicule dont le nom est:");
		try {
			String id=sc.nextLine();
			Vehicle v=veh.vehicleService.findByName(id);
			System.out.println(v.toString());
		}catch(ServiceException | DaoException e)
		{
			e.printStackTrace();
		};
	}


	private static void supprimerVehicule(VehController veh, Scanner sc) {
		System.out.println("Supprimer le véhicule n°");
		int id=sc.nextInt();
		try {
			veh.vehicleService.delete(id);
		}catch(ServiceException e) {
			e.printStackTrace();
		}
	}


	private static void ajouterVehicule(VehController veh, Scanner sc) {
		Vehicle v=new Vehicle();
		System.out.println("Entrez le constructeur");
		v.setManufacturer(sc.nextLine());
		System.out.println("Entrez le modele");
		v.setModele(sc.nextLine());
		System.out.println("Entrez le nombre de place");
		v.setSeats(sc.nextInt());
		try {
			
			
			veh.vehicleService.create(v);
			
		}catch(ServiceException e) {
			e.printStackTrace();
		}
	}


	private static void printAllVehicle(VehController veh) {
		try {
			List<Vehicle> list = veh.vehicleService.findAll();
			
			for(Vehicle vehicle : list) { 
				System.out.println(vehicle);
			}
			
		}catch (ServiceException e) {
			System.out.println("Une erreur est survenue : " + e.getMessage());
		}
	}
}

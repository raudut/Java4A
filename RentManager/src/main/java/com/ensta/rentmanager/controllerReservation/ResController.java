package com.ensta.rentmanager.controllerReservation;

import java.sql.Date;
import java.util.List;
import java.util.Scanner;

import com.ensta.rentmanager.controllerClient.CliControler;
import com.ensta.rentmanager.exception.ServiceException;
import com.ensta.rentmanager.model.Reservation;
import com.ensta.rentmanager.service.ClientService;
import com.ensta.rentmanager.service.ReservationService;
import com.ensta.rentmanager.service.VehicleService;

public class ResController {
	private ReservationService resaService= ReservationService.getInstance();
		
	public static void main (String[] args){
		ResController res = new ResController();
		Scanner sc=new Scanner(System.in);
		boolean done=false;
		
		
		while(!done) 
		{
				
				System.out.println("Liste des opérations");
				System.out.println("0- Quitter");
				System.out.println("1- Créer une reservation");
				System.out.println("2- Supprimer une reservation");
				System.out.println("3- Trouver les reservations d'un client");
				System.out.println("4- Trouver les reservations d'un véhicule");
				System.out.println("5- Afficher toutes les reservations");
				
				int choix = sc.nextInt();
				sc.nextLine();
				
				switch(choix) {
				case 0:
					done = true;
					break;
				
				case 1:
					createResa(res, sc);
					break;
				case 2:
					deleteReservation(res, sc);
					break;
				case 3:
					findResaByClient(res, sc);
					break;
				case 4:
					findResaByVehicule(res, sc);
					break;
				case 5:
					printAllReservations(res);
					break;
				default: 
					System.out.println("Pas le bon choix");
				}
			} sc.close();
	}
			private static void findResaByVehicule(ResController res, Scanner sc) {
				System.out.println("Trouver les reservations du vehicule n° :");
				int idv=sc.nextInt();
				try {
					List<Reservation>list= res.resaService.findByVehicule(idv);
					
					for (Reservation resa : list) {
						System.out.println(resa);
					}
				}catch(ServiceException e) {
					e.printStackTrace();
				}
			}


			private static void findResaByClient(ResController res, Scanner sc) {
				System.out.println("Trouver les reservations du client n° :");
				int idc=sc.nextInt();
				try {
				 	List<Reservation> list = res.resaService.findByClient(idc);
				 	for(Reservation resa : list) {
				 		System.out.println(resa);
				 	}
				}catch(ServiceException e) {
					e.printStackTrace();
				}
			}


			private static void deleteReservation(ResController res, Scanner sc) {
				System.out.println("Supprimer la n°");
				int id=sc.nextInt();
				try {
					res.resaService.delete(id);
				}catch(ServiceException e) {
					e.printStackTrace();
				}
			}


			private static void printAllReservations(ResController res) {
				try {
					List<Reservation> list = res.resaService.findAll();
					
					for(Reservation resa : list) { 
						System.out.println(resa);
					}
					
				}catch (ServiceException e) {
					System.out.println("Une erreur est survenue : " + e.getMessage());
				}
			}


			private static void createResa(ResController res, Scanner sc) {
				Reservation resa = new Reservation();
				System.out.println("Entrez l'id du client");
				resa.setClient_id(sc.nextInt());
				System.out.println("Entrez l'id du véhicule");
				resa.setVehicle_id(sc.nextInt());	
				sc.nextLine();
				System.out.println();
				System.out.println("Entrez la date de début au format yyyy-[m]m-[d]d");
				resa.setDebut(Date.valueOf(sc.nextLine()));
				System.out.println("Entrez la date de fin au format yyyy-mm-dd");
				resa.setFin(Date.valueOf(sc.nextLine()));
				
				try {
					res.resaService.create(resa);
				}catch(ServiceException e) {
					e.printStackTrace();
				}
			}

		

}


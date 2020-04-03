package com.ensta.rentmanager.controllerClient;

import java.sql.Date;
import java.util.List;
import java.util.Scanner;

import com.ensta.rentmanager.service.ClientService;
import com.ensta.rentmanager.model.*;
import com.ensta.rentmanager.exception.*; 

public class CliControler {
	private ClientService clientService= ClientService.getInstance();
	
	
	public static void main (String[] args){
		CliControler cli = new CliControler();
		Scanner sc=new Scanner(System.in);
		boolean done=false;
		
		while(!done) 
	{
			
			System.out.println("Liste des opérations");
			System.out.println("0- Quitter");
			System.out.println("1- Afficher la liste des clients");
			System.out.println("2- Ajouter un client");
			System.out.println("3- Actualiser un client");
			System.out.println("4- Supprimer un client");
			System.out.println("5- Trouver un client par id");
			System.out.println("6- Trouver un client par nom");
			
			
			
			int choix = sc.nextInt();
			sc.nextLine();
			
			switch(choix) {
			case 0:
				done = true;
				break;
			case 1:
				printAllClient(cli);			
				break;
			case 2:
				ajouterClient(cli, sc);
				break;
			case 3:
				updateClient(cli, sc);
				break;
			case 4:
				supprimerClient(cli, sc);				
				break;
			case 5:
				clientByID(cli, sc);
				break;				
			case 6:
				clientByNom(cli, sc);
				break;
			
			
			default: 
				System.out.println("Pas le bon choix");
			}
		} sc.close();
	}


	

	


	private static void clientByID(CliControler cli, Scanner sc) {
		System.out.println("Trouver le client n°");
		try {
			int id=sc.nextInt();
			Client c=cli.clientService.findById(id);
			System.out.println(c.toString());
		}catch(ServiceException e)
		{
			e.printStackTrace();
		}
	}
	
	private static void clientByNom(CliControler cli, Scanner sc) {
		System.out.println("Trouver le client ");
		try {
			String nom=sc.nextLine();
			Client c=cli.clientService.findByName(nom);
			System.out.println(c.toString());
		}catch(ServiceException e)
		{
			e.printStackTrace();
		}
	}


	private static void supprimerClient(CliControler cli, Scanner sc) {
		System.out.println("Supprimer le client n° :");
		try {
			int id=sc.nextInt();
			cli.clientService.delete(id);
		}catch(ServiceException e) {
			e.printStackTrace();
		}
	}


	private static void ajouterClient(CliControler cli, Scanner sc) {
		Client client = new Client();
		System.out.println("Entrez le nom");
		client.setNom(sc.nextLine());
		System.out.println("Entrez le prénom");
		client.setPrenom(sc.nextLine());
		System.out.println("Entrez l'email");
		client.setEmail(sc.nextLine());
		System.out.println("Entrez la date de naissance au format yyyy-[m]m-[d]d");
		client.setNaissance(Date.valueOf(sc.nextLine()));	

		try {
			
			cli.clientService.create(client);
		}catch(ServiceException e) {
			e.printStackTrace();
		}
	}
	
	private static void updateClient(CliControler cli,  Scanner sc)  {
		Client client = new Client();
		System.out.println("Entrez l'id");
		client.setId(sc.nextInt());
		sc.nextLine();
		System.out.println("Entrez le nom");
		client.setNom(sc.nextLine());
		System.out.println("Entrez le prénom");
		client.setPrenom(sc.nextLine());
		System.out.println("Entrez l'email");
		client.setEmail(sc.nextLine());
		System.out.println("Entrez la date de naissance au format yyyy-[m]m-[d]d");
		client.setNaissance(Date.valueOf(sc.nextLine()));	
		
		
		try {
			
			cli.clientService.update(client);
		}catch(ServiceException e) {
			e.printStackTrace();
		}
	}
		

	private static void printAllClient(CliControler cli) {
		try {
			List<Client> list = cli.clientService.findAll();
			
			for(Client client : list) { 
				System.out.println(client);
			}
			
		}catch (ServiceException e) {
			System.out.println("Une erreur est survenue : " + e.getMessage());
		}
	}
}

package com.ensta.rentmanager.controllerClient;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ensta.rentmanager.exception.DaoException;
import com.ensta.rentmanager.exception.ServiceException;
import com.ensta.rentmanager.model.Client;
import com.ensta.rentmanager.model.Reservation;
import com.ensta.rentmanager.model.Vehicle;
import com.ensta.rentmanager.service.ClientService;
import com.ensta.rentmanager.service.ReservationService;
import com.ensta.rentmanager.service.VehicleService;

@WebServlet("/users/details")
public class DetailsClients extends HttpServlet {
	ClientService clientService = ClientService.getInstance();
	ReservationService reservationservice=ReservationService.getInstance();
	VehicleService vehiculeService= VehicleService.getInstance();
	private static final long serialVersionUID=1L;
	
	protected void doGet (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	RequestDispatcher dispatcher = 
			request.getRequestDispatcher("/WEB-INF/views/users/details.jsp");
		int id=Integer.parseInt(request.getParameter("id"));
	System.out.println("Id" + id);
	try {
		List<Reservation>	resa =  new ArrayList<Reservation>();
	
		Client c= clientService.findById(id);
		if(reservationservice.findByClient(c.getId()) != null) { 
	resa.addAll(  reservationservice.findByClient(c.getId()));
		}

		else {System.out.println("Aucune reservation");
		
		}
		
		
	List<Vehicle> vehRes= new ArrayList<Vehicle>();
	List<Vehicle> veh= new ArrayList<Vehicle>();
	
	if (resa !=null) {
	for(Reservation  r : resa )
	{
		Vehicle v = vehiculeService.findById(r.getVehicle_id());
		vehRes.add(vehiculeService.findById(r.getVehicle_id())); 
		 
		 if(veh.contains(v) == false) {
			 veh.add(vehiculeService.findById(r.getVehicle_id())); 
		 }
		 else {System.out.println("déja present");}
	}
	}
		request.setAttribute("nomUtilisateur", c.getNom());
		request.setAttribute("prenomUtilisateur", c.getPrenom());
		request.setAttribute("vehicules", veh);
		request.setAttribute("reservations", resa);
		request.setAttribute("nbreservations", resa.size());
		request.setAttribute("nbvoiture", vehRes.size());
	}
	catch(ServiceException e) {
		request.setAttribute("nomUtilisateur", "Une erreur est survenue");
		request.setAttribute("prenomUtilisateur", "Une erreur est survenue");
		request.setAttribute("nbreservation", "Une erreur est survenue");
		request.setAttribute("nbvoiture", "Une erreur est survenue");
	} catch (DaoException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	
	}
	
dispatcher.forward(request, response);
		
		
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		doGet(request, response);
	}
}
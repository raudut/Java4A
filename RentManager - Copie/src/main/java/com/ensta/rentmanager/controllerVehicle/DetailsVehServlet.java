package com.ensta.rentmanager.controllerVehicle;

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

@WebServlet("/cars/details")
public class DetailsVehServlet extends HttpServlet{
	ClientService clientService = ClientService.getInstance();
	ReservationService reservationservice=ReservationService.getInstance();
	VehicleService vehService= VehicleService.getInstance();
	private static final long serialVersionUID=1L;
	
	protected void doGet (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	RequestDispatcher dispatcher = 
			request.getRequestDispatcher("/WEB-INF/views/cars/details.jsp");
		int id=Integer.parseInt(request.getParameter("id"));
	
	try {
		Vehicle v= vehService.findById(id);
		
		List<Reservation> resa = new ArrayList<Reservation>();
		resa.addAll(reservationservice.findByVehicule(v.getId()));
		
		List<Client> client = new ArrayList<Client>();
	//	List<Client> cli = new ArrayList<Client>;
		for(Reservation r : resa) {
			Client c = new Client();
			c=clientService.findById(r.getClient_id());
			if(client.contains(c) == false) {
				client.add(c);
		}
		}
		
		request.setAttribute("consVehicule", v.getManufacturer());
		request.setAttribute("modVehicule", v.getModele());
		request.setAttribute("nbPlaces", v.getSeats());
		request.setAttribute("clients", client);
		request.setAttribute("nbclients", client.size());
		request.setAttribute("reservations", resa);
		request.setAttribute("nbreservations", resa.size());
		
	}
	catch(ServiceException | DaoException e) {
		request.setAttribute("nomUtilisateur", "Une erreur est survenue");
		request.setAttribute("prenomUtilisateur", "Une erreur est survenue");
		request.setAttribute("nbreservation", "Une erreur est survenue");
	}

	
dispatcher.forward(request, response);
		
		
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		doGet(request, response);
	}

}

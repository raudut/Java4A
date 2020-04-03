package com.ensta.rentmanager.controllerReservation;

import java.io.IOException;

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

@WebServlet("/rents/details")
public class DetailsResServlet extends HttpServlet {
	ClientService clientService = ClientService.getInstance();
	ReservationService reservationservice=ReservationService.getInstance();
	VehicleService vehiculeService= VehicleService.getInstance();
	private static final long serialVersionUID=1L;
	
	protected void doGet (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	RequestDispatcher dispatcher = 
			request.getRequestDispatcher("/WEB-INF/views/rents/details.jsp");
		int id=Integer.parseInt(request.getParameter("id"));
	try {
	Reservation res = reservationservice.findById(id);
	
	Vehicle v = vehiculeService.findById(res.getVehicle_id());
	Client c = clientService.findById(res.getClient_id());

	
		request.setAttribute("reservation_id", id);
		request.setAttribute("debut", res.getDebut());
		request.setAttribute("fin", res.getFin());
		request.setAttribute("clients_id", c.getId());
		request.setAttribute("clients_nom", c.getNom());
		request.setAttribute("clients_prenom", c.getPrenom());
		request.setAttribute("clients_email", c.getEmail());
		request.setAttribute("voiture_id", v.getId());
		request.setAttribute("voiture_manufacturer", v.getManufacturer());
		request.setAttribute("voiture_modele", v.getModele());
		request.setAttribute("voiture_seats", v.getSeats());
		
	}
	catch(ServiceException e) {
		request.setAttribute("reservation_id", "Une erreur est survenue");
		request.setAttribute("client_nom", "Une erreur est survenue");
		request.setAttribute("client_prenom", "Une erreur est survenue");
		request.setAttribute("client_email", "Une erreur est survenue");
		request.setAttribute("voiture_manufacturer", "Une erreur est survenue");
		request.setAttribute("voiture_modele", "Une erreur est survenue");
		request.setAttribute("voiture_seats", "Une erreur est survenue");
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
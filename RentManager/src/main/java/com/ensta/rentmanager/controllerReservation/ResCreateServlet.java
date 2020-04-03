package com.ensta.rentmanager.controllerReservation;

import java.io.IOException;
import java.sql.Date;

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
import com.ensta.rentmanager.service.ClientService;
import com.ensta.rentmanager.service.ReservationService;
import com.ensta.rentmanager.service.VehicleService;

@WebServlet("/rents/create")
public class ResCreateServlet extends HttpServlet{
ReservationService resaService = ReservationService.getInstance();
	ClientService clientService = ClientService.getInstance();
	VehicleService vehService = VehicleService.getInstance();
	private static final long serialVersionUID = 1L;
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/rents/create.jsp");
		//Recuperer les clients pour faire une liste déroulante
				try {
					request.setAttribute("clients", clientService.findAll());
				} catch (ServiceException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				
				//Récuperer les vehicules pour faire une liste déroulante
				try {
					request.setAttribute("cars", vehService.findAll());
				} catch (ServiceException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
		dispatcher.forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher ;
		//Récuperer les noms des variables
		int client_id =Integer.parseInt(request.getParameter("client"))    ;
		int veh_id = Integer.parseInt(request.getParameter("car"));
		Date debut = Date.valueOf(request.getParameter("begin"));
		Date fin = Date.valueOf(request.getParameter("end"));
		
		//Creer une nouvelle reservation
		Reservation newResa = new Reservation();
		newResa.setClient_id(client_id);
		newResa.setVehicle_id(veh_id);
		newResa.setDebut(debut);
		newResa.setFin(fin);
		
		
		try {
			resaService.create(newResa);
			dispatcher = request.getRequestDispatcher("/WEB-INF/views/home.jsp");
		} catch (ServiceException e) {
			System.out.println(e.getMessage());
			dispatcher = request.getRequestDispatcher("/WEB-INF/views/rents/create.jsp");
		}
		dispatcher.forward(request,response);
	}
}

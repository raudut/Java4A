package com.ensta.rentmanager.controllerClient;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ensta.rentmanager.exception.ServiceException;
import com.ensta.rentmanager.service.ClientService;
import com.ensta.rentmanager.service.ReservationService;
import com.ensta.rentmanager.service.VehicleService;

@WebServlet("/home")
public class HomeServlet extends HttpServlet {
	
	ClientService clientservice = ClientService.getInstance();
	VehicleService vehicleservice=VehicleService.getInstance();
	ReservationService reservationservice=ReservationService.getInstance();
			
	private static final long serialVersionUID=1L;
	
	protected void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException{
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/home.jsp");
		try {
			request.setAttribute("nbutilisateurs", clientservice.findAll().size());
			request.setAttribute("nbvoitures", vehicleservice.findAll().size());
			request.setAttribute("nbreservations", reservationservice.findAll().size());
		}catch (ServiceException e){
			request.setAttribute("nbutilisateurs", "une erreur est survenue");
			
		}
		dispatcher.forward(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException{
		doGet(request,response);
	}

}

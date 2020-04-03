package com.ensta.rentmanager.controllerReservation;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ensta.rentmanager.exception.ServiceException;
import com.ensta.rentmanager.service.ReservationService;


@WebServlet("/rents")
public class ResListServlet extends HttpServlet{
	ReservationService resaService = ReservationService.getInstance();
	
	private static final long serialVersionUID=1L;
	
	protected void doGet (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/rents/list.jsp");
		try {
			request.setAttribute("rents", resaService.findAll());
		} catch (ServiceException e) {
			request.setAttribute("nbRents", "Une erreur est survenue");
		}
		dispatcher.forward(request, response);
		
		
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		doGet(request, response);
	}
}

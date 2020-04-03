package com.ensta.rentmanager.controllerReservation;

import java.io.IOException;
import java.sql.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.JOptionPane;

import com.ensta.rentmanager.exception.DaoException;
import com.ensta.rentmanager.exception.ServiceException;
import com.ensta.rentmanager.model.Reservation;
import com.ensta.rentmanager.service.ReservationService;

@WebServlet("/rents/change")
public class UpdateResaServlet extends HttpServlet{
	int id;
	ReservationService resaService = ReservationService.getInstance();
	private static final long serialVersionUID = 1L;
	protected void doGet (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/rents/change.jsp");
	id= Integer.parseInt(request.getParameter("id"));			
				
					Reservation r;
					try {
						r = resaService.findById(id);
						request.setAttribute("client", r.getClient_id());
						request.setAttribute("voiture", r.getVehicle_id());
						request.setAttribute("debut", r.getDebut());
						request.setAttribute("fin", r.getFin());
					} catch (ServiceException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (DaoException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
		dispatcher.forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
	   
		Reservation r = new Reservation();
		String client = request.getParameter("client");
		String voiture = request.getParameter("voiture");
		Date debut = Date.valueOf(request.getParameter("debut")) ;
		Date fin = Date.valueOf(request.getParameter("fin")) ;
		
			r.setId(id);
			r.setClient_id(Integer.parseInt(client));
			r.setVehicle_id(Integer.parseInt(voiture));
			r.setDebut(debut);
			r.setFin(fin);
		
		RequestDispatcher dispatcher;
		
	
		try {
			resaService.update(r);
			dispatcher = request.getRequestDispatcher("/WEB-INF/views/rents/change.jsp");
			
	} catch (ServiceException e) {
		JOptionPane.showConfirmDialog(null, e.getMessage(), "Information", JOptionPane.CANCEL_OPTION);
		e.printStackTrace();
	}
		dispatcher = request.getRequestDispatcher("/WEB-INF/views/home.jsp");
		dispatcher.forward(request,response);
	}


}





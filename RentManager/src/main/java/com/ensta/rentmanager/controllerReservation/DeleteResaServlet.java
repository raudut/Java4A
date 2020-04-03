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
import com.ensta.rentmanager.model.Reservation;
import com.ensta.rentmanager.service.ReservationService;

@WebServlet("/rents/delete")
public class DeleteResaServlet extends HttpServlet {
	ReservationService resaservice = ReservationService.getInstance();
	private static final long serialVersionUID = 1L;
	protected void doGet (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/rents/delete.jsp");
		int id = Integer.parseInt(request.getParameter("id"));
		Reservation r;
		
			try {
				r = resaservice.findById(id);
				resaservice.delete(r.getId());
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
		doGet(request, response);
	}
}

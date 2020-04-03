package com.ensta.rentmanager.controllerVehicle;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.JOptionPane;

import com.ensta.rentmanager.exception.ServiceException;
import com.ensta.rentmanager.model.Vehicle;
import com.ensta.rentmanager.service.VehicleService;


@WebServlet("/cars/create")
public class VehCreateServlet extends HttpServlet {
	
VehicleService vehService = VehicleService.getInstance();
	
	private static final long serialVersionUID = 1L;
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/cars/create.jsp");
		dispatcher.forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
		
		String manufacturer = request.getParameter("manufacturer");
		String modele = request.getParameter("modele");
		int seats = Integer.parseInt(request.getParameter("seats"));
		
		
		Vehicle newVeh = new Vehicle();
		newVeh.setManufacturer(manufacturer);
		newVeh.setModele(modele);
		newVeh.setSeats(seats);
		RequestDispatcher dispatcher ;
		
		try {
			vehService.create(newVeh);
			dispatcher = request.getRequestDispatcher("/WEB-INF/views/home.jsp");
		} catch (ServiceException e) {
			JOptionPane.showConfirmDialog(null, e.getMessage(), "Information", JOptionPane.CANCEL_OPTION);
			dispatcher = request.getRequestDispatcher("/WEB-INF/views/cars/create.jsp");
		}
		dispatcher.forward(request,response);
	}

}

package com.ensta.rentmanager.controllerVehicle;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.JOptionPane;

import com.ensta.rentmanager.exception.DaoException;
import com.ensta.rentmanager.exception.ServiceException;
import com.ensta.rentmanager.model.Vehicle;
import com.ensta.rentmanager.service.VehicleService;

@WebServlet("/cars/change")
public class UpdateVehServlet extends HttpServlet{
	int id;
	VehicleService vehService = VehicleService.getInstance();
	private static final long serialVersionUID = 1L;
	protected void doGet (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/cars/change.jsp");
	id= Integer.parseInt(request.getParameter("id"));
				
				
					Vehicle v;
					try {
						v = vehService.findById(id);
						request.setAttribute("idVehicule", v.getId());
						request.setAttribute("marque", v.getManufacturer());
						request.setAttribute("modele",v.getModele());
						request.setAttribute("seats", v.getSeats());
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
	
		String marque = request.getParameter("marque");
		String modele = request.getParameter("modele");
		String seats = request.getParameter("seats");
		Vehicle v = new Vehicle();
		v.setId(id);
		v.setManufacturer(marque);
		v.setModele(modele);
		v.setSeats(Integer.parseInt(seats));
		System.out.println(v);
		RequestDispatcher dispatcher;
	
		try {
			vehService.update(v);
			
	} catch (ServiceException e) {
		JOptionPane.showConfirmDialog(null, e.getMessage(), "Information", JOptionPane.CANCEL_OPTION);
		dispatcher = request.getRequestDispatcher("/WEB-INF/views/cars/change.jsp");
	}
		dispatcher = request.getRequestDispatcher("/WEB-INF/views/home.jsp");
		dispatcher.forward(request,response);
	}

}

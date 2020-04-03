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

@WebServlet("/cars/delete")
public class DeleteVehServlet extends HttpServlet {
	VehicleService vehservice = VehicleService.getInstance();
	private static final long serialVersionUID = 1L;
	protected void doGet (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/cars/delete.jsp");
		int id = Integer.parseInt(request.getParameter("id"));
		Vehicle v;
		
			try {
				v = vehservice.findById(id);
				vehservice.delete(v.getId());
			} catch (ServiceException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (DaoException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			JOptionPane.showConfirmDialog(null, "Les reservations associées ont été supprimées", "Information", JOptionPane.CANCEL_OPTION);
		dispatcher.forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		doGet(request, response);
	}
}

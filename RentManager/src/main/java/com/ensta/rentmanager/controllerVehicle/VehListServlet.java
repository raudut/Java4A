package com.ensta.rentmanager.controllerVehicle;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ensta.rentmanager.exception.ServiceException;
import com.ensta.rentmanager.service.VehicleService;

@WebServlet("/cars")
public class VehListServlet extends HttpServlet{



VehicleService vehService = VehicleService.getInstance();
		private static final long serialVersionUID=1L;
		
		protected void doGet (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/cars/list.jsp");
			try {
				request.setAttribute("vehicles", vehService.findAll());
			} catch (ServiceException e) {
				request.setAttribute("nbVehicle", "Une erreur est survenue");
			}
			dispatcher.forward(request, response);
			
			
		}
		protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
			doGet(request, response);
		}
	}


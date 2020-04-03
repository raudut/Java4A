package com.ensta.rentmanager.controllerClient;

import java.io.IOException;
import java.sql.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.JOptionPane;

import com.ensta.rentmanager.model.Client;
import com.ensta.rentmanager.exception.ServiceException;
import com.ensta.rentmanager.service.ClientService;

@WebServlet("/users/create")
public class ClientCreateServlet extends HttpServlet{
	ClientService clientservice = ClientService.getInstance();
	
	private static final long serialVersionUID = 1L;
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/users/create.jsp");
		dispatcher.forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
		
		String last_name = request.getParameter("last_name");
		String first_name = request.getParameter("first_name");
		String email = request.getParameter("email");
		Date birthdate = Date.valueOf(request.getParameter("birthdate"));
		
		
		Client newClient = new Client();
		newClient.setEmail(email);
		newClient.setNom(last_name);
		newClient.setPrenom(first_name);
		newClient.setNaissance(birthdate);
		RequestDispatcher dispatcher ;
		
		try {
			clientservice.create(newClient);
			dispatcher = request.getRequestDispatcher("/WEB-INF/views/home.jsp");
			dispatcher.forward(request,response);
			
		} catch (ServiceException e) {
			JOptionPane.showConfirmDialog(null, e.getMessage(), "Information", JOptionPane.OK_OPTION);
			dispatcher = request.getRequestDispatcher("/WEB-INF/views/users/list.jsp");
		}
		
	}

}
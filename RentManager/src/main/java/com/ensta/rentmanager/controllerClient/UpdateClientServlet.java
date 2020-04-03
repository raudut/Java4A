package com.ensta.rentmanager.controllerClient;

import java.io.IOException;
import java.sql.Date;
import java.util.Optional;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ensta.rentmanager.exception.DaoException;
import com.ensta.rentmanager.exception.ServiceException;
import com.ensta.rentmanager.model.Client;
import com.ensta.rentmanager.service.ClientService;

@WebServlet("/users/change")

public class UpdateClientServlet extends HttpServlet{
	int id;
	ClientService clientservice = ClientService.getInstance();
	private static final long serialVersionUID = 1L;
	protected void doGet (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/users/change.jsp");
	id= Integer.parseInt(request.getParameter("id"));
		
			
				try {
					Client c = clientservice.findById(id);
					request.setAttribute("idUtilisateur", c.getId());
					request.setAttribute("nomUtilisateur", c.getNom());
					request.setAttribute("prenomUtilisateur", c.getPrenom());
					request.setAttribute("emailUtilisateur", c.getEmail());
					request.setAttribute("naissanceUtilisateur", c.getNaissance());
				} catch (ServiceException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}			
			
			
		dispatcher.forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
	//	int id = Integer.parseInt(request.getParameter("id"));
		String last_name = request.getParameter("last_name");
		String first_name = request.getParameter("first_name");
		String email = request.getParameter("email");
		Date birthdate = Date.valueOf(request.getParameter("birthdate"));
		Client c = new Client();
		c.setId(id);
		c.setEmail(email);
		c.setNom(last_name);
		c.setPrenom(first_name);
		c.setNaissance(birthdate);
		RequestDispatcher dispatcher ;
	
		try {
			clientservice.update(c);
			
	} catch (ServiceException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
		dispatcher = request.getRequestDispatcher("/WEB-INF/views/home.jsp");
		dispatcher.forward(request,response);
	}

}

package com.ensta.rentmanager.controllerClient;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.JOptionPane;

import com.ensta.rentmanager.exception.ServiceException;
import com.ensta.rentmanager.model.Client;
import com.ensta.rentmanager.service.ClientService;

@WebServlet("/users/delete")
public class SupprimerClientServlet extends HttpServlet {
	ClientService clientservice = ClientService.getInstance();
	private static final long serialVersionUID = 1L;
	protected void doGet (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/users/delete.jsp");
		int id_client = Integer.parseInt(request.getParameter("id"));
		Client c;
		try {
			c = clientservice.findById(id_client);
			clientservice.delete(c.getId());
		} catch (ServiceException e) {
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

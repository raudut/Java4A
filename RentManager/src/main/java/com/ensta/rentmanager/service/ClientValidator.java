package com.ensta.rentmanager.service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

import com.ensta.rentmanager.dao.ClientDao;
import com.ensta.rentmanager.dao.ReservationDao;
import com.ensta.rentmanager.exception.DaoException;
import com.ensta.rentmanager.exception.ServiceException;
import com.ensta.rentmanager.model.Client;
import com.ensta.rentmanager.model.Reservation;

public class ClientValidator {
	
	private ClientDao clientdao = ClientDao.getInstance();
	private ReservationDao resadao = ReservationDao.getInstance();
	public static ClientValidator instance=null;
	
	public static ClientValidator getInstance() {
		if (instance == null) {
			instance = new ClientValidator();
		}
		
		return instance;
	}
	
	public void checkAge(Client client) throws ServiceException {
		long age = ChronoUnit.YEARS.between(client.getNaissance().toLocalDate(), LocalDate.now());
		if (age<18) {
			
			throw new ServiceException("le client doit avoir 18 ans");
			
		}
	}
	
	public void checkString(Client client) throws ServiceException{
		String nom = client.getNom();
		String prenom = client.getPrenom();
		if (nom.length() < 3 || prenom.length() < 3 ) {
			throw new ServiceException("Le nom et le prénom doivent être composé de 3 lettres minimum");
		}
	}
	
	public void checkEmail(Client client) throws ServiceException, DaoException{
		String email = client.getEmail();
		
		List<Client> list = clientdao.findAll();
		
		
		for(Client c : list)
		{
			if(email.equals(c.getEmail()))
			{throw new ServiceException("Cet Email est déjà pris");}
		}
		
		
	}
	
	public void suppResa(int id) throws DaoException {
		List<Reservation> list = resadao.findResaByClientId(id);
		
		for(Reservation r : list) {
			resadao.delete(r.getId());
		}
		
	}


}

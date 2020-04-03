package com.ensta.rentmanager.service;

import java.util.List;

import com.ensta.rentmanager.dao.ClientDao;
import com.ensta.rentmanager.exception.DaoException;
import com.ensta.rentmanager.exception.ServiceException;
import com.ensta.rentmanager.model.Client;



public class ClientService {
	
	

	private ClientDao clientdao;
	private ClientValidator clientValidator= ClientValidator.getInstance();
	public static ClientService instance=null;
		
	private ClientService() {
		this.clientdao = ClientDao.getInstance();
	}
	
	public static ClientService getInstance() {
		if (instance == null) {
			instance = new ClientService();
		}
		
		return instance;
	}
	
	
	public long create(Client client) throws ServiceException {
		

		try {
			clientValidator.checkEmail(client);
			clientValidator.checkAge(client);
			clientValidator.checkString(client);
			return clientdao.create(client);
			
		} catch(DaoException e){
			throw new ServiceException(e.getMessage());
		}
		
	}
	
	public void update(Client client) throws ServiceException {
		
		try {
			clientValidator.checkAge(client);
			clientValidator.checkString(client);
			clientdao.update(client);
			
		} catch(DaoException e){
			throw new ServiceException(e.getMessage());
		}
		
	}
	
	
	public long delete(int id) throws ServiceException{
		try {
			clientValidator.suppResa(id);
			return clientdao.delete(id);
			
		}catch(DaoException e ) {
			throw new ServiceException(e.getMessage());
		}
	}


	public Client findById(int id) throws ServiceException {

			Client c=clientdao.findById(id).get();
			return c;
	
		
	}
	
	public Client findByName(String nom) throws ServiceException {

		Client c=clientdao.findClientByName(nom).get();
		return c;

	
}

	public List<Client> findAll() throws ServiceException {
		try {
			return clientdao.findAll();
			
		}catch (DaoException e ) {
			throw new ServiceException(e.getMessage());
		}
		
	}
	
	
	
	
}

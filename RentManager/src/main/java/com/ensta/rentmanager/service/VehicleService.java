package com.ensta.rentmanager.service;

import java.util.List;

import com.ensta.rentmanager.exception.DaoException;
import com.ensta.rentmanager.exception.ServiceException;
import com.ensta.rentmanager.model.Client;
import com.ensta.rentmanager.model.Vehicle;
import com.ensta.rentmanager.dao.ClientDao;
import com.ensta.rentmanager.dao.VehicleDao;

public class VehicleService {

	private VehicleDao vehicleDao;
	private VehicleValidator vehiclevalidator = VehicleValidator.getInstance();
	public static VehicleService instance;
	
	private VehicleService() {
		this.vehicleDao = VehicleDao.getInstance();
	}
	
	public static VehicleService getInstance() {
		if (instance == null) {
			instance = new VehicleService();
		}
		
		return instance;
	}
	
	public void update(Vehicle v) throws ServiceException {
		
		try {
			vehiclevalidator.checkSeats(v);
			vehicleDao.update(v);
		} catch(DaoException e){
			throw new ServiceException(e.getMessage());
		}
		
	}	
	public long create(Vehicle vehicle) throws ServiceException {
		
		try {
			vehiclevalidator.checkSeats(vehicle);
			return vehicleDao.create(vehicle);
		
	} catch(DaoException e){
		throw new ServiceException(e.getMessage());
	}
	
	}
	
	public long delete(int id) throws ServiceException{
		try {
			vehiclevalidator.suppResa(id);
			return vehicleDao.delete(id);
		}
		catch(DaoException e) {
			throw new ServiceException(e.getMessage());
		}
	}

	public Vehicle findById(int id) throws ServiceException, DaoException {				
					Vehicle v=vehicleDao.findById(id).get();
					return v;
				
		}	
	
	public Vehicle findByName(String cons) throws ServiceException, DaoException {				
		Vehicle v=vehicleDao.findVehicleByName(cons).get();
		return v;
	
}	
				
	

	public List<Vehicle> findAll() throws ServiceException {
		try {
			
			return vehicleDao.findAll();
		}catch(DaoException e) {
			throw new ServiceException(e.getMessage());
		}
		
	}
	
}

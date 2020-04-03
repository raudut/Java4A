package com.ensta.rentmanager.service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

import com.ensta.rentmanager.dao.ReservationDao;
import com.ensta.rentmanager.exception.DaoException;
import com.ensta.rentmanager.exception.ServiceException;
import com.ensta.rentmanager.model.Client;
import com.ensta.rentmanager.model.Reservation;

public class ReservationService {

	private ReservationDao resadao;
	public static ReservationService instance=null;
	
	private ReservationService() {
		this.resadao=ReservationDao.getInstance();
	}
	
	public static ReservationService getInstance() {
		if(instance==null) {
			instance=new ReservationService();
		}
		
		return instance;
	}
	
	public void update(Reservation resa) throws ServiceException {
		

		try {
			resadao.update(resa);
			
		} catch(DaoException e){
			throw new ServiceException(e.getMessage());
		}
		
	}
	
	public long create(Reservation resa) throws ServiceException {
		
		try {
			return resadao.create(resa);
			
		} catch(DaoException e){
			throw new ServiceException(e.getMessage());
		}
		
	}
	
	public long delete(int id) throws ServiceException{
		try {
			return resadao.delete(id);
		}catch(DaoException e ) {
			throw new ServiceException(e.getMessage());
		}
	}
	
	public List<Reservation> findAll() throws ServiceException {
		try {
			return resadao.findAll();
			
		}catch (DaoException e ) {
			throw new ServiceException(e.getMessage());
		}	
	}
	
	public List<Reservation> findByClient(int clientId) throws ServiceException {
		try {
			return resadao.findResaByClientId(clientId);
			
		}catch (DaoException e ) {
			throw new ServiceException(e.getMessage());
		}	
	}
	
	public List<Reservation> findByVehicule(int vehiculeId) throws ServiceException {
		try {
			return resadao.findResaByVehicleId(vehiculeId);
			
		}catch (DaoException e ) {
			throw new ServiceException(e.getMessage());
		}	
	}
	
	public Reservation findById(int id) throws ServiceException, DaoException {
		return resadao.findById(id);	
	}
	
	
	
	
	
	
}

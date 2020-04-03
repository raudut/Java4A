package com.ensta.rentmanager.service;

import com.ensta.rentmanager.dao.ClientDao;
import com.ensta.rentmanager.dao.ReservationDao;

public class ReservationValidator {
	
	private ClientDao clientdao = ClientDao.getInstance();
	private ReservationDao resadao = ReservationDao.getInstance();
	public static ReservationValidator instance=null;
	
	public static ReservationValidator getInstance() {
		if (instance == null) {
			instance = new ReservationValidator();
		}
		
		return instance;
	}

}

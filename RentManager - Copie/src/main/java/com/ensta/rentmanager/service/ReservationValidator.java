package com.ensta.rentmanager.service;

import java.sql.Date;
import java.util.Calendar;
import java.util.List;


import com.ensta.rentmanager.dao.ReservationDao;
import com.ensta.rentmanager.exception.DaoException;
import com.ensta.rentmanager.exception.ServiceException;
import com.ensta.rentmanager.model.Reservation;

public class ReservationValidator {
	
	private ReservationDao resadao = ReservationDao.getInstance();
	public static ReservationValidator instance=null;
	
	public static ReservationValidator getInstance() {
		if (instance == null) {
			instance = new ReservationValidator();
		}
		
		return instance;
	}

	  void checkResa7(java.sql.Date debut, java.sql.Date fin) throws ServiceException {
	        // calcul de la duree de la periode voulue
	        long diff = fin.getTime() - debut.getTime();
	        float length = (diff / (1000 * 60 * 60 * 24));
	        System.out.println("duree " + length);
	        // verification de la duree de la periode
	        if (length > 7) {
	            throw new ServiceException(
	                    "La periode de location est trop longue, elle ne doit pas etre supérieur à 7 jours");
	        }

	    }
	  
	 public void checkResaPeriod(Date debut, Date fin, int veh_id, int id)
	            throws ServiceException, DaoException {
	        // recuperation de toutes reservaion du vehicule concerne
	        List<Reservation> list = resadao.findResaByVehicleId(veh_id);

	        if (!list.isEmpty()) {
	        for (Reservation resa : list) {
	            if (resa.getId() != id) {
	                // recuperation des dates de reservation deja dans la base de donnees
	                Date resdebut = resa.getDebut();
	                Date resfin = resa.getFin();
	                // comparaision des nouvelles dates et des anciennes
	                if (!(resfin.before(debut) || fin.before(resdebut))) {
	                    throw new ServiceException(
	                            "Ce véhicule est déjà reservé pour cette date.");
	                }
	            }
	        }
	    }
	 }
	 
	  
	 
	 public void lessThanThirtiesDays(Date debut,Date fin,int id_vehicle) throws ServiceException{
	        try {
	            List<Reservation> allreservation=resadao.findResaByVehicleId(id_vehicle);
	            if(allreservation.size()>0) {
	                for(Reservation rsvcr:allreservation) {
	                    if(fin.getMonth() == rsvcr.getDebut().getMonth() && fin.getYear() == rsvcr.getDebut().getYear() || fin.getMonth() == rsvcr.getDebut().getMonth()+1 && fin.getYear() == rsvcr.getDebut().getYear()) {
	                        if(compareDate(fin,rsvcr.getDebut())<=30 && compareDate(rsvcr.getFin(),debut)<=30) {
	                        }else {
	                            throw new ServiceException("Impossible de louer un même véhicule plus de 30 jours");
	                    }
	                    }
	                }
	            }
	        }catch(DaoException e) {
	            throw new ServiceException(e.getMessage());
	        }
	    }
	 
	public long compareDate(Date debut, Date fin) {
		final long milisecjour= 1000*60*60*24;
		long diff = debut.getTime() - fin .getTime();
		return Math.abs(diff/milisecjour);
	}

	    

}

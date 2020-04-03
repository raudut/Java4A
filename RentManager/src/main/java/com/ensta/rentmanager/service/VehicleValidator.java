package RentManager.src.main.java.com.ensta.rentmanager.service;

import java.sql.Date;
import java.util.List;

import javax.servlet.ServletException;

import com.ensta.rentmanager.dao.ReservationDao;
import com.ensta.rentmanager.exception.DaoException;
import com.ensta.rentmanager.exception.ServiceException;
import com.ensta.rentmanager.model.Reservation;
import com.ensta.rentmanager.model.Vehicle;

public class VehicleValidator {
	
	private ReservationDao resadao = ReservationDao.getInstance();
	public static VehicleValidator instance=null;
	
	public static VehicleValidator getInstance() {
		if (instance == null) {
			instance = new VehicleValidator();
		}
		
		return instance;
	}
	
	public void suppResa(int id) throws DaoException {
		List<Reservation> list = resadao.findResaByVehicleId(id);
		
		for(Reservation r : list) {
			resadao.delete(r.getId());
		}
		
	}
	
	public void checkSeats(Vehicle v) throws ServiceException{
		int nbSeats = v.getSeats();
		if(nbSeats<2 || nbSeats>9) {
			throw new ServiceException("Le nombre de place doit être compris entre 2 et 9");
		}
	}



}

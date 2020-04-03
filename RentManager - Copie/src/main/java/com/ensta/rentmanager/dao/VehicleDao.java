package com.ensta.rentmanager.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.ensta.rentmanager.exception.DaoException;
import com.ensta.rentmanager.model.Vehicle;
import com.ensta.rentmanager.persistence.ConnectionManager;

public class VehicleDao {
	
	private static VehicleDao instance = null;
	private VehicleDao() {}
	public static VehicleDao getInstance() {
		if(instance == null) {
			instance = new VehicleDao();
		}
		return instance;
	}
	
	private static final String CREATE_VEHICLE_QUERY = "INSERT INTO Vehicle(constructeur, modele, nb_places) VALUES(?, ? ,?);";
	private static final String DELETE_VEHICLE_QUERY = "DELETE FROM Vehicle WHERE id=?;";
	private static final String FIND_VEHICLE_QUERY = "SELECT id, constructeur, modele, nb_places FROM Vehicle WHERE id=?;";
	private static final String FIND_VEHICLES_QUERY = "SELECT id, constructeur, modele, nb_places FROM Vehicle;";
	private static final String FIND_VEHICLES_BY_NAME_QUERY = "SELECT id, constructeur, modele, nb_places FROM Vehicle WHERE constructeur=?;";
	private static final String UPDATE_VEHICLE_QUERY= "UPDATE Vehicle SET  constructeur=?, modele=?, nb_places=? WHERE id=?;";
	
	
	public void update(Vehicle vehicule) throws DaoException{
		try (Connection conn  = ConnectionManager.getConnection();
				PreparedStatement statement = conn.prepareStatement(UPDATE_VEHICLE_QUERY); 
				){
			
			statement.setString(1, vehicule.getManufacturer());
			statement.setString(2,vehicule.getModele());
			statement.setInt(3, vehicule.getSeats());
			statement.setInt(4, vehicule.getId());
			
			statement.executeUpdate();
		} 
		catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Erreur lors de l'update" +e.getMessage());
		}
		
	}
	
	public Optional<Vehicle> findVehicleByName(String cons)
	{
	Vehicle v = new Vehicle();
		Optional<Vehicle> optVeh = Optional.of(v);
		try (Connection conn = ConnectionManager.getConnection();
				PreparedStatement statement = conn.prepareStatement(FIND_VEHICLES_BY_NAME_QUERY);) {
			
			
			statement.setString(1, cons);
			ResultSet resultSet3 = statement.executeQuery();
			
			while(resultSet3.next()) {
				
				v.setId(resultSet3.getInt(1));
				v.setManufacturer(cons);
				v.setModele(resultSet3.getString(3));
				v.setSeats(resultSet3.getInt(4));
			}
			
		} catch(SQLException e) {
			return Optional.empty();
		}
		
		
			return optVeh;
		
		}
		
	
	
	
	public long create(Vehicle vehicle) throws DaoException {
		
		try (Connection conn = ConnectionManager.getConnection();
				PreparedStatement statement = conn.prepareStatement(CREATE_VEHICLE_QUERY); 
				){	
			statement.setString(1, vehicle.getManufacturer());
			statement.setString(2, vehicle.getModele());
			statement.setInt(3, vehicle.getSeats());
			
			
			long result = statement.executeUpdate(); 
			
			return result;
			
		} catch (SQLException e) {
			throw new DaoException("Erreur lors de la création : " + e.getMessage());
		}
		
	}

	public long delete(int id) throws DaoException {
		try (Connection conn = ConnectionManager.getConnection();
				PreparedStatement statement = conn.prepareStatement(DELETE_VEHICLE_QUERY); 
				){			
			statement.setInt(1,id);
			
			long result = statement.executeUpdate();
			return result;
			
		} catch (SQLException e) {
			throw new DaoException("Erreur lors de la création : " + e.getMessage());
		}
		
	}

	public Optional<Vehicle> findById(int id) {
			Vehicle v = new Vehicle();
		
		Optional<Vehicle> optClient = Optional.of(v);
		try (Connection conn = ConnectionManager.getConnection();
				PreparedStatement statement = conn.prepareStatement(FIND_VEHICLE_QUERY);) {
			
			statement.setInt(1, id);
			ResultSet resultSet2 = statement.executeQuery();
		
			
			while(resultSet2.next()) {
				
				v.setId(id);
				
				v.setManufacturer(resultSet2.getString(2));	
				v.setModele(resultSet2.getString(3));
				v.setSeats(resultSet2.getInt(4));			
				
			}
			
		} catch(SQLException e) {
			
			return Optional.empty();
			
		}
		return optClient;
	}

	public List<Vehicle> findAll() throws DaoException {
		
		List<Vehicle> resultList= new ArrayList<>();
		try (Connection conn = ConnectionManager.getConnection();
				PreparedStatement statement = conn.prepareStatement(FIND_VEHICLES_QUERY); ){
			
			ResultSet resultSet = statement.executeQuery();
			
			while(resultSet.next()) {
				Vehicle vehicule = new Vehicle(
						resultSet.getInt(1),
						resultSet.getString(2),
						resultSet.getString(3),
						resultSet.getInt(4)
						);

				resultList.add(vehicule);
			}
			

		}catch (SQLException e) {
			throw new DaoException("Erreur lors de la création : " + e.getMessage());
		}
		return resultList;
		
	}
	
	public static void main (String... args) {
		VehicleDao dao = VehicleDao.getInstance();
		
		try {
			List<Vehicle> list= dao.findAll();
			for(Vehicle v :  list) {
				System.out.println(v);
			}			
			
		}catch (DaoException e ) {
				System.out.println("Erreur lors du Select" + e.getMessage());
		}
		
		
			Optional<Vehicle> optVehicle2 = dao.findVehicleByName("Seat");
			if(optVehicle2.isPresent()) {
				Vehicle v=optVehicle2.get();
				System.out.println(v);
			} 
			else {
				//System.out.println("Erreur lors du select du vehicule");
			}
		
	
		}
		
	}




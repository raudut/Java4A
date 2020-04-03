package com.ensta.rentmanager.dao;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.ensta.rentmanager.exception.DaoException;
import com.ensta.rentmanager.model.Client;
import com.ensta.rentmanager.model.Reservation;
import com.ensta.rentmanager.model.Vehicle;
import com.ensta.rentmanager.persistence.ConnectionManager;

public class ClientDao {
	
	private static ClientDao instance = null;
	private ClientDao() {}
	public static ClientDao getInstance() {
		if(instance == null) {
			instance = new ClientDao();
		}
		return instance;
	}
	
	private static final String CREATE_CLIENT_QUERY = "INSERT INTO Client(nom, prenom, email, naissance) VALUES(?, ?, ?, ?);";
	private static final String DELETE_CLIENT_QUERY = "DELETE FROM Client WHERE id=?;";
	private static final String FIND_CLIENT_QUERY = "SELECT nom, prenom, email, naissance FROM Client WHERE id=?;";
	private static final String FIND_CLIENTS_QUERY = "SELECT id, nom, prenom, email, naissance FROM Client;";
	private static final String FIND_CLIENTS_BY_NAME_QUERY = "SELECT id, nom, prenom, email, naissance FROM Client WHERE nom=?;";
	private static final String UPDATE_CLIENT_QUERY= "UPDATE Client SET  nom=?, prenom=?, email=?, naissance=? WHERE id=?;";
	
	
	public void update(Client client) throws DaoException{
		try (Connection conn  = ConnectionManager.getConnection();
				PreparedStatement statement = conn.prepareStatement(UPDATE_CLIENT_QUERY); 
				){
			statement.setString(1, client.getNom());
			statement.setString(2, client.getPrenom());
			statement.setString(3, client.getEmail());
			statement.setDate(4, client.getNaissance());
			statement.setInt(5, client.getId());
			
			statement.executeUpdate();
		} 
		catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Erreur lors de l'update" +e.getMessage());
		}
		
	}
	
	
	public long create(Client client) throws DaoException {
		try (Connection conn  = ConnectionManager.getConnection();
				PreparedStatement statement = conn.prepareStatement(CREATE_CLIENT_QUERY); 
				){			
			statement.setString(1, client.getNom());
			statement.setString(2, client.getPrenom());
			statement.setString(3, client.getEmail());
			statement.setDate(4, client.getNaissance());
			
			long result = statement.executeUpdate(); //renvoie le nombre de ligne qui a ete affecté

			
			return result;
			
		} catch (SQLException e) {
			throw new DaoException("Erreur lors de la création : " + e.getMessage());
		}
	}

	public long delete(int id) throws DaoException {
		try (Connection conn = ConnectionManager.getConnection();
				PreparedStatement statement = conn.prepareStatement(DELETE_CLIENT_QUERY);
				){
			statement.setInt(1, id);						
			long result = statement.executeUpdate();
			
			return result;
		}catch (SQLException e) {
			throw new DaoException("Erreur lors de la suppression : " + e.getMessage());
		}
	}



	public List<Client> findAll() throws DaoException {
		
		List<Client> resultList= new ArrayList<>();
		try (Connection conn = ConnectionManager.getConnection();
				PreparedStatement statement = conn.prepareStatement(FIND_CLIENTS_QUERY); ){
			
			ResultSet resultSet = statement.executeQuery();
			
			
			while(resultSet.next()) {
				
				Client client=new Client(
						resultSet.getInt(1),
						resultSet.getString(2),
						resultSet.getString(3),
						resultSet.getString(4),
						resultSet.getDate(5)
						);

				resultList.add(client);
			}
			

		}catch (SQLException e) {
			throw new DaoException("Erreur lors de la création : " + e.getMessage());
		}
		return resultList;
	}

	public static void main (String... args) {
		ClientDao dao = ClientDao.getInstance();
		
		try {
			System.out.println("liste des clients");
			List<Client> list= dao.findAll();
			for(Client c :  list) {
				System.out.println(c);
			}			
			
			
		}catch (DaoException e ) {
				System.out.println("Erreur lors du Select" + e.getMessage());
			}
		
		System.out.println("Afficher le client n°1");
		Optional<Client> optClient = dao.findById(1);
		
		System.out.println("Afficher le client Dupont");
		Optional<Client> optClientN = dao.findClientByName("Dupont");
		
		if(optClient.isPresent()) {
			Client c=optClientN.get();
			System.out.println(c);
		} else {
			System.out.println("Erreur lors du select du client");
		}
		}
	
	public Optional<Client> findById(int id)
	{
	Client c = new Client();
		
		Optional<Client> optClient = Optional.of(c);
		try (Connection conn = ConnectionManager.getConnection();
				PreparedStatement statement = conn.prepareStatement(FIND_CLIENT_QUERY);) {
			
			statement.setInt(1, id);
			ResultSet resultSet2 = statement.executeQuery();
			
			while(resultSet2.next()) {
				
				c.setId(id);
				c.setNom(resultSet2.getString(1));
				c.setPrenom(resultSet2.getString(2));
				c.setEmail(resultSet2.getString(3));
				c.setNaissance(resultSet2.getDate(4));
			}
			
		} catch(SQLException e) {
			return Optional.empty();
		}
		return optClient;
	}
	
	
	public Optional<Client> findClientByName(String nom)
	{
	Client c = new Client();
		Optional<Client> optClient = Optional.of(c);
		try (Connection conn = ConnectionManager.getConnection();
				PreparedStatement statement = conn.prepareStatement(FIND_CLIENTS_BY_NAME_QUERY);) {
			
			
			statement.setString(1, nom);
			ResultSet resultSet3 = statement.executeQuery();
			
			while(resultSet3.next()) {
				
				c.setId(resultSet3.getInt(1));
				c.setNom(nom);
				c.setPrenom(resultSet3.getString(3));
				c.setEmail(resultSet3.getString(4));
				c.setNaissance(resultSet3.getDate(5));
			}
			
		} catch(SQLException e) {
			return Optional.empty();
		}
		return optClient;
	}
	
	

}
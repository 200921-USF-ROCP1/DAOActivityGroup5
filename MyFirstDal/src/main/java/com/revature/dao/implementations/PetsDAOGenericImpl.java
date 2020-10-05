package com.revature.dao.implementations;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.revature.dao.interfaces.GenericDAO;
import com.revature.models.Apartment;
import com.revature.models.Pets;

public class PetsDAOGenericImpl implements GenericDAO<Pets> {
	
	Connection connection;

	public void create(Pets t) {
		// TODO Auto-generated method stub
		try {
			
			if (t.getApartment() != null) {
				PreparedStatement ps = connection.prepareStatement("SELECT * FROM apartments WHERE id = ?");
				ps.setInt(1, t.getApartment().getId());
				
				ResultSet rs = ps.executeQuery();
				if (!rs.next()) {
					
					ApartmentDAOGenericImpl apartmentDAO = new ApartmentDAOGenericImpl();
					apartmentDAO.create(t.getApartment());
					
					//PreparedStatement ps = connection.prepareStatement("INSERT INTO pets ");
					
				}
			}
			
				PreparedStatement ps = connection.prepareStatement("INSERT INTO pets"
						+ "(id, breed, name, apartment_id, is_service_animal) VALUES (?, ?, ?, ?, ?);");
				ps.setInt(1, t.getId());
				ps.setString(2, t.getBreed());
				ps.setString(3, t.getName());
				ps.setInt(4, t.getApartment().getId());
				ps.setBoolean(5, t.getIsServiceAnimal());
				
				ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public Pets get(int id) {
		// TODO Auto-generated method stub
		
		try {
		PreparedStatement ps = connection.prepareStatement("SELECT * FROM pets WHERE id = ?;");
			ps.setInt(1, id);
			
			ResultSet rs = ps.getResultSet();
			if (rs.next()) {
				Pets pet = new Pets();
				pet.setId(rs.getInt("id"));
				pet.setBreed(rs.getString("breed"));
				pet.setName(rs.getNString("name"));
				pet.setIsServiceAnimal(rs.getBoolean("is_service_animal"));
				
				PreparedStatement apartmentStatement = connection
						.prepareStatement("SELECT * FROM apartments WHERE id  ?");
				apartmentStatement.setInt(1, rs.getInt("apartment_id"));
				
				ApartmentDAOGenericImpl apartmentDao = new ApartmentDAOGenericImpl();
				Apartment apartment = new Apartment();
				apartment = apartmentDao.get(rs.getInt("apartment_id"));
				
				pet.setApartment(apartment);
				
				return pet;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public void update(Pets t) {
		// TODO Auto-generated method stub
		
		try {
			PreparedStatement ps = connection.prepareStatement("UPDATE pets "
					+ "SET breed = ?, name = ?, is_service_animal = ?, apartment_id = ?  WHERE id = ?;");
			
			ps.setString(1, t.getBreed());
			ps.setString(2, t.getName());
			ps.setBoolean(3, t.getIsServiceAnimal());
			ps.setInt(4, t.getApartment().getId());
			ps.setInt(5, t.getId());
			
			ps.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void delete(Pets t) {
		// TODO Auto-generated method stub
		try {
			PreparedStatement ps = connection.prepareStatement("DELETE FROM pets WHERE id = ?;");
			ps.setInt(1, t.getId());

			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

	public List<Pets> getAll() {
		// TODO Auto-generated method stub
		List<Pets> pets = new ArrayList<Pets>();
		
		try {
			PreparedStatement ps = connection.prepareStatement(
					"SELECT * FROM pets " + "LEFT JOIN apartments on pets.apartment_id = apartment.id;");
		
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				Pets pet = new Pets();
				pet.setId(rs.getInt("pets.id"));
				pet.setBreed(rs.getString("breed"));
				pet.setName(rs.getString("name"));
				pet.setIsServiceAnimal(rs.getBoolean("is_service_animal"));
				
				Apartment apartment = new Apartment();
				ApartmentDAOGenericImpl apartmentDAO = new ApartmentDAOGenericImpl();
				apartment = apartmentDAO.get(rs.getInt("apartment_id"));
				
				pet.setApartment(apartment);
				pets.add(pet);
			}
			
			return pets;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return null;
	}
}

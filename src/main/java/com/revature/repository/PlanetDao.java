package com.revature.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;
import com.revature.models.Planet;
import com.revature.utilities.ConnectionUtil;

import javax.xml.transform.Result;

public class PlanetDao {
    
    public List<Planet> getAllPlanets() {
		// TODO: implement
		List<Planet> planets = new ArrayList<>();
		try(Connection connection = ConnectionUtil.createConnection())
		{

			String sql = "SELECT * FROM PLANETS";
			PreparedStatement ps = connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next())
			{
				Planet planet = new Planet();

				int retrievedId = rs.getInt("id");
				String retrievedName = rs.getString("name");
				int retrievedOwnerId = rs.getInt("ownerId");

				planet.setId(retrievedId);
				planet.setName(retrievedName);
				planet.setOwnerId(retrievedOwnerId);
				planets.add(planet);

			}


		}
		catch(SQLException e){
			e.printStackTrace();
			return null;
		}
		return planets;
	}
	public static void main(String [] args)
	{
		PlanetDao dao = new PlanetDao();
		System.out.println(dao.getAllPlanets());
		Planet newPlanet = new Planet();
		newPlanet.setId(4);
		newPlanet.setName("test planet 4");
		newPlanet.setOwnerId(4);
		dao.createPlanet(newPlanet);
		System.out.println(dao.getPlanetByName("test planet2"));
		//System.out.println(dao.getPlanetById(4));
		//System.out.println(dao.deletePlanetById(3));
		System.out.println(dao.getAllPlanets());

	}

	public Planet getPlanetByName(String planetName) {
		// TODO: implement
		try(Connection connection = ConnectionUtil.createConnection()){
			//test on DBeaver with ? = test
			String sql = "SELECT name from planets WHERE name = ?";
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, planetName);
			ResultSet rs = ps.executeQuery();
			Planet possiblePlanet = new Planet();
			while(rs.next())
			{
				int retrievedId = rs.getInt("id");
				String retrievedPlanet = rs.getString("name");
				int retrievedOwnerId = rs.getInt("ownerId");
			}
			return possiblePlanet;
		}
		catch(SQLException e)
		{
			e.printStackTrace();
			return null;
		}

	}

	public Planet getPlanetById(int planetId) {
		try(Connection connection = ConnectionUtil.createConnection()){
			String sql = "SELECT id from planets WHERE id = ?";
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setInt(1, planetId);
			ResultSet rs = ps.executeQuery();
			Planet possiblePlanet = new Planet();
			while(rs.next())
			{
				int retrievedId = rs.getInt("id");
				String retrievedPlanet = rs.getString("name");
				int retrievedOwnerId = rs.getInt("ownerId");
			}
			return possiblePlanet;
		}
		catch(SQLException e)
		{
			e.printStackTrace();
			return null;
		}

	}

	public Planet createPlanet(Planet p) {
		// TODO: implement
		try(Connection connection = ConnectionUtil.createConnection()){
			String sql = "INSERT INTO planets(id,name,ownerId) VALUES(?,?,?)";
			PreparedStatement ps = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
			ps.setString(1,p.getName());
			ps.setInt(2,p.getOwnerId());
			ps.executeUpdate();
			ResultSet rs = ps.getGeneratedKeys();
			Planet newPlanet = new Planet();
			newPlanet.setName(p.getName());
			newPlanet.setOwnerId(p.getOwnerId());
			while(rs.next()){
				newPlanet.setId(rs.getInt(1));
			}
			return newPlanet;
		}
		catch(SQLException e)
		{
			e.printStackTrace();
			return null;
		}

	}

	public boolean deletePlanetById(int planetId) {
		// TODO: implement
		try(Connection connection = ConnectionUtil.createConnection()){

			String sql = "DELETE FROM planets WHERE id = ?";
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setInt(1, planetId);
			ps.executeUpdate();
			return true;
		}
		catch(SQLException e)
		{
			e.printStackTrace();
			return false;
		}
	}
}

package com.revature.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.revature.exceptions.MoonFailException;
import com.revature.models.Moon;
import com.revature.utilities.ConnectionUtil;

public class MoonDao {
    
    public List<Moon> getAllMoons(int myPlanetId) {
		// TODO: implement
		List<Moon> moons = new ArrayList<>();
		try(Connection connection = ConnectionUtil.createConnection())
		{
			String sql = "SELECT * FROM moons WHERE myPlanetId = ?";
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setInt(1,myPlanetId);
			ResultSet rs = ps.executeQuery();
			while(rs.next())
			{
				Moon moon = new Moon();
				int retrievedId = rs.getInt("id");
				String retrievedName = rs.getString("name");
				int retrievedMyPlanetId = rs.getInt("myPlanetId");

				moon.setId(retrievedId);
				moon.setName(retrievedName);
				moon.setMyPlanetId(retrievedMyPlanetId);

				moons.add(moon);
			}

		}
		catch(SQLException e )
		{
			e.printStackTrace();
			return null;
		}
		return moons;
	}

	public Moon getMoonByName(String moonName) {
		// TODO: implement
		try(Connection connection = ConnectionUtil.createConnection())
		{
			String sql = "Select * FROM moons WHERE name = ?";
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, moonName);

			Moon moon = new Moon();
			ResultSet rs = ps.executeQuery();

			while(rs.next()){

				int retrievedId = rs.getInt("id");
				String retrievedName = rs.getString("name");
				int retrievedPlanetId = rs.getInt("myPlanetId");

				moon.setId(retrievedId);
				moon.setName(retrievedName);
				moon.setMyPlanetId(retrievedPlanetId);
			}
			return moon;

		}
		catch(SQLException e)
		{
			e.printStackTrace();
			return null;
		}

	}

	public Moon getMoonById(int moonId) {
		try(Connection connection = ConnectionUtil.createConnection())
		{
			String sql = "Select * FROM moons WHERE id = ?";
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setInt(1,moonId);

			Moon moon = new Moon();
			ResultSet rs = ps.executeQuery();

			while(rs.next()){
				int retrievedId = rs.getInt("id");
				String retrievedName = rs.getString("name");
				int retrievedPlanetId = rs.getInt("myPlanetId");

				moon.setId(retrievedId);
				moon.setName(retrievedName);
				moon.setMyPlanetId(retrievedPlanetId);
			}
			return moon;
		}
		catch(SQLException e)
		{
			e.printStackTrace();
			return null;
		}
	}

	public Moon createMoon(Moon moon) {
		try(Connection connection = ConnectionUtil.createConnection())
		{
			String sql = "INSERT INTO moon(name, myPlanetId) VALUES(?,?)";
			PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			ps.setString(1,moon.getName());
			ps.setInt(2, moon.getMyPlanetId());


			ResultSet rs = ps.getGeneratedKeys();
			Moon newMoon = new Moon();

			newMoon.setName(moon.getName());
			newMoon.setMyPlanetId(moon.getMyPlanetId());

			while(rs.next()){
				newMoon.setId(rs.getInt(1));
			}
			return newMoon;
		}
		catch(SQLException e)
		{
			e.printStackTrace();
			return null;
		}
	}

	public boolean deleteMoonById(int moonId) {
		try(Connection connection = ConnectionUtil.createConnection())
		{
			String sql = "DELETE FROM moons WHERE id = ?";
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setInt(1,moonId);
			ps.executeUpdate();
			return true;

		}
		catch(SQLException e)
		{
			e.printStackTrace();
			return false;
		}
	}

	public List<Moon> getMoonsFromPlanet(int planetId) {
		List<Moon> moons = new ArrayList<>();
		try(Connection connection = ConnectionUtil.createConnection())
		{
			String sql = "SELECT * FROM moons, planets Where s.myPlanetid = planets.id";
			PreparedStatement ps = connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next())
			{
				Moon moon = new Moon();

				int retrievedId = rs.getInt("id");
				String retrievedName = rs.getString("name");
				int retrievedMoonId = rs.getInt("myPlanetId");

				moon.setId(retrievedId);
				moon.setName(retrievedName);
				moon.setMyPlanetId(retrievedMoonId);

				moons.add(moon);
			}

		}
		catch(SQLException e )
		{
			e.printStackTrace();
			return null;
		}
		return moons;
	}
}

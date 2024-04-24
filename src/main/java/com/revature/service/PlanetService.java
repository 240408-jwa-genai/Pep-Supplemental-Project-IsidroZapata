package com.revature.service;

import java.util.List;

import com.revature.models.Planet;
import com.revature.repository.PlanetDao;

public class PlanetService {

	private PlanetDao dao;

	public PlanetService(PlanetDao dao){
		this.dao = dao;
	}

	public List<Planet> getAllPlanets(int currentUserId) {

		return dao.getAllPlanets(currentUserId);
	}

	public Planet getPlanetByName(int ownerId, String planetName) {
		// TODO Auto-generated method stub
		// ownerId in planets table references users(id) table
		Planet planet = dao.getPlanetByName(planetName);

		if(planet != null && planet.getOwnerId() == ownerId)
		{
			return planet;
		}
		else
		{
			return null;
		}
	}

	public Planet getPlanetById(int ownerId, int planetId) {
		// TODO Auto-generated method stub
		Planet planet = dao.getPlanetById(planetId);
		if(planet != null && planet.getOwnerId() == ownerId)
		{
			return planet;
		}
		else {
			return null;
		}
	}

	public Planet createPlanet(int ownerId, Planet planet) {
		// TODO Auto-generated method stub

		planet.setOwnerId(ownerId);

		return dao.createPlanet(planet);
	}

	public boolean deletePlanetById(int planetId) {
		// TODO Auto-generated method stub
		return dao.deletePlanetById(planetId);
	}


}

package com.revature.service;

import java.util.List;

import com.revature.models.Moon;
import com.revature.models.Planet;
import com.revature.repository.MoonDao;
import com.revature.repository.PlanetDao;

public class MoonService {

	private MoonDao dao;
	private PlanetDao planetDao;
	public MoonService(MoonDao dao, PlanetDao planetDao) {

		this.dao = dao;
		this.planetDao = planetDao;
	}

	public List<Moon> getAllMoons(int currentUserId) {
		// TODO implement
		return dao.getAllMoons(currentUserId);
	}

	public Moon getMoonByName(int myPlanetId, String moonName) {
		// TODO implement
		Moon moon = dao.getMoonByName(moonName);

		if( moon != null && moon.getMyPlanetId()==myPlanetId )
		{
			return moon;
		}
		else{
			return null;
		}
	}

	public Moon getMoonById(int myPlanetId, int moonId) {
		// TODO Aimplement
		Moon moon = dao.getMoonById(myPlanetId);
		if(moon != null && moon.getMyPlanetId() == myPlanetId)
		{
			return moon;
		}
		else {
			return null;
		}
	}

	public Moon createMoon(int myPlanetId, Moon moon) {
		// TODO implement
		if(doesPlanetExist(myPlanetId))
		{
			moon.setMyPlanetId(myPlanetId);
			return dao.createMoon(moon);
		}
		else{
			return null;
		}


	}

	public boolean doesPlanetExist(int myPlanetId)
	{
		Planet planet = planetDao.getPlanetById(myPlanetId);
		return planet != null;
	}
	public boolean deleteMoonById(int moonId) {
		return dao.deleteMoonById(moonId);
	}

	public List<Moon> getMoonsFromPlanet(int myPlanetId) {
		// TODO Auto-generated method stub
		return dao.getMoonsFromPlanet(myPlanetId);
	}
}

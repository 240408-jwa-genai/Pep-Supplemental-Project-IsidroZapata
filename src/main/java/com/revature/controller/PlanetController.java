package com.revature.controller;

import com.revature.models.Planet;
import com.revature.service.PlanetService;
import com.revature.service.UserService;

import java.util.List;

public class PlanetController {
	
	private PlanetService planetService;
	private UserService userService;

	public PlanetController(PlanetService planetService){
		this.planetService = planetService;
	}

	public void getAllPlanets(int currentUserId) {
		// TODO: implement

		List<Planet> listofPlanets = planetService.getAllPlanets(currentUserId);
		if(listofPlanets.isEmpty())
		{
			System.out.println("No plants were found in your planetarium");
		}
		else{
			System.out.println("The planets in your Planetarium:");
			for(Planet planet: listofPlanets)
			{
				System.out.println(planet);
			}
		}
	}

	public void getPlanetByName(int currentUserId, String name) {
		// TODO: implement
		Planet planet = planetService.getPlanetByName(currentUserId,name);
	}

	public void getPlanetByID(int currentUserId, int id) {
		// TODO: implement
		Planet planet = planetService.getPlanetById(currentUserId,id);
	}

	public void createPlanet(int currentUserId, Planet planet) {
		Planet newPlanet = planetService.createPlanet(currentUserId,planet);
	}

	public void deletePlanet(int currentUserId, int id) {
		boolean deletedPlanet = planetService.deletePlanetById(id);
		if(deletedPlanet)
		{
			System.out.println("Planet deleted successfully");
		}
		else{
			System.out.println("Planet was not deleted successfully");
		}
	}
}

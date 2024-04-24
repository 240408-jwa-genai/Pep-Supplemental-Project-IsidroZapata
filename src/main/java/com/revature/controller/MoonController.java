package com.revature.controller;

import com.revature.models.Moon;
import com.revature.service.MoonService;

import java.util.List;

public class MoonController {
	
	private MoonService moonService;

	public MoonController(MoonService moonService) {
		this.moonService = moonService;
	}

	public void getAllMoons(int currentUserId) {
		// TODO: implement
		List<Moon> listofMoons = moonService.getAllMoons(currentUserId);
		if(listofMoons.isEmpty())
		{
			System.out.println("No moons were found in your planetarium");
		}
		else {
			System.out.println("The moons in your Planetarium: ");
			for(Moon moon: listofMoons)
			{
				System.out.println(moon);
			}
		}

	}

	public void getMoonByName(int currentUserId, String name) {
		// TODO: implement
		Moon moon = moonService.getMoonByName(currentUserId,name);

	}

	public void getMoonById(int currentUserId, int id) {
		// TODO: implement
		Moon moon = moonService.getMoonById(currentUserId,id);

	}

	public void createMoon(int myPlanetId, Moon moon) {
		// TODO: implement
		Moon possibleMoon = moonService.createMoon(myPlanetId, moon);
		if(possibleMoon != null)
		{
			System.out.println("Moon created successfully.");
			System.out.println(possibleMoon);
		}
		else{
			System.out.println("Failed to create moon!");
		}
	}

	public void  deleteMoon(int id) {
		// TODO: implement
		boolean deletedMoon = moonService.deleteMoonById(id);
		if(deletedMoon)
		{
			System.out.println("Moon deleted successfully");
		}
		else{
			System.out.println("Moon was not deleted sucessfully");
		}

	}
	
	public List<Moon> getPlanetMoons(int myPlanetId) {
		// TODO: implement
		return moonService.getMoonsFromPlanet(myPlanetId);
	}
}

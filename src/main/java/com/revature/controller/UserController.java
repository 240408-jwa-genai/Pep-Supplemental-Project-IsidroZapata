package com.revature.controller;

import com.revature.MainDriver;
import com.revature.models.User;
import com.revature.models.UsernamePasswordAuthentication;
import com.revature.service.UserService;

public class UserController {

	private UserService userService;

	public UserController(UserService userService) {

		this.userService = userService;
	}

	public void authenticate(UsernamePasswordAuthentication loginRequestData) {
		// TODO: implement
		User possibleUser = userService.authenticate(loginRequestData);
		if(possibleUser != null){
			MainDriver.loggedInUserId = possibleUser.getId();
			System.out.println(String.format("Hello %s! Welcome to the Planetarium",possibleUser.getUsername()));
		}
		else{
			System.out.println("Username/Password combo invalid, please try again!");

		}
	}

	public void register(User registerRequestData) {

		User userResponse = userService.register(registerRequestData);
		if (userResponse.getId() != 0){
			System.out.println("Registration successful! Enjoy using the Planetarium!");
		} else {
			System.out.println("Registration failed: please double check your username and password and try again.");
		}
	}

	public void logout() {
		MainDriver.loggedInUserId = 0;


	}

	public boolean checkAuthorization(int userId) {
		// TODO: implement
		return MainDriver.loggedInUserId == userId;
	}
}

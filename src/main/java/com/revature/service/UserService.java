package com.revature.service;

import com.revature.models.User;
import com.revature.models.UsernamePasswordAuthentication;
import com.revature.repository.UserDao;

public class UserService {

	private UserDao dao;

	public UserService(UserDao dao) {
		this.dao = dao;
	}

	public User authenticate(UsernamePasswordAuthentication loginRequestData) {

		User possibleUser = dao.getUserByUsername(loginRequestData.getUsername());
		if(possibleUser != null){
			boolean usernamesMatch = loginRequestData.getUsername().equals(possibleUser.getUsername());
			boolean passwordMatch = loginRequestData.getPassword().equals(possibleUser.getPassword());
			if(usernamesMatch && passwordMatch)
			{
				return possibleUser;
			}
		}

		// TODO: Try return NULL, to hit logic = invalid data === null
		return null;

	}

	public User register(User registerRequestData) {
		// 2 reqs username to be unique && username and passwords should not be longer
		// than 30 chars
		if (registerRequestData.getUsername().length() <= 30 && registerRequestData.getPassword().length() <= 30) {
			User existingUser = dao.getUserByUsername(registerRequestData.getUsername());
			if (existingUser != null) {
				return null;
			}
			UsernamePasswordAuthentication creds = new UsernamePasswordAuthentication();
			creds.setUsername(registerRequestData.getUsername());
			creds.setPassword(registerRequestData.getPassword());

			User newUser = dao.createUser(creds);
			if(newUser != null)
			{
				System.out.println("User created with ID: "+ newUser.getId());
				newUser.setUsername(registerRequestData.getUsername());
				newUser.setPassword(registerRequestData.getPassword());
			}
			return newUser;
		}
		System.out.println();

		return null;
	}
}

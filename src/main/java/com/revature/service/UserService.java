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
		return new User();

	}

	public User register(User registerRequestData) {
		// 2 reqs username to be unique && username and passwords should not be longer
		// than 30 chars
		if (registerRequestData.getUsername().length() <= 30 && registerRequestData.getPassword().length() <= 30) {
			// This block of code should be reached if both meet req

			// currently DAO returns null
			// TODO: Update this next week when squeel works
			User databaseData = dao.getUserByUsername(registerRequestData.getUsername());

			// Using the dao method to grab any account

			if (databaseData != null) {
				// example of saving the data we are working with in their own vars
				String usernameFromDatabase = databaseData.getUsername();
				String usernameFromRegisterRequest = registerRequestData.getUsername();
				if (!usernameFromRegisterRequest.equals(usernameFromDatabase)) {
					// if the data is not the same, then the creds are valid
					// IF THE USERNAME IS NOT ALREADY EXISTING CREATE A NEW USER
					UsernamePasswordAuthentication validUserCredentials = new UsernamePasswordAuthentication();
					validUserCredentials.setUsername(usernameFromRegisterRequest);
					validUserCredentials.setPassword(registerRequestData.getPassword());
					// NOTE: return a user object to inform CL the registration processed was
					// successful
					return dao.createUser(validUserCredentials);
				}
			}
		}
		// if any business/ software reqs are not met, we return an empty user which we
		// can use to
		// inform the controller layer that the registration process failed
		return new User();

	}
}

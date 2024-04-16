package com.revature;

import java.util.Scanner;

import com.revature.controller.UserController;
import com.revature.models.User;
import com.revature.models.UsernamePasswordAuthentication;
import com.revature.repository.UserDao;
import com.revature.service.UserService;
import com.revature.utilities.ConnectionUtil;

public class MainDriver {

    /*
     * An example of how to get started with the registration business and software
     * requirements has been
     * provided in this code base. Feel free to use it yourself, or adjust it to
     * better fit your own vision
     * of the application. The affected classes are:
     * MainDriver
     * UserController
     * UserService
     */
    public static UserDao userDao = new UserDao();
    public static UserService userService = new UserService(userDao);
    public static UserController userController = new UserController(userService);

    public static void main(String[] args) {
        // TODO: implement main method to initialize layers and run the application

        // We are using a Try with Resources block to auto close our scanner when we are
        // done with it
        try (Scanner scanner = new Scanner(System.in)) {
            boolean activeSession = true;
            while (activeSession) {
                System.out.println(
                        "\nHello! Welcome to our Planetarium! Enter 1 to register an account, 2 to log in, or q to quit this session.");
                String sessionChoice = scanner.nextLine();
                if (sessionChoice.equals("1")) {
                    System.out.println("You have chosen to register your account to our Planetarium!");

                    System.out.print("Please enter the username for your account: ");
                    String potentialUsername = scanner.nextLine();

                    System.out.print("Please enter the password for your account: ");
                    String potentialPassword = scanner.nextLine();
                    // Create a User Object and provide it with the username and password
                    // keep in mind the id wil be set by the DB
                    // (Future note sql - auto increment remember to ignore 1st param)

                    User potentialUser = new User();
                    potentialUser.setUsername(potentialUsername);
                    potentialUser.setPassword(potentialPassword);

                    // pass the data into the service layer for validation
                    userService.register(potentialUser);
                } else if (sessionChoice.equals("2")) {
                    System.out.println("\nYou have chosen to log in!");
                    System.out.println("Please enter your username: ");
                    String username = scanner.nextLine();

                    System.out.println("Please enter your password: ");
                    String password = scanner.nextLine();

                    UsernamePasswordAuthentication credentials = new UsernamePasswordAuthentication();
                    credentials.setUsername(username);
                    credentials.setPassword(password);
                    userController.authenticate(credentials);

                    System.out.println("Welcome! You have successfully logged in: " + username);




                } else if (sessionChoice.equals("q")) {
                    System.out.println("Thank you for visiting our Planetarium, Goodbye World!");
                    activeSession = false;
                } else {
                    System.out
                            .println("This is not a valid option, please choose from one of the options printed below");
                }
            }
        }

    }

}
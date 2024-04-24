package com.revature;

import java.lang.ref.SoftReference;
import java.sql.SQLOutput;
import java.util.Scanner;

import com.revature.controller.MoonController;
import com.revature.controller.UserController;
import com.revature.models.Moon;
import com.revature.models.Planet;
import com.revature.models.User;
import com.revature.models.UsernamePasswordAuthentication;
import com.revature.repository.MoonDao;
import com.revature.repository.PlanetDao;
import com.revature.repository.UserDao;
import com.revature.service.MoonService;
import com.revature.service.UserService;
import com.revature.controller.PlanetController;
import com.revature.service.PlanetService;
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
     * Meeting with Dan notes:
     * THe Main driver is the "endpoint" the interface of this application
     * Obvious as it is for the other controllers to control the interactions I am having
     * With the application that is where I am getting all my information from
     * Can also call MainDriver.loggedInUserId this id or their current id and
     * work with the interface like that.
     *  This may just do what the return generated keys already does
     *      insert into users (username, password) values (?, ?) returning *
     *  Should test and find out
     */
    public static int loggedInUserId = 0;


    public static UserDao userDao = new UserDao();
    public static PlanetDao planetDao = new PlanetDao();
    public static MoonDao moonDao = new MoonDao();

    public static UserService userService = new UserService(userDao);
    public static PlanetService planetService = new PlanetService(planetDao);
    public static MoonService moonService = new MoonService(moonDao, planetDao);

    public static UserController userController = new UserController(userService);
    public static PlanetController planetController = new PlanetController(planetService);
    public static MoonController moonController = new MoonController(moonService);

    public static void main(String[] args) {
        // TODO: implement main method to initialize layers and run the application

        // We are using a Try with Resources block to auto close our scanner when we are
        // done with it
        try (Scanner scanner = new Scanner(System.in)) {
            boolean activeSession = true;
            boolean loggedIn = false;
            while (activeSession) {
                if (!loggedIn) {
                    System.out.println(
                            "\nHello! Welcome to our Planetarium! Enter 1 to register an account, 2 to log in, or q to quit this session.");
                    String sessionChoice = scanner.nextLine();
                    switch (sessionChoice) {
                        case "1":
                            System.out.println("You have chosen to register your account to our Planetarium!");
                            System.out.print("Please enter the username for your account: ");
                            String potentialUsername = scanner.nextLine();
                            System.out.print("Please enter the password for your account: ");
                            String potentialPassword = scanner.nextLine();
                            User potentialUser = new User();
                            potentialUser.setUsername(potentialUsername);
                            potentialUser.setPassword(potentialPassword);
                            System.out.println(potentialUser.getId());

                            userController.register(potentialUser);
                            System.out.println(potentialUser.getId());
                            break;
                        case "2":
                            System.out.println("\nYou have chosen to log in!");
                            System.out.println("Please enter your username: ");
                            String username = scanner.nextLine();

                            System.out.println("Please enter your password: ");
                            String password = scanner.nextLine();

                            UsernamePasswordAuthentication credentials = new UsernamePasswordAuthentication();
                            credentials.setUsername(username);
                            credentials.setPassword(password);
                            userController.authenticate(credentials);
                            loggedIn = true;
                            break;
                        case "q":
                            System.out.println("Thank you for visiting our Planetarium, Goodbye World!");
                            activeSession = false;
                            break;
                        default:
                            System.out
                                    .println("This is not a valid option, please choose from one of the options printed below");
                            break;
                    }
                } else {
                    if(loggedInUserId == 0)
                    {
                        System.out.println("You are not logged in. Please log in to gain access to the Planetarium");
                        loggedIn=false;
                        continue;
                    }
                    System.out.println("Here is your list of possible request!");
                    System.out.println("1 - add a new Planet to your Planetarium ");
                    System.out.println("2 - remove a Planet to your Planetarium ");
                    System.out.println("3 - add a new Moon to a Planet in your Planetarium ");
                    System.out.println("4 - remove a Moon to a Planet in your Planetarium ");
                    System.out.println("5 - to view all your Planets and Moons in your Planetarium");
                    System.out.println("q - to logout");
                    String userChoice = scanner.nextLine();
                    int loggedInUserId = MainDriver.loggedInUserId;
                    switch (userChoice) {
                        case "1":
                            System.out.println("Please enter the name of your Planet ");
                            Planet possiblePlanet = new Planet();
                            possiblePlanet.setName(scanner.nextLine());
                            if (possiblePlanet.getName().length() <= 30) {
                                planetController.createPlanet(loggedInUserId, possiblePlanet);
                                System.out.println("Planet created successfully");
                            } else {
                                System.out.println("Plant name exceeds 30 characters");
                            }
                            break;
                        case "2":
                            System.out.println("Here is a list of all your planets:");
                            planetController.getAllPlanets(loggedInUserId);
                            System.out.println("Please enter the ID of the planet you want to delete:");
                            int deathStartBeamPlanetId = Integer.parseInt(scanner.nextLine());
                            planetController.deletePlanet(loggedInUserId, deathStartBeamPlanetId);
                            break;
                        case "3":
                            System.out.println("Here is a list of all your planets:");
                            planetController.getAllPlanets(loggedInUserId);
                            System.out.println("Please enter the ID of the Planet to which you want to add a Moon:");
                            int planetId = Integer.parseInt(scanner.nextLine());
                            System.out.println("Now please enter the name of the Moon you want to create for this planet: ");
                            String possibleMoonName = scanner.nextLine();
                            if (possibleMoonName.length() <= 30) {
                                Moon newMoon = new Moon();
                                newMoon.setName(possibleMoonName);
                                newMoon.setMyPlanetId(planetId);
                                moonController.createMoon(planetId, newMoon);
                            } else {
                                System.out.println("Moon name exceeds 30 characters. Please try again!");
                            }
                            break;
                        case "4":
                            System.out.println("Here is a list of all your moons: ");
                            moonController.getAllMoons(loggedInUserId);
                            System.out.println("Please enter the ID of the moon you want to delete:");
                            int deathStarBeamMoonId = Integer.parseInt(scanner.nextLine());
                            moonController.deleteMoon(deathStarBeamMoonId);
                            break;
                        case "5":
                            planetController.getAllPlanets(loggedInUserId);
                            moonController.getAllMoons(loggedInUserId);
                            break;
                        case "q":
                            loggedIn = false;
                            loggedInUserId = 0;
                            //activeSession = false;
                            break;
                        default:
                            System.out.println("Invalid option. Please try again." + loggedInUserId);
                    }

                }
            }
        }

    }

}
package com.sbu.boxoffice;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;

import com.sbu.boxoffice.commands.CommandInvoker;
import com.sbu.boxoffice.exceptions.NoSuchDataException;
import com.sbu.boxoffice.repositories.data.DataLoader;
import com.sbu.boxoffice.utils.ApplicationConfig;

public class BoxOfficeApplication {

	public static void main(String[] args) {

		System.out.println("\n !!!!!--- Welcome to Regal Cinema Theatre ---!!!!!" +
				"\nPlease wait while we startup the Box-Office application\n");

		// Add and load Data
		ApplicationConfig applicationConfig = new ApplicationConfig();
		CommandInvoker commandInvoker = applicationConfig.getCommandInvoker();
		DataLoader dataLoader = applicationConfig.getDataLoader();

		Properties properties = new Properties();

		try (InputStream inputStream = new FileInputStream(
				"src/main/resources/application.properties")) {

			properties.load(inputStream);

			// insert Data in order
			dataLoader.executeData("CINEMA-DATA", properties.getProperty("CINEMA-DATA"));
			dataLoader.executeData("SCREEN-DATA", properties.getProperty("SCREEN-DATA"));
			dataLoader.executeData("SEAT-DATA", properties.getProperty("SEAT-DATA"));
			dataLoader.executeData("CUSTOMER-DATA", properties.getProperty("CUSTOMER-DATA"));
			dataLoader.executeData("MOVIE-DATA", properties.getProperty("MOVIE-DATA"));
			dataLoader.executeData("SHOW-DATA", properties.getProperty("SHOW-DATA"));

		} catch (IOException ioe) {
			ioe.printStackTrace();
		} catch (NoSuchDataException e) {
			e.printStackTrace();
		}

		// user-interaction starts here
		Scanner scanner = new Scanner(System.in);
		String choice = "";

		do {

			displayChoices();

			choice = scanner.nextLine();

			checkChoice(choice, commandInvoker, scanner);

		} while (!("6").equals(choice));

		scanner.close();
	}

	private static void displayChoices() {
		System.out.println("Please enter your choice [and press enter] : \n" +
				"\n1. Display Movies" +
				"\n2. Display Movie Shows" +
				"\n3. Book Tickets" +
				"\n4. Swap Movies" +
				"\n5. Generate Report" +
				"\n6. ShutDown Box-Office " +
				"\n7. Exit\n");
	}

	private static void checkChoice(String choice, CommandInvoker commandInvoker, Scanner scanner) {

		try {
			List<String> tokens = new ArrayList<String>();

			switch (choice) {

				case "1": {

					commandInvoker.executeCommand("DISPLAY-MOVIES", null);
				}
					break;

				case "2": {

					System.out.println("Enter the movie name whose shows would you like to check.\n");
					String inputMovie = scanner.nextLine();
					tokens.add(inputMovie);
					commandInvoker.executeCommand("DISPLAY-SHOWS", tokens);
				}
					break;

				case "3": {

					System.out.println("Following are the movies currently screening: ");
					commandInvoker.executeCommand("DISPLAY-MOVIES", null);

					System.out.println("Enter the name of the movie you want to watch : \n");
					String inputMovie = scanner.nextLine();
					tokens.add(inputMovie); // 0

					System.out.println("Following are the shows available for the movie : " + inputMovie);
					commandInvoker.executeCommand("DISPLAY-SHOWS", tokens);

					System.out.println("Enter the showId of the show you want to watch : ");
					String inputShowId = scanner.nextLine();
					tokens.add(inputShowId); // 1

					System.out.println("enter your name :");
					String inputName = scanner.nextLine();
					System.out.println("Enter your email :");
					String inputEmail = scanner.nextLine();
					tokens.add(inputName); // 2
					tokens.add(inputEmail);// 3

					System.out.println("Following are the seats/tickets available for the showId :" + inputShowId);
					commandInvoker.executeCommand("DISPLAY-SEATS", tokens);

					System.out.println(
							"Enter the seats/tickets do you want to book (format : rowNum1#colNum1 rowNum2#colNum2), example to book first 3 tickets in 4th row : 4#1 4#2 4#3");
					String selectedSeats = scanner.nextLine();
					tokens.add(selectedSeats);// 4

					commandInvoker.executeCommand("BOOK-TICKETS", tokens);
				}
					break;

				case "4": {

					System.out.println("Following are the shows which are currently scrrening : \n");
					commandInvoker.executeCommand("DISPLAY-SHOWS", tokens);

					System.out.println("Enter the showIds of the two shows whose movies you would like to swap: ");
					System.out.println("Enter the first show Id : ");
					String show1Id = scanner.nextLine();
					tokens.add(show1Id);

					System.out.println("Enter the second show Id :");
					String show2Id = scanner.nextLine();
					tokens.add(show2Id);

					commandInvoker.executeCommand("SWAP-MOVIES", tokens);
				}

					break;

				case "5": {

					System.out.println("Enter the Movie Theatre Id : ");
					String id = scanner.nextLine();
					tokens.add(id);
					commandInvoker.executeCommand("GENERATE-REPORT", tokens);
				}
					break;

				case "6": {

					System.exit(1);
				}

				case "7": {
					System.exit(1);
				}

				default:
					System.out.println("Please enter a choice within the given options and try again \n");

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}

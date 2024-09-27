package com.demo.project.transport;
import java.util.Scanner;

import com.demo.project.Dao.impl.Adminimpl;
import com.demo.project.Dao.impl.ClientDaoimpl;
import com.demo.project.Dao.impl.DriverDaoimpl;
import com.demo.project.Dao.impl.DurationDaoimpl;
import com.demo.project.Dao.impl.PaymentDaoimpl;
import com.demo.project.Dao.impl.ProductDaoimpl;
import com.demo.project.Dao.impl.VehicleDaoimpl;


public class App {
	public static void main(String[] args) {
		Adminimpl adminDao = new Adminimpl();
		ClientDaoimpl clientDao = new ClientDaoimpl();
		DriverDaoimpl dimpl = new DriverDaoimpl();
		DurationDaoimpl uimpl = new DurationDaoimpl();
		PaymentDaoimpl pimpl = new PaymentDaoimpl();
		ProductDaoimpl himpl = new ProductDaoimpl();
		VehicleDaoimpl vimpl = new VehicleDaoimpl();
		Scanner sc = new Scanner(System.in);
		boolean loggedIn = false;

		// Admin Login
		while (!loggedIn) {
			System.out.print("Enter admin username: ");
			String username = sc.next();

			System.out.print("Enter admin password: ");
			String password = sc.next();

			loggedIn = adminDao.login(username, password);

			if (!loggedIn) {
				System.out.println("Invalid username or password. Please try again.");
			}
		}

		System.out.println("Login successful. Welcome!");

		try {
			char mainMenuChoice;
			do {
				System.out.println("Main Menu");
				System.out.println("1. Client");
				System.out.println("2. Driver");
				System.out.println("3. Duration");
				System.out.println("4. Payment");
				System.out.println("5. Product");  
				System.out.println("6. vehicle");  
				System.out.print("Enter choice: ");

				while (!sc.hasNextInt()) {
					System.out.println("Invalid input. Please enter a number between 1 and 3.");
					sc.next(); // clear invalid input
				}

				int mainChoice = sc.nextInt();
				sc.nextLine(); // consume the newline left-over

				switch (mainChoice) {
				case 1:

					ClientMenu(clientDao, sc);
					break;
				case 2:
					DriverMenu(dimpl, sc);
					break;
				case 3:
					DurationMenu(uimpl, sc);
					break;
 				case 4:
 					PaymentMenu(pimpl, sc);
					break;
				case 5:
					ProductMenu(himpl, sc);
					break;
				case 6:
					VehicleMenu (vimpl, sc);
					break;
				case 7:
					System.out.println("Exiting... Thank you!");
					return;
				default:
					System.out.println("Incorrect choice entered. Please try again.");
					break;
				}
				System.out.print("Do you want to return to the main menu? (Y/N): ");
				String input = sc.nextLine().trim();
				mainMenuChoice = (input.isEmpty() || (input.charAt(0) != 'Y' && input.charAt(0) != 'y')) ? 'N' : 'Y';

			} while (mainMenuChoice == 'Y' || mainMenuChoice == 'y');

			System.out.println("Thank You..");

		} finally {
			sc.close(); // Ensure that the Scanner is closed
		}
	}

	// Client Menu
	private static void ClientMenu(ClientDaoimpl clientDao, Scanner sc) {
		char ClientMenuChoice;
		do {
			System.out.println("Client System");
			System.out.println("1. registerClient");
			System.out.println("2. viewClient");
			System.out.println("3. editClient");
			System.out.print("Enter choice: ");

			while (!sc.hasNextInt()) {
				System.out.println("Invalid input. Please enter a number between 1 and 5.");
				sc.next(); // clear invalid input
			}

			int choice = sc.nextInt();
			sc.nextLine(); // consume the newline left-over

			switch (choice) {
			case 1:
				clientDao.registerClient();
				break;
			case 2:
				clientDao.viewClient();
				break;
			case 3:
				clientDao.editClient();
				break;
			case 4:
				return; // Back to main menu
			default:
				System.out.println("Incorrect choice entered. Please try again.");
				break;
			}

			System.out.print("Do you want to continue in the Patient Menu? (Y/N): ");
			String input = sc.nextLine().trim();
			ClientMenuChoice = (input.isEmpty() || (input.charAt(0) != 'Y' && input.charAt(0) != 'y')) ? 'N' : 'Y';

		} while (ClientMenuChoice == 'Y' || ClientMenuChoice == 'y');
	}

	// DriverMenu
	private static void DriverMenu(DriverDaoimpl dimpl, Scanner sc) {
		char DriverMenuChoice;
		do {
			System.out.println("Driver System");
			System.out.println("1. registerDriver");
			System.out.println("2. viewDriver");
			System.out.println("3. editDriver");
			System.out.print("Enter choice:");

			while (!sc.hasNextInt()) {
				System.out.println("Invalid input. Please enter a number between 1 and 5.");
				sc.next(); // clear invalid input
			}

			int choice = sc.nextInt();
			sc.nextLine(); // consume the newline left-over

			switch (choice) {
			case 1:
				dimpl.registerDriver();
				break;
			case 2:
				dimpl.viewDriver();
				break;
			case 3:
				dimpl.editDriver();
				break;
			
			case 5:
				return; // Back to main menu
			default:
				System.out.println("Incorrect choice entered. Please try again.");
				break;
			}

			System.out.print("Do you want to continue in the  Doctor Menu? (Y/N): ");
			String input = sc.nextLine().trim();
			DriverMenuChoice = (input.isEmpty() || (input.charAt(0) != 'Y' && input.charAt(0) != 'y')) ? 'N' : 'Y';

		} while (DriverMenuChoice == 'Y' || DriverMenuChoice == 'y');
	}

	// DurationMenu
	private static void DurationMenu(DurationDaoimpl uimpl, Scanner sc) {
		char DurationMenuChoice;
		do {
			System.out.println("Duration System");
			System.out.println("1. registerDuration");
			System.out.println("2. viewDuration");
			System.out.println("3. editDuration");
			System.out.print("Enter choice:");

			while (!sc.hasNextInt()) {
				System.out.println("Invalid input. Please enter a number between 1 and 5.");
				sc.next(); // clear invalid input
			}

			int choice = sc.nextInt();
			sc.nextLine(); // consume the newline left-over

			switch (choice) {
			case 1:
				uimpl.registerDuration();
				break;
			case 2:
				uimpl.viewDuration();
				break;
			case 3:
				uimpl.editDuration();
				break;
			case 5:
				return; // Back to main menu
			default:
				System.out.println("Incorrect choice entered. Please try again.");
				break;
			}

			System.out.print("Do you want to continue in the Appointment Menu? (Y/N): ");
			String input = sc.nextLine().trim();
			DurationMenuChoice = (input.isEmpty() || (input.charAt(0) != 'Y' && input.charAt(0) != 'y')) ? 'N' : 'Y';

		} while (DurationMenuChoice == 'Y' || DurationMenuChoice == 'y');
	}

	// PaymentMenu
	private static void PaymentMenu(PaymentDaoimpl pimpl, Scanner sc) {
		char PaymentChoice;
		do {
			System.out.println(" Payment system");
			System.out.println("1. registerPayment");
			System.out.println("2. ViewPayment");
			System.out.println("3. editPayment");
			System.out.print("Enter choice:");

			while (!sc.hasNextInt()) {
				System.out.println("Invalid input. Please enter a number between 1 and 5.");
				sc.next(); // clear invalid input
			}

			int choice = sc.nextInt();
			sc.nextLine(); // consume the newline left-over

			switch (choice) {
			case 1:
				pimpl.registerPayment();
				break;
			case 2:
				pimpl.viewPayment();
				break;
			case 3:
				pimpl.editPayment();
			case 5:
				return; // Back to main menu
			default:
				System.out.println("Incorrect choice entered. Please try again.");
				break;
			}
			System.out.print("Do you want to continue in the Appointment Menu? (Y/N): ");
			String input = sc.nextLine().trim();
			PaymentChoice = (input.isEmpty() || (input.charAt(0) != 'Y' && input.charAt(0) != 'y')) ? 'N' : 'Y';

		} while (PaymentChoice == 'Y' || PaymentChoice == 'y');
	}
   //ProductMenu			
   private static void ProductMenu(ProductDaoimpl himpl, Scanner sc) {
				char ProductChoice;
				do {
					System.out.println(" Product system");
					System.out.println("1. registerProduct");
					System.out.println("2. ViewProduct");
					System.out.println("3. editProduct");
					System.out.print("Enter choice:");

					while (!sc.hasNextInt()) {
						System.out.println("Invalid input. Please enter a number between 1 and 5.");
						sc.next(); // clear invalid input
					}

					int choice = sc.nextInt();
					sc.nextLine(); // consume the newline left-over

					switch (choice) {
					case 1:
						himpl.registerProduct();
						break;
					case 2:
						himpl.viewProduct();
						break;
					case 3:
						himpl.editProduct();
					break;
					case 5:
						return; // Back to main menu
					default:
						System.out.println("Incorrect choice entered. Please try again.");
						break;
					}
					System.out.print("Do you want to continue in the Appointment Menu? (Y/N): ");
					String input = sc.nextLine().trim();
					ProductChoice = (input.isEmpty() || (input.charAt(0) != 'Y' && input.charAt(0) != 'y')) ? 'N' : 'Y';

				} while (ProductChoice == 'Y' || ProductChoice == 'y');
			}				
					
	  //VehicleMenu			
	   private static void VehicleMenu(VehicleDaoimpl vimpl, Scanner sc) {
									char VehicleChoice;
									do {
										System.out.println(" Vehicle system");
										System.out.println("1. registerVehicle");
										System.out.println("2. ViewVehicle");
										System.out.println("3. editVehicle");
										System.out.print("Enter choice:");

										while (!sc.hasNextInt()) {
											System.out.println("Invalid input. Please enter a number between 1 and 5.");
											sc.next(); // clear invalid input
										}

										int choice = sc.nextInt();
										sc.nextLine(); // consume the newline left-over

										switch (choice) {
										case 1:
											vimpl.registerVehicle();
											break;
										case 2:
											vimpl.viewVehicle();
											break;
										case 3:
											vimpl. editVehicle();
											break;
										case 4:
											return; // Back to main menu
										default:
											System.out.println("Incorrect choice entered. Please try again.");
											break;
										}
			System.out.print("Do you want to continue in the medicalHistory Menu? (Y/N): ");
			String input = sc.nextLine().trim();
			VehicleChoice = (input.isEmpty() || (input.charAt(0) != 'Y' && input.charAt(0) != 'y')) ? 'N' : 'Y';

		} while (VehicleChoice == 'Y' || VehicleChoice == 'y');
	}

}
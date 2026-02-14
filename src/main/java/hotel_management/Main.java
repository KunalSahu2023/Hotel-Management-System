package hotel_management;

import hotel_management.model.*;
import hotel_management.service.*;

import java.sql.Date;
import java.util.List;
import java.util.Scanner;

public class Main {

	private static final Scanner sc = new Scanner(System.in);
	private static final CustomerService customerService = new CustomerService();
	private static final RoomService roomService = new RoomService();
	private static final BookingService bookingService = new BookingService();
	private static final BillingService billingService = new BillingService();

	public static void main(String[] args) {
		boolean running = true;

		while (running) {
			System.out.println("\n=== HOTEL MANAGEMENT SYSTEM ===");
			System.out.println("1. Add Customer");
			System.out.println("2. View Customers");
			System.out.println("3. Add Room");
			System.out.println("4. Book Room");
			System.out.println("5. Check In");
			System.out.println("6. Check Out");
			System.out.println("7. Exit");
			System.out.print("Enter your choice: ");

			int choice = sc.nextInt();
			sc.nextLine(); // consume newline

			switch (choice) {
				case 1 -> addCustomer();
				case 2 -> viewCustomers();
				case 3 -> addRoom();
				case 4 -> bookRoom();
				case 5 -> checkIn();
				case 6 -> checkOut();
				case 7 -> {
					running = false;
					System.out.println("Exiting... Goodbye!");
				}
				default -> System.out.println("Invalid choice! Try again.");
			}
		}

		sc.close();
	}

	// 1️⃣ Add Customer
	private static void addCustomer() {
		System.out.print("Enter Name: ");
		String name = sc.nextLine();
		System.out.print("Enter Phone: ");
		String phone = sc.nextLine();
		System.out.print("Enter Email: ");
		String email = sc.nextLine();
		System.out.print("Enter Address: ");
		String address = sc.nextLine();

		Customers customer = new Customers(0, name, phone, email, address);
		boolean success = customerService.addCustomer(customer);
		if (success) System.out.println("Customer added successfully!");
		else System.out.println("Failed to add customer.");
	}

	// 2️⃣ View Customers
	private static void viewCustomers() {
		List<Customers> customers = customerService.getAllCustomers();
		System.out.println("\n--- Customers ---");
		for (Customers c : customers) {
			System.out.println(c);
		}
	}

	// 3️⃣ Add Room
	private static void addRoom() {
		System.out.print("Enter Room Number: ");
		int number = sc.nextInt();
		sc.nextLine();
		System.out.print("Enter Room Type: ");
		String type = sc.nextLine();
		System.out.print("Enter Price per Day: ");
		double price = sc.nextDouble();
		sc.nextLine();

		Rooms room = new Rooms(0, number, type, price, "AVAILABLE");
		boolean success = roomService.addRoom(room);
		if (success) System.out.println("Room added successfully!");
		else System.out.println("Failed to add room.");
	}

	// 4️⃣ Book Room
	private static void bookRoom() {
		System.out.print("Enter Customer ID: ");
		int customerId = sc.nextInt();
		System.out.print("Enter Room ID: ");
		int roomId = sc.nextInt();
		sc.nextLine();
		System.out.print("Enter Check-in Date (YYYY-MM-DD): ");
		String checkIn = sc.nextLine().trim();
		System.out.print("Enter Check-out Date (YYYY-MM-DD): ");
		String checkOut = sc.nextLine().trim();
		System.out.print("Enter Total Amount: ");
		double amount = sc.nextDouble();
		sc.nextLine();

		Bookings booking = new Bookings(0, customerId, roomId,
				Date.valueOf(checkIn), Date.valueOf(checkOut), amount, "BOOKED");

		boolean success = bookingService.bookRoom(booking);
		if (success) System.out.println("Room booked successfully!");
		else System.out.println("Failed to book room.");
	}

	// 5️⃣ Check In
	private static void checkIn() {
		System.out.print("Enter Booking ID for Check-in: ");
		int bookingId = sc.nextInt();
		sc.nextLine();

		boolean success = bookingService.checkIn(bookingId);
		if (success) System.out.println("Checked in successfully!");
		else System.out.println("Booking ID not found.");
	}

	// 6️⃣ Check Out
	private static void checkOut() {
		System.out.print("Enter Booking ID for Check-out: ");
		int bookingId = sc.nextInt();
		sc.nextLine();

		boolean success = bookingService.checkOut(bookingId);
		if (success) System.out.println("Checked out successfully!");
		else System.out.println("Booking ID not found.");
	}
}

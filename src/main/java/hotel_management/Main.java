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

	public static void main(String[] args) throws InterruptedException {
		boolean running = true;

		while (running) {
			System.out.println("\n=== HOTEL MANAGEMENT SYSTEM ===");
			System.out.println("1. Add Customer");
			System.out.println("2. View Customers");
			System.out.println("3. Update Customer");
			System.out.println("4. Delete Customer");
			System.out.println("5. Add Room");
			System.out.println("6. Book Room");
			System.out.println("7. Update Room");
			System.out.println("8. Delete Room");
			System.out.println("9. Check In");
			System.out.println("10. Check Out");
			System.out.println("11. Add Payment");
			System.out.println("12. Exit");
			System.out.print("Enter your choice: ");

			int choice = sc.nextInt();
			sc.nextLine(); // consume newline

			switch (choice) {
				case 1 -> addCustomer();
				case 2 -> viewCustomers();
				case 3 -> updateCustomer();
				case 4 -> deleteCustomer();
				case 5 -> addRoom();
				case 6 -> bookRoom();
				case 7 -> updateRoom();
				case 8 -> deleteRoom();
				case 9 -> checkIn();
				case 10 -> checkOut();
				case 11 -> addPayment();
				case 12 -> {
					running = false;
					Thread.sleep(2000);
					System.out.println("Exiting... Thanks for visiting !");
				}
				default -> System.out.println("Invalid choice! Try again.");
			}
		}

		sc.close();
	}

	// 1.Add Customer
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

	// 2.View Customers
	private static void viewCustomers() {
		List<Customers> customers = customerService.getAllCustomers();
		System.out.println("\n--- Customers ---");
		for (Customers c : customers) {
			System.out.println(c);
		}
	}

	// 3.Update Customer
	private static void updateCustomer() {
		System.out.print("Enter Customer ID to update: ");
		int id = sc.nextInt();
		sc.nextLine();

		System.out.print("Enter New Name: ");
		String name = sc.nextLine();
		System.out.print("Enter New Phone: ");
		String phone = sc.nextLine();
		System.out.print("Enter New Email: ");
		String email = sc.nextLine();
		System.out.print("Enter New Address: ");
		String address = sc.nextLine();

		Customers customer = new Customers(id, name, phone, email, address);
		boolean success = customerService.updateCustomer(id, customer);

		if (success) System.out.println("Customer updated successfully!");
		else System.out.println("Failed to update customer.");
	}

	// 4.Delete Customer
		private static void deleteCustomer() {
			System.out.print("Enter Customer ID to delete: ");
			int id = sc.nextInt();
			sc.nextLine();

			boolean success = customerService.deleteCustomer(id);

			if (success) System.out.println("Customer deleted successfully!");
			else System.out.println("Failed to delete customer.");
		}

	// 5.Add Room
	private static void addRoom() {
		System.out.print("Enter Room Number: ");
		int number = sc.nextInt();
		sc.nextLine();
		System.out.print("Enter Room Type (Deluxe, Standard, Luxury): ");
		String type = sc.nextLine();
		System.out.print("Enter Price per Day: ");
		double price = sc.nextDouble();
		sc.nextLine();

		Rooms room = new Rooms(0, number, type, price, "AVAILABLE");
		boolean success = roomService.addRoom(room);
		if (success) System.out.println("Room added successfully!");
		else System.out.println("Failed to add room.");
	}

	// 6.Book Room
	private static void bookRoom() {
		System.out.print("Enter Customer ID: ");
		int customerId = sc.nextInt();
		System.out.print("Enter Room Number: ");
		int number = sc.nextInt();
		sc.nextLine();
		System.out.print("Enter Check-in Date (YYYY-MM-DD): ");
		String checkIn = sc.nextLine().trim();
		System.out.print("Enter Check-out Date (YYYY-MM-DD): ");
		String checkOut = sc.nextLine().trim();
		System.out.print("Enter Total Amount: ");
		double amount = sc.nextDouble();
		sc.nextLine();

		Bookings booking = new Bookings(0, customerId, number,
				Date.valueOf(checkIn), Date.valueOf(checkOut), amount, "BOOKED");

		boolean success = bookingService.bookRoom(booking);
		if (success) System.out.println("Room booked successfully!");
		else System.out.println("Failed to book room.");
	}

	// 7.Update Room
	private static void updateRoom() {
		System.out.print("Enter Room ID to update: ");
		int id = sc.nextInt();
		sc.nextLine();

		System.out.print("Enter New Room Number: ");
		int number = sc.nextInt();
		sc.nextLine();

		System.out.print("Enter New Room Type: ");
		String type = sc.nextLine();

		System.out.print("Enter New Price per Day: ");
		double price = sc.nextDouble();
		sc.nextLine();

		System.out.print("Enter New Status (AVAILABLE / BOOKED): ");
		String status = sc.nextLine();

		Rooms room = new Rooms(id, number, type, price, status);
		boolean success = roomService.updateRoom(id, room);

		if (success) System.out.println("Room updated successfully!");
		else System.out.println("Failed to update room.");
	}

	// 8.Delete Room
	private static void deleteRoom() {
		System.out.print("Enter Room Number to delete: ");
		int id = sc.nextInt();
		sc.nextLine();

		boolean success = roomService.deleteRoom(id);

		if (success) System.out.println("Room deleted successfully!");
		else System.out.println("Failed to delete room.");
	}

	// 9.Check In
	private static void checkIn() {
		System.out.print("Enter Booking ID for Check-in: ");
		int bookingId = sc.nextInt();
		sc.nextLine();

		boolean success = bookingService.checkIn(bookingId);
		if (success) System.out.println("Checked in successfully!");
		else System.out.println("Booking ID not found.");
	}

	// 10.Check Out
	private static void checkOut() {
		System.out.print("Enter Booking ID for Check-out: ");
		int bookingId = sc.nextInt();
		sc.nextLine();

		boolean success = bookingService.checkOut(bookingId);
		if (success) System.out.println("Checked out successfully!");
		else System.out.println("Booking ID not found.");
	}

	// 11.Add Payment
	private static  void addPayment() {
		System.out.print("Enter Booking ID: ");
		int bookingId = sc.nextInt();
		sc.nextLine();

		System.out.print("Enter Amount: ");
		double amount = sc.nextDouble();
		sc.nextLine();

		System.out.println("Select Payment Method:");
		System.out.println("1. CASH");
		System.out.println("2. CARD");
		System.out.println("3. UPI");

		int methodChoice = sc.nextInt();
		sc.nextLine();

		String method = "";

		switch (methodChoice) {
			case 1: method = "CASH"; break;
			case 2: method = "CARD"; break;
			case 3: method = "UPI"; break;
			default:
				System.out.println("Invalid choice");
				break;
		}

		Payments payment = new Payments(0, bookingId, new java.sql.Date(System.currentTimeMillis()), amount, method);
		boolean success = billingService.makePayment(payment);
		if (success) System.out.println("Payment added successfully!");
		else System.out.println("Failed to add payment.");
	}
}

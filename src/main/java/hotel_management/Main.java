package hotel_management;

import hotel_management.util.DBConnection;

import java.sql.*;
import java.util.Scanner;

public class Main {
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
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
				case 1:
					addCustomer(sc);
					break;
				case 2:
					viewCustomers();
					break;
				case 3:
					addRoom(sc);
					break;
				case 4:
					bookRoom(sc);
					break;
				case 5:
					checkIn(sc);
					break;
				case 6:
					checkOut(sc);
					break;
				case 7:
					running = false;
					System.out.println("Exiting... Goodbye!");
					break;
				default:
					System.out.println("Invalid choice! Try again.");
			}
		}

		sc.close();
	}

	// 1️⃣ Add Customer
	public static void addCustomer(Scanner sc) {
		try (Connection conn = DBConnection.getConnection()) {
			System.out.print("Enter Name: ");
			String name = sc.nextLine();
			System.out.print("Enter Phone: ");
			String phone = sc.nextLine();
			System.out.print("Enter Email: ");
			String email = sc.nextLine();
			System.out.print("Enter Address: ");
			String address = sc.nextLine();

			String sql = "INSERT INTO customers (name, phone, email, address) VALUES (?, ?, ?, ?)";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, name);
			ps.setString(2, phone);
			ps.setString(3, email);
			ps.setString(4, address);

			int rows = ps.executeUpdate();
			if (rows > 0) System.out.println("Customer added successfully!");
		} catch (SQLException e) {
			System.out.println("Error adding customer: " + e.getMessage());
		}
	}

	// 2️⃣ View Customers
	public static void viewCustomers() {
		try (Connection conn = DBConnection.getConnection()) {
			String sql = "SELECT * FROM customers";
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);

			System.out.println("\n--- Customers ---");
			while (rs.next()) {
				System.out.println("ID: " + rs.getInt("customer_id") +
						", Name: " + rs.getString("name") +
						", Phone: " + rs.getString("phone") +
						", Email: " + rs.getString("email") +
						", Address: " + rs.getString("address"));
			}
		} catch (SQLException e) {
			System.out.println("Error viewing customers: " + e.getMessage());
		}
	}

	// 3️⃣ Add Room
	public static void addRoom(Scanner sc) {
		try (Connection conn = DBConnection.getConnection()) {
			System.out.print("Enter Room Number: ");
			int roomNumber = sc.nextInt();
			sc.nextLine();
			System.out.print("Enter Room Type: ");
			String type = sc.nextLine();
			System.out.print("Enter Price per Day: ");
			double price = sc.nextDouble();
			sc.nextLine();

			String sql = "INSERT INTO rooms (room_number, type, price_per_day) VALUES (?, ?, ?)";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, roomNumber);
			ps.setString(2, type);
			ps.setDouble(3, price);

			int rows = ps.executeUpdate();
			if (rows > 0) System.out.println("Room added successfully!");
		} catch (SQLException e) {
			System.out.println("Error adding room: " + e.getMessage());
		}
	}

	// 4️⃣ Book Room
	public static void bookRoom(Scanner sc) {
		try (Connection conn = DBConnection.getConnection()) {
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

			String sql = "INSERT INTO bookings (customer_id, room_id, check_in, check_out, total_amount) VALUES (?, ?, ?, ?, ?)";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, customerId);
			ps.setInt(2, roomId);
			ps.setDate(3, Date.valueOf(checkIn));
			ps.setDate(4, Date.valueOf(checkOut));
			ps.setDouble(5, amount);

			int rows = ps.executeUpdate();
			if (rows > 0) System.out.println("Room booked successfully!");
		} catch (SQLException e) {
			System.out.println("Error booking room: " + e.getMessage());
		}
	}

	// 5️⃣ Check In
	public static void checkIn(Scanner sc) {
		try (Connection conn = DBConnection.getConnection()) {
			System.out.print("Enter Booking ID for Check-in: ");
			int bookingId = sc.nextInt();
			sc.nextLine();

			String sql = "UPDATE bookings SET status='CHECKED_IN' WHERE booking_id=?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, bookingId);

			int rows = ps.executeUpdate();
			if (rows > 0) System.out.println("Checked in successfully!");
			else System.out.println("Booking ID not found.");
		} catch (SQLException e) {
			System.out.println("Error during check-in: " + e.getMessage());
		}
	}

	// 6️⃣ Check Out
	public static void checkOut(Scanner sc) {
		try (Connection conn = DBConnection.getConnection()) {
			System.out.print("Enter Booking ID for Check-out: ");
			int bookingId = sc.nextInt();
			sc.nextLine();

			String sql = "UPDATE bookings SET status='CHECKED_OUT' WHERE booking_id=?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, bookingId);

			int rows = ps.executeUpdate();
			if (rows > 0) System.out.println("Checked out successfully!");
			else System.out.println("Booking ID not found.");
		} catch (SQLException e) {
			System.out.println("Error during check-out: " + e.getMessage());
		}
	}
	}

package hotel_management;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Hotel {
	
	private static String URL="jdbc:mysql://localhost:3306/hotel_management";
	private static String USERNAME="root";
	private static String PASSWORD="kunal@123";
	
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			System.out.println(e.getMessage());
		}
		
		try {
			Connection connection = DriverManager.getConnection(URL,USERNAME,PASSWORD);
			System.out.println(connection);
			while(true) {
				System.out.println("Hotel Management!!");
				Scanner sc = new Scanner(System.in);
				System.out.println("1.Reserve a Room");
				System.out.println("2.View Reservation");
				System.out.println("3.get Room Number");
				System.out.println("4.Update Reservation");
				System.out.println("5.Delete Reservation");
				System.out.println("6.Exit");
				System.out.println("Choice Option");
				 
				int choice = sc.nextInt();
				
				switch(choice) {
					case 1:
						reserveRoom(connection, sc);
						break;
					case 2:
						viewResevation(connection);
						break;
					case 3:
						getRoomNumber(connection, sc);
						break;
					case 4:
						updateReservation(connection, sc);
						break;
					case 5:
						deleteReservation(connection, sc);
						break;
					case 6:
						exit();
						sc.close();
						return;
					default:
						System.out.println("Invalid Choice");
				}
			}
		}
		catch(SQLException e) {
			System.out.println(e.getMessage());
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
		// option 1. 
		public static void reserveRoom(Connection connection, Scanner sc){
			try {
				System.out.println("Enter Guest Name");
				String name=sc.next();
				sc.nextLine();
				System.out.println("Enter Room Number");
				int number=sc.nextInt();
				System.out.println("Enter Contact Number");
				String contact=sc.next();
				
				String sql="INSERT INTO reservation(guest_name,room_number,contact)"
						+ "value('"+name+"',"+number+",'"+contact+"')";
				Statement stmt = connection.createStatement();
				int res=stmt.executeUpdate(sql);
				if(res>0)
					System.out.println("Reservation Successfull!!✅");
				else System.out.println("Reservation Failed!! ❌");
			}
				
			catch(SQLException e) {
				System.out.println(e.getMessage());
	}
		}

		// option . 2
		private static void viewResevation(Connection connection) throws SQLException {
		String sql="SELECT id,guest_name,room_number, contact FROM Reservation";
		Statement stmt = connection.createStatement();
		ResultSet rs=stmt.executeQuery(sql);
		System.out.println("Current reservation!!");
		
		while(rs.next()) {
			int id=rs.getInt("id");
			String name=rs.getString("guest_name");
			int room=rs.getInt("room_number");
			int contact=rs.getInt("contact");
			System.out.println("Guest Id: "+id+"\tGuest Name "+name+"\tRoom Number "+room+"\tContact: "+contact);
		}
		
	}
		// option . 3
		private static void getRoomNumber(Connection connection, Scanner sc) {
			System.out.println("Enter Id");
			int id=sc.nextInt();
			System.out.println("Enter Guest Name");
			String name=sc.next();
			
			String sql = "SELECT room_number FROM Reservation WHERE id = " + id + " AND guest_name = '" + name + "'";
			
		try {
			Statement stmt=connection.createStatement();
			ResultSet rs=stmt.executeQuery(sql);
			if(rs.next()) {
				int room =rs.getInt("room_number");
				System.out.println("Guest Id: "+id+" Guest Name: "+name+" And Your Room Number is: "+room);
			}
			else 
				System.out.println("Reservation Room Number  is not found for given guest Id");
		}
		catch(SQLException e) {
			System.out.println(e.getMessage());
		}
		
		
	}
		// option 4. 
		private static void updateReservation(Connection connection, Scanner sc) throws SQLException {
			System.out.println("Enter Id to update");
			int id=sc.nextInt();
			sc.nextLine();
			
			if(!reservationExists(connection, id)) {
				System.out.println("Reservation is not found for given id"+id);
				return;
			}
			
			System.out.println("Enter new Guest Name ");
			String name=sc.next();
			System.out.println("Enter new Room Number");
			int room=sc.nextInt();
			System.out.println("Enter new Contact Number");
			int contact=sc.nextInt();
			
			String sql = "UPDATE reservation SET guest_name = '" + name + 
		             "', room_number = " + room + 
		             ", contact = '" + contact + 
		             "' WHERE id = " + id;
		
		try{
			Statement stmt=connection.createStatement();
		int res=stmt.executeUpdate(sql);
		
		if(res>0)
			System.out.println("Reservation Successfully Updated!!");
		else
			System.out.println("Reservation Updation Failed!!");
	}
		catch(SQLException e) {
		System.out.println(e.getMessage());
		}
}
		// option 5. 
		private static void deleteReservation(Connection connection, Scanner sc) {
			System.out.println("Enter Reservation id");
			int id = sc.nextInt();
			
			if(!reservationExists(connection, id)) {
				System.out.println("Reservation is not found for given id"+id);
				return;
			}
			
			String sql = "DELETE FROM Reservation WHERE id = " + id;
			
			try {
				Statement stmt=connection.createStatement();
				int rs=stmt.executeUpdate(sql);
				
				if(rs>0)
					System.out.println("Reservation Deleted Successfully!!");
				else
					System.out.println("Reservation Deletion Failed!!");
			}
			catch(SQLException e) {
			System.out.println(e.getMessage());
			}
		
	}
		// option exists
		public static boolean reservationExists(Connection connection, int id) {
			try{
				String sql = "SELECT id FROM Reservation WHERE id = " + id;

			
			try (Statement stmt=connection.createStatement();
				ResultSet rs=stmt.executeQuery(sql)){
				return rs.next();
				}
			}
			
			catch(SQLException e) {
				System.out.println(e.getMessage());
				return false;
			}
			
}
		// option 6. 
		private static void exit () throws InterruptedException {
			System.out.print("Existing");
			int i=10;
			while(i!=0) {
				System.out.print(".");
				Thread.sleep(1000);
				i--;
			}
			System.out.println();
	}

}

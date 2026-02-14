package hotel_management.service;

import hotel_management.model.Rooms;
import hotel_management.util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RoomService {

    // Add a new room
    public boolean addRoom(Rooms room) {
        String sql = "INSERT INTO rooms (room_number, type, price_per_day, status) VALUES (?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, room.getRoomNumber());
            ps.setString(2, room.getType());
            ps.setDouble(3, room.getPricePerDay());
            ps.setString(4, room.getStatus());

            int rows = ps.executeUpdate();
            return rows > 0;

        } catch (SQLException e) {
            System.out.println("Error adding room: " + e.getMessage());
            return false;
        }
    }

    // Get all rooms
    public List<Rooms> getAllRooms() {
        List<Rooms> rooms = new ArrayList<>();
        String sql = "SELECT * FROM rooms";

        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Rooms r = new Rooms(
                        rs.getInt("room_id"),
                        rs.getInt("room_number"),
                        rs.getString("type"),
                        rs.getDouble("price_per_day"),
                        rs.getString("status")
                );
                rooms.add(r);
            }

        } catch (SQLException e) {
            System.out.println("Error fetching rooms: " + e.getMessage());
        }

        return rooms;
    }

    // Optionally, get available rooms only
    public List<Rooms> getAvailableRooms() {
        List<Rooms> rooms = new ArrayList<>();
        String sql = "SELECT * FROM rooms WHERE status='AVAILABLE'";

        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Rooms r = new Rooms(
                        rs.getInt("room_id"),
                        rs.getInt("room_number"),
                        rs.getString("type"),
                        rs.getDouble("price_per_day"),
                        rs.getString("status")
                );
                rooms.add(r);
            }

        } catch (SQLException e) {
            System.out.println("Error fetching available rooms: " + e.getMessage());
        }

        return rooms;
    }
}

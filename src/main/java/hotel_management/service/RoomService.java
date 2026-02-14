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

    // Update Room
    public List<Rooms> updateRoom(String id, Rooms room) {
        List<Rooms> rooms = new ArrayList<>();
        return rooms;

    }
    // Update Room
    public boolean deleteRoom(String id) {
        String sql = "DELETE FROM rooms WHERE room_id = " + id;
        return true;
    }
}

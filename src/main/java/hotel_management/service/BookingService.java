package hotel_management.service;

import hotel_management.model.Bookings;
import hotel_management.util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

    public class BookingService {

        // Book a room
        public boolean bookRoom(Bookings booking) {
            String sql = "INSERT INTO bookings (customer_id, room_id, check_in, check_out, total_amount) VALUES (?, ?, ?, ?, ?)";
            try (Connection conn = DBConnection.getConnection();
                 PreparedStatement ps = conn.prepareStatement(sql)) {

                ps.setInt(1, booking.getCustomerId());
                ps.setInt(2, booking.getRoomId());
                ps.setDate(3, (Date) booking.getCheckIn());
                ps.setDate(4, (Date) booking.getCheckOut());
                ps.setDouble(5, booking.getTotalAmount());

                int rows = ps.executeUpdate();
                return rows > 0;
            } catch (SQLException e) {
                System.out.println("Error booking room: " + e.getMessage());
                return false;
            }
        }

        // Check-in
        public boolean checkIn(int bookingId) {
            return updateBookingStatus(bookingId, "CHECKED_IN");
        }

        // Check-out
        public boolean checkOut(int bookingId) {
            return updateBookingStatus(bookingId, "CHECKED_OUT");
        }

        // Helper method to update status
        private boolean updateBookingStatus(int bookingId, String status) {
            String sql = "UPDATE bookings SET status=? WHERE booking_id=?";
            try (Connection conn = DBConnection.getConnection();
                 PreparedStatement ps = conn.prepareStatement(sql)) {

                ps.setString(1, status);
                ps.setInt(2, bookingId);

                int rows = ps.executeUpdate();
                return rows > 0;
            } catch (SQLException e) {
                System.out.println("Error updating booking status: " + e.getMessage());
                return false;
            }
        }

        // Get all bookings
        public List<Bookings> getAllBookings() {
            List<Bookings> bookings = new ArrayList<>();
            String sql = "SELECT * FROM bookings";

            try (Connection conn = DBConnection.getConnection();
                 Statement stmt = conn.createStatement();
                 ResultSet rs = stmt.executeQuery(sql)) {

                while (rs.next()) {
                    Bookings b = new Bookings(
                            rs.getInt("booking_id"),
                            rs.getInt("customer_id"),
                            rs.getInt("room_id"),
                            rs.getDate("check_in"),
                            rs.getDate("check_out"),
                            rs.getDouble("total_amount"),
                            rs.getString("status")
                    );
                    bookings.add(b);
                }

            } catch (SQLException e) {
                System.out.println("Error fetching bookings: " + e.getMessage());
            }

            return bookings;
        }
    }

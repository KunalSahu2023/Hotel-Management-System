package hotel_management.service;

import hotel_management.model.Payments;
import hotel_management.util.DBConnection;

import java.sql.*;

    public class BillingService {

        // Make a payment
        public boolean makePayment(Payments payment) {
            String sql = "INSERT INTO payments (booking_id, payment_date, amount, payment_method) VALUES (?, ?, ?, ?)";
            try (Connection conn = DBConnection.getConnection();
                 PreparedStatement ps = conn.prepareStatement(sql)) {

                ps.setInt(1, payment.getBookingId());
                ps.setDate(2, (Date) payment.getPaymentDate());
                ps.setDouble(3, payment.getAmount());
                ps.setString(4, payment.getPaymentMethod());

                int rows = ps.executeUpdate();
                return rows > 0;

            } catch (SQLException e) {
                System.out.println("Error processing payment: " + e.getMessage());
                return false;
            }
        }

        // Optionally, get total payments for a booking
        public double getTotalPaid(int bookingId) {
            String sql = "SELECT SUM(amount) AS total FROM payments WHERE booking_id=?";
            try (Connection conn = DBConnection.getConnection();
                 PreparedStatement ps = conn.prepareStatement(sql)) {

                ps.setInt(1, bookingId);
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    return rs.getDouble("total");
                }

            } catch (SQLException e) {
                System.out.println("Error calculating total payment: " + e.getMessage());
            }
            return 0;
        }
}

package model;

import java.util.Date;

public class Bookings {
    private int bookingId;
    private int customerId;  // Foreign key to Customer
    private int roomId;      // Foreign key to Room
    private Date checkIn;
    private Date checkOut;
    private double totalAmount;
    private String status;

    // Default constructor
    public Bookings() {}

    // Parameterized constructor
    public Bookings(int bookingId, int customerId, int roomId, Date checkIn, Date checkOut, double totalAmount, String status) {
        this.bookingId = bookingId;
        this.customerId = customerId;
        this.roomId = roomId;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.totalAmount = totalAmount;
        this.status = status;
    }

    // Getters and Setters
    public int getBookingId() {
        return bookingId;
    }

    public void setBookingId(int bookingId) {
        this.bookingId = bookingId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    public Date getCheckIn() {
        return checkIn;
    }

    public void setCheckIn(Date checkIn) {
        this.checkIn = checkIn;
    }

    public Date getCheckOut() {
        return checkOut;
    }

    public void setCheckOut(Date checkOut) {
        this.checkOut = checkOut;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    // toString() method
    @Override
    public String toString() {
        return "Booking [bookingId=" + bookingId + ", customerId=" + customerId + ", roomId=" + roomId
                + ", checkIn=" + checkIn + ", checkOut=" + checkOut
                + ", totalAmount=" + totalAmount + ", status=" + status + "]";
    }
}

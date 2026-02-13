package model;

import java.util.Date;

public class Payments {
    private int paymentId;
    private int bookingId;       // Foreign key to Booking
    private Date paymentDate;
    private double amount;
    private String paymentMethod;

    // Default constructor
    public Payments() {}

    // Parameterized constructor
    public Payments(int paymentId, int bookingId, Date paymentDate, double amount, String paymentMethod) {
        this.paymentId = paymentId;
        this.bookingId = bookingId;
        this.paymentDate = paymentDate;
        this.amount = amount;
        this.paymentMethod = paymentMethod;
    }

    // Getters and Setters
    public int getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(int paymentId) {
        this.paymentId = paymentId;
    }

    public int getBookingId() {
        return bookingId;
    }

    public void setBookingId(int bookingId) {
        this.bookingId = bookingId;
    }

    public Date getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    // toString() method
    @Override
    public String toString() {
        return "Payment [paymentId=" + paymentId + ", bookingId=" + bookingId + ", paymentDate=" + paymentDate
                + ", amount=" + amount + ", paymentMethod=" + paymentMethod + "]";
    }
}

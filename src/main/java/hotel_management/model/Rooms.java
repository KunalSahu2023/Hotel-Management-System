package hotel_management.model;

public class Rooms {
    private int roomId;
    private int roomNumber;
    private String type;
    private double pricePerDay;
    private String status;

    // Default constructor
    public Rooms() {}

    // Parameterized constructor
    public Rooms(int roomId, int roomNumber, String type, double pricePerDay, String status) {
        this.roomId = roomId;
        this.roomNumber = roomNumber;
        this.type = type;
        this.pricePerDay = pricePerDay;
        this.status = status;
    }

    // Getters and Setters
    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getPricePerDay() {
        return pricePerDay;
    }

    public void setPricePerDay(double pricePerDay) {
        this.pricePerDay = pricePerDay;
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
        return "Room [roomId=" + roomId + ", roomNumber=" + roomNumber + ", type=" + type
                + ", pricePerDay=" + pricePerDay + ", status=" + status + "]";
    }
}

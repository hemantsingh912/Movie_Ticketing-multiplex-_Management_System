package Model;

import java.sql.Timestamp;

public class BookingModel {
    
    private int bookingId;
    private int customerId;
    private int showId;
    private Timestamp bookingDate;
    private double totalAmount;
    private String bookingBy;
    private String bookingType;
    private String bookingStatus;

    public BookingModel() {}

    

public BookingModel(int bookingId, int customerId, int showId, Timestamp bookingDate, double totalAmount,
        String bookingBy, String bookingType, String bookingStatus) {
    this.bookingId = bookingId;
    this.customerId = customerId;
    this.showId = showId;
    this.bookingDate = bookingDate;
    this.totalAmount = totalAmount;
    this.bookingBy = bookingBy;
    this.bookingType = bookingType;
    this.bookingStatus = bookingStatus;
}
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
public int getShowId() {
    return showId;
}
public void setShowId(int showId) {
    this.showId = showId;
}
public Timestamp getBookingDate() {
    return bookingDate;
}
public void setBookingDate(Timestamp bookingDate) {
    this.bookingDate = bookingDate;
}
public double getTotalAmount() {
    return totalAmount;
}
public void setTotalAmount(double totalAmount) {
    this.totalAmount = totalAmount;
}
public String getBookingBy() {
    return bookingBy;
}
public void setBookingBy(String bookingBy) {
    this.bookingBy = bookingBy;
}
public String getBookingType() {
    return bookingType;
}
public void setBookingType(String bookingType) {
    this.bookingType = bookingType;
}
public String getBookingStatus() {
    return bookingStatus;
}
public void setBookingStatus(String bookingStatus) {
    this.bookingStatus = bookingStatus;
}
}

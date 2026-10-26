package Model;

import java.sql.Timestamp;

public class TicketModel {
    private int ticketId;
    private int bookingId;
    private String ticketNumber;
    private Timestamp generatedDate;

    public TicketModel() {}

        
    public TicketModel(int ticketId, int bookingId, String ticketNumber, Timestamp generatedDate) {
        this.ticketId = ticketId;
        this.bookingId = bookingId;
        this.ticketNumber = ticketNumber;
        this.generatedDate = generatedDate;
    }
    public int getTicketId() {
        return ticketId;
    }
    public void setTicketId(int ticketId) {
        this.ticketId = ticketId;
    }
    public int getBookingId() {
        return bookingId;
    }
    public void setBookingId(int bookingId) {
        this.bookingId = bookingId;
    }
    public String getTicketNumber() {
        return ticketNumber;
    }
    public void setTicketNumber(String ticketNumber) {
        this.ticketNumber = ticketNumber;
    }
    public Timestamp getGeneratedDate() {
        return generatedDate;
    }
    public void setGeneratedDate(Timestamp generatedDate) {
        this.generatedDate = generatedDate;
    }
}

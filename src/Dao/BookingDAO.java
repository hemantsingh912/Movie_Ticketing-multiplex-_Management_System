package Dao;

import Db.DBConnection;
import Model.BookingModel;
import java.sql.*;

public class BookingDAO {

    Connection con;

    public BookingDAO() {
        con = DBConnection.getConnection();
    }

    // Insert Booking
    public boolean insertBooking(BookingModel b) {

        try {

            String sql =
            "INSERT INTO booking(customerId,showId,bookingDate,totalAmount,bookingBy,bookingType,bookingStatus) VALUES(?,?,?,?,?,?,?)";

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, b.getCustomerId());
            ps.setInt(2, b.getShowId());
            ps.setTimestamp(3, b.getBookingDate());
            ps.setDouble(4, b.getTotalAmount());
            ps.setString(5, b.getBookingBy());
            ps.setString(6, b.getBookingType());
            ps.setString(7, b.getBookingStatus());

            return ps.executeUpdate() > 0;

        } catch(Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    // Update Booking
    public boolean updateBooking(BookingModel b) {

        try {

            String sql =
            "UPDATE booking SET customerId=?,showId=?,bookingDate=?,totalAmount=?,bookingBy=?,bookingType=?,bookingStatus=? WHERE bookingId=?";

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, b.getCustomerId());
            ps.setInt(2, b.getShowId());
            ps.setTimestamp(3, b.getBookingDate());
            ps.setDouble(4, b.getTotalAmount());
            ps.setString(5, b.getBookingBy());
            ps.setString(6, b.getBookingType());
            ps.setString(7, b.getBookingStatus());
            ps.setInt(8, b.getBookingId());

            return ps.executeUpdate() > 0;

        } catch(Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    // Delete Booking
    public boolean deleteBooking(int bookingId) {

        try {

            String sql =
            "DELETE FROM booking WHERE bookingId=?";

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, bookingId);

            return ps.executeUpdate() > 0;

        } catch(Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    // Search Booking
    public ResultSet getBookingById(int bookingId) {

        try {

            String sql =
            "SELECT * FROM booking WHERE bookingId=?";

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, bookingId);

            return ps.executeQuery();

        } catch(Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    // Show All Booking
    public ResultSet getAllBookings() {

        try {

            String sql = "SELECT * FROM booking";

            PreparedStatement ps = con.prepareStatement(sql);

            return ps.executeQuery();

        } catch(Exception e) {
            e.printStackTrace();
        }

        return null;
    }

   /*  public static void main(String[] args){
       BookingModel b = new BookingModel();

        b.setCustomerId(19);
        b.setShowId(1);
        b.setBookingDate(new Timestamp(System.currentTimeMillis()));
        b.setTotalAmount(450);

        b.setBookingBy("Customer");
        b.setBookingType("Online");
        b.setBookingStatus("Confirmed");

        BookingDAO dao = new BookingDAO();

        if(dao.insertBooking(b))
            System.out.println("Booking Added Successfully");
        else
            System.out.println("Booking Failed");
    }*/
}
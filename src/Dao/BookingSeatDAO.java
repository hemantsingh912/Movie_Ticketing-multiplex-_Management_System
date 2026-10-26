package Dao;

import Db.DBConnection;
import Model.BookingSeatModel;
import java.sql.*;

public class BookingSeatDAO {

    Connection con;

    public BookingSeatDAO() {
        con = DBConnection.getConnection();
    }

    // Insert
    public boolean insertBookingSeat(BookingSeatModel bs) {

        try {

            String sql =
            "INSERT INTO booking_seat(bookingId, seatId) VALUES(?,?)";

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, bs.getBookingId());
            ps.setInt(2, bs.getSeatId());

            return ps.executeUpdate() > 0;

        } catch(Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    // Update
    public boolean updateBookingSeat(BookingSeatModel bs) {

        try {

            String sql =
            "UPDATE booking_seat SET bookingId=?, seatId=? WHERE bookingSeatId=?";

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, bs.getBookingId());
            ps.setInt(2, bs.getSeatId());
            ps.setInt(3, bs.getBookingSeatId());

            return ps.executeUpdate() > 0;

        } catch(Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    // Delete
    public boolean deleteBookingSeat(int bookingSeatId) {

        try {

            String sql =
            "DELETE FROM booking_seat WHERE bookingSeatId=?";

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, bookingSeatId);

            return ps.executeUpdate() > 0;

        } catch(Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    // Search By ID
    public ResultSet getBookingSeatById(int bookingSeatId) {

        try {

            String sql =
            "SELECT * FROM booking_seat WHERE bookingSeatId=?";

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, bookingSeatId);

            return ps.executeQuery();

        } catch(Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    // Show All
    public ResultSet getAllBookingSeats() {

        try {

            String sql = "SELECT * FROM booking_seat";

            PreparedStatement ps = con.prepareStatement(sql);

            return ps.executeQuery();

        } catch(Exception e) {
            e.printStackTrace();
        }

        return null;
    }
    /*public static void main(String[] args) {
        BookingSeatModel bs = new BookingSeatModel();

            bs.setBookingId(10);
            bs.setSeatId(1);

            BookingSeatDAO dao = new BookingSeatDAO();

            if(dao.insertBookingSeat(bs))
                System.out.println("Booking Seat Added");
            else
                System.out.println("Failed");
    }*/
}
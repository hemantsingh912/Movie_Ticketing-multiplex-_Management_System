package Dao;

import Db.DBConnection;
import Model.TicketModel;
import java.sql.*;

public class TicketDAO {

    Connection con;

    public TicketDAO() {
        con = DBConnection.getConnection();
    }

    // =========================
    // INSERT TICKET
    // =========================
    public boolean insertTicket(TicketModel t) {

        try {
            String sql = "INSERT INTO ticket(bookingId, ticketNumber, generatedDate) VALUES(?,?,?)";

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, t.getBookingId());
            ps.setString(2, t.getTicketNumber());
            ps.setTimestamp(3, t.getGeneratedDate());

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    // =========================
    // UPDATE TICKET
    // =========================
    public boolean updateTicket(TicketModel t) {

        try {
            String sql = "UPDATE ticket SET bookingId=?, ticketNumber=?, generatedDate=? WHERE ticketId=?";

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, t.getBookingId());
            ps.setString(2, t.getTicketNumber());
            ps.setTimestamp(3, t.getGeneratedDate());
            ps.setInt(4, t.getTicketId());

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    // =========================
    // DELETE TICKET
    // =========================
    public boolean deleteTicket(int ticketId) {

        try {
            String sql = "DELETE FROM ticket WHERE ticketId=?";

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, ticketId);

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    // =========================
    // GET TICKET BY ID
    // =========================
    public ResultSet getTicketById(int ticketId) {

        try {
            String sql = "SELECT * FROM ticket WHERE ticketId=?";

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, ticketId);

            return ps.executeQuery();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    // =========================
    // GET ALL TICKETS
    // =========================
    public ResultSet getAllTickets() {

        try {
            String sql = "SELECT * FROM ticket";

            PreparedStatement ps = con.prepareStatement(sql);

            return ps.executeQuery();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    /*public static void main(String[] args) {

    TicketDAO dao = new TicketDAO();

    TicketModel t = new TicketModel();

    t.setBookingId(10);
    t.setTicketNumber("MOV1005");
    t.setGeneratedDate(new java.sql.Timestamp(System.currentTimeMillis()));

    if (dao.insertTicket(t))
        System.out.println("Ticket Generated Successfully");
    else
        System.out.println("Ticket Failed");

    System.out.println("\nAll Tickets:");

    try {
        java.sql.ResultSet rs = dao.getAllTickets();

        while (rs.next()) {
            System.out.println(
                rs.getInt("ticketId") + " | " +
                rs.getInt("bookingId") + " | " +
                rs.getString("ticketNumber") + " | " +
                rs.getTimestamp("generatedDate")
            );
        }

    } catch (Exception e) {
        e.printStackTrace();
    }
}*/
}
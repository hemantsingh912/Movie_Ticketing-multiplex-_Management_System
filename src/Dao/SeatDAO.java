package Dao;

import Db.DBConnection;
import Model.SeatModel;
import java.sql.*;

public class SeatDAO {

    Connection con;

    public SeatDAO() {
        con = DBConnection.getConnection();
    }

    // Insert Seat
    public boolean insertSeat(SeatModel s) {

        try {

            String sql =
            "INSERT INTO seat(screenId,seatNumber,seatType,seatStatus) VALUES(?,?,?,?)";

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, s.getScreenId());
            ps.setString(2, s.getSeatNumber());
            ps.setString(3, s.getSeatType());
            ps.setString(4, s.getSeatStatus());

            return ps.executeUpdate() > 0;

        } catch(Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    // Update Seat
    public boolean updateSeat(SeatModel s) {

        try {

            String sql =
            "UPDATE seat SET screenId=?,seatNumber=?,seatType=?,seatStatus=? WHERE seatId=?";

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, s.getScreenId());
            ps.setString(2, s.getSeatNumber());
            ps.setString(3, s.getSeatType());
            ps.setString(4, s.getSeatStatus());
            ps.setInt(5, s.getSeatId());

            return ps.executeUpdate() > 0;

        } catch(Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    // Delete Seat
    public boolean deleteSeat(int seatId) {

        try {

            String sql = "DELETE FROM seat WHERE seatId=?";

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, seatId);

            return ps.executeUpdate() > 0;

        } catch(Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    // Search Seat
    public ResultSet getSeatById(int seatId) {

        try {

            String sql = "SELECT * FROM seat WHERE seatId=?";

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, seatId);

            return ps.executeQuery();

        } catch(Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    // Show All Seats
    public ResultSet getAllSeats() {

        try {

            String sql = "SELECT * FROM seat";

            PreparedStatement ps = con.prepareStatement(sql);

            return ps.executeQuery();

        } catch(Exception e) {
            e.printStackTrace();
        }

        return null;
    }

  public void generateSeats(int screenId,
                          int regular,
                          int premium,
                          int gold) {

    try {

        for(int i=1;i<=regular;i++) {

            SeatModel s = new SeatModel();

            s.setScreenId(screenId);
            s.setSeatNumber("R"+i);
            s.setSeatType("Regular");
            s.setSeatStatus("Available");

            insertSeat(s);
        }

        for(int i=1;i<=premium;i++) {

            SeatModel s = new SeatModel();

            s.setScreenId(screenId);
            s.setSeatNumber("P"+i);
            s.setSeatType("Premium");
            s.setSeatStatus("Available");

            insertSeat(s);
        }

        for(int i=1;i<=gold;i++) {

            SeatModel s = new SeatModel();

            s.setScreenId(screenId);
            s.setSeatNumber("G"+i);
            s.setSeatType("Gold");
            s.setSeatStatus("Available");

            insertSeat(s);
        }

    } catch(Exception e) {

        e.printStackTrace();
    }

}

    // Get Seats By Screen
public ResultSet getSeatsByScreen(int screenId) {

    try {

        String sql = "SELECT * FROM seat WHERE screenId=? ORDER BY seatId";

        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1, screenId);

        return ps.executeQuery();

    } catch (Exception e) {
        e.printStackTrace();
    }

    return null;
}

    public boolean bookSeat(int seatId) {

    try {

        String sql =
        "UPDATE seat SET seatStatus='Booked' WHERE seatId=?";

        PreparedStatement ps =
        con.prepareStatement(sql);

        ps.setInt(1, seatId);

        return ps.executeUpdate() > 0;

    } catch(Exception e) {
        e.printStackTrace();
    }

    return false;
}

        public boolean isSeatAvailable(int seatId) {

    try {

        String sql =
        "SELECT seatStatus FROM seat WHERE seatId=?";

        PreparedStatement ps =
        con.prepareStatement(sql);

        ps.setInt(1, seatId);

        ResultSet rs = ps.executeQuery();

        if(rs.next()) {

            return rs.getString("seatStatus")
                     .equalsIgnoreCase("Available");
        }

    } catch(Exception e) {
        e.printStackTrace();
    }

    return false;
}

        public boolean deleteSeatsByScreen(int screenId) {

    try {

        String sql =
        "DELETE FROM seat WHERE screenId=?";

        PreparedStatement ps =
        con.prepareStatement(sql);

        ps.setInt(1, screenId);

        return ps.executeUpdate() > 0;

    } catch(Exception e) {
        e.printStackTrace();
    }

    return false;
}

        public int getTotalSeatsByScreen(int screenId) {

    try {

        String sql =
        "SELECT COUNT(*) total FROM seat WHERE screenId=?";

        PreparedStatement ps =
        con.prepareStatement(sql);

        ps.setInt(1, screenId);

        ResultSet rs = ps.executeQuery();

        if(rs.next()) {
            return rs.getInt("total");
        }

    } catch(Exception e) {
        e.printStackTrace();
    }

    return 0;
}

        public int getBookedSeats(int screenId) {

    try {

        String sql =
        "SELECT COUNT(*) total FROM seat WHERE screenId=? AND seatStatus='Booked'";

        PreparedStatement ps =
        con.prepareStatement(sql);

        ps.setInt(1, screenId);

        ResultSet rs = ps.executeQuery();

        if(rs.next()) {
            return rs.getInt("total");
        }

    } catch(Exception e) {
        e.printStackTrace();
    }

    return 0;
}

        public int getAvailableSeats(int screenId) {

    try {

        String sql =
        "SELECT COUNT(*) total FROM seat WHERE screenId=? AND seatStatus='Available'";

        PreparedStatement ps =
        con.prepareStatement(sql);

        ps.setInt(1, screenId);

        ResultSet rs = ps.executeQuery();

        if(rs.next()) {
            return rs.getInt("total");
        }

    } catch(Exception e) {
        e.printStackTrace();
    }

    return 0;
}

        public boolean releaseSeat(int seatId) {

    try {

        String sql =
        "UPDATE seat SET seatStatus='Available' WHERE seatId=?";

        PreparedStatement ps =
        con.prepareStatement(sql);

        ps.setInt(1, seatId);

        return ps.executeUpdate() > 0;

    } catch(Exception e) {
        e.printStackTrace();
    }

    return false;
}


    /*public static void main(String[] args) {
        SeatModel seat = new SeatModel();

seat.setScreenId(1);
seat.setSeatNumber("D1");
seat.setSeatType("Gold");
seat.setSeatStatus("Available");

SeatDAO dao = new SeatDAO();

if(dao.insertSeat(seat))
    System.out.println("Seat Added");
else
    System.out.println("Failed");
    }*/
}
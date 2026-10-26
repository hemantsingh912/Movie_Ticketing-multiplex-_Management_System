package Dao;

import Db.DBConnection;
import Model.SeatPriceModel;
import java.sql.*;

public class SeatPriceDAO {

    Connection con;

    public SeatPriceDAO() {
        con = DBConnection.getConnection();
    }

    // Insert Seat Price
    public boolean insertSeatPrice(SeatPriceModel sp) {

        try {

            String sql =
            "INSERT INTO seat_price(showId,seatType,price) VALUES(?,?,?)";

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, sp.getShowId());
            ps.setString(2, sp.getSeatType());
            ps.setDouble(3, sp.getPrice());

            return ps.executeUpdate() > 0;

        } catch(Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    // Update Seat Price
    public boolean updateSeatPrice(SeatPriceModel sp) {

        try {

            String sql =
            "UPDATE seat_price SET showId=?, seatType=?, price=? WHERE seatPriceId=?";

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, sp.getShowId());
            ps.setString(2, sp.getSeatType());
            ps.setDouble(3, sp.getPrice());
            ps.setInt(4, sp.getSeatPriceId());

            return ps.executeUpdate() > 0;

        } catch(Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    // Delete Seat Price
    public boolean deleteSeatPrice(int seatPriceId) {

        try {

            String sql =
            "DELETE FROM seat_price WHERE seatPriceId=?";

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, seatPriceId);

            return ps.executeUpdate() > 0;

        } catch(Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    // Search Seat Price
    public ResultSet getSeatPriceById(int seatPriceId) {

        try {

            String sql =
            "SELECT * FROM seat_price WHERE seatPriceId=?";

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, seatPriceId);

            return ps.executeQuery();

        } catch(Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    // Show All Seat Prices
    public ResultSet getAllSeatPrices() {

        try {

            String sql = "SELECT * FROM seat_price";

            PreparedStatement ps = con.prepareStatement(sql);

            return ps.executeQuery();

        } catch(Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public boolean deleteByShowId(int showId) {

    try {

        String sql =
        "DELETE FROM seat_price WHERE showId=?";

        PreparedStatement ps =
        con.prepareStatement(sql);

        ps.setInt(1, showId);

        ps.executeUpdate();

        return true;

    } catch(Exception e) {

        e.printStackTrace();
    }

    return false;
}

    public int getLastShowId() {

    try {

        String sql =
        "SELECT MAX(showId) FROM shows";

        PreparedStatement ps =
        con.prepareStatement(sql);

        ResultSet rs =
        ps.executeQuery();

        if(rs.next()) {

            return rs.getInt(1);
        }

    } catch(Exception e) {

        e.printStackTrace();
    }

    return 0;
}

public boolean deleteShow(int showId) {

    try {

        String sql1 =
        "DELETE FROM seat_price WHERE showId=?";

        PreparedStatement ps1 =
        con.prepareStatement(sql1);

        ps1.setInt(1, showId);

        ps1.executeUpdate();

        String sql2 =
        "DELETE FROM shows WHERE showId=?";

        PreparedStatement ps2 =
        con.prepareStatement(sql2);

        ps2.setInt(1, showId);

        return ps2.executeUpdate() > 0;

    } catch(Exception e) {

        e.printStackTrace();
    }

    return false;
}

    /*public static void main(String[] args) {
        SeatPriceModel sp = new SeatPriceModel();

sp.setShowId(1);
sp.setSeatType("Gold");
sp.setPrice(450);

SeatPriceDAO dao = new SeatPriceDAO();

if(dao.insertSeatPrice(sp))
    System.out.println("Seat Price Added");
else
    System.out.println("Failed");
    }*/
}
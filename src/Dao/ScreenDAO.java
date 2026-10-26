package Dao;

import Db.DBConnection;
import Model.ScreenModel;
import java.sql.*;
import java.util.ArrayList;

public class ScreenDAO {

    Connection con;

    public ScreenDAO() {
        con = DBConnection.getConnection();
    }

    // Insert Screen
    public boolean insertScreen(ScreenModel s) {

        try {

            String sql =
            "INSERT INTO screen(screenName,regularSeatCount,premiumSeatCount,goldSeatCount,totalSeat,screenType,status) VALUES(?,?,?,?,?,?,?)";

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, s.getScreenName());
            ps.setInt(2, s.getRegularSeatCount());
            ps.setInt(3, s.getPremiumSeatCount());
            ps.setInt(4, s.getGoldSeatCount());
            ps.setInt(5, s.getTotalSeat());
            ps.setString(6, s.getScreenType());
            ps.setString(7, s.getStatus());

            return ps.executeUpdate() > 0;

        } catch(Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    // Update Screen
    public boolean updateScreen(ScreenModel s) {

        try {

            String sql =
            "UPDATE screen SET screenName=?,regularSeatCount=?,premiumSeatCount=?,goldSeatCount=?,totalSeat=?,screenType=?,status=? WHERE screenId=?";

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, s.getScreenName());
            ps.setInt(2, s.getRegularSeatCount());
            ps.setInt(3, s.getPremiumSeatCount());
            ps.setInt(4, s.getGoldSeatCount());
            ps.setInt(5, s.getTotalSeat());
            ps.setString(6, s.getScreenType());
            ps.setString(7, s.getStatus());
            ps.setInt(8, s.getScreenId());

            return ps.executeUpdate() > 0;

        } catch(Exception e) {
            e.printStackTrace();
        }

        return false;
    }

  public boolean deleteScreen(int screenId) {

    try {

        String seatSql =
                "DELETE FROM seat WHERE screenId=?";

        PreparedStatement ps1 =
                con.prepareStatement(seatSql);

        ps1.setInt(1, screenId);
        ps1.executeUpdate();

        String screenSql =
                "DELETE FROM screen WHERE screenId=?";

        PreparedStatement ps2 =
                con.prepareStatement(screenSql);

        ps2.setInt(1, screenId);

        return ps2.executeUpdate() > 0;

    } catch(Exception e) {

        e.printStackTrace();
    }

    return false;
}

    // Search Screen
    public ResultSet getScreenById(int screenId) {

        try {

            String sql = "SELECT * FROM screen WHERE screenId=?";

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, screenId);

            return ps.executeQuery();

        } catch(Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    // Show All Screens
    public ResultSet getAllScreens() {

        try {

            String sql = "SELECT * FROM screen";

            PreparedStatement ps = con.prepareStatement(sql);

            return ps.executeQuery();

        } catch(Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public int getLastScreenId() {

    try {

        String sql =
        "SELECT MAX(screenId) FROM screen";

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

public ScreenModel searchScreen(int screenId) {

    ScreenModel s = null;

    try {

        String sql =
        "SELECT * FROM screen WHERE screenId=?";

        PreparedStatement ps =
        con.prepareStatement(sql);

        ps.setInt(1, screenId);

        ResultSet rs =
        ps.executeQuery();

        if(rs.next()) {

            s = new ScreenModel();

            s.setScreenId(
                    rs.getInt("screenId"));

            s.setScreenName(
                    rs.getString("screenName"));

            s.setRegularSeatCount(
                    rs.getInt("regularSeatCount"));

            s.setPremiumSeatCount(
                    rs.getInt("premiumSeatCount"));

            s.setGoldSeatCount(
                    rs.getInt("goldSeatCount"));

            s.setTotalSeat(
                    rs.getInt("totalSeat"));

            s.setScreenType(
                    rs.getString("screenType"));

            s.setStatus(
                    rs.getString("status"));
        }

    } catch(Exception e) {

        e.printStackTrace();
    }

    return s;
}

public ArrayList<ScreenModel> getScreenList() {

    ArrayList<ScreenModel> list =
            new ArrayList<>();

    try {

        String sql =
        "SELECT * FROM screen";

        PreparedStatement ps =
        con.prepareStatement(sql);

        ResultSet rs =
        ps.executeQuery();

        while(rs.next()) {

            ScreenModel s =
                    new ScreenModel();

            s.setScreenId(
                    rs.getInt("screenId"));

            s.setScreenName(
                    rs.getString("screenName"));

            s.setRegularSeatCount(
                    rs.getInt("regularSeatCount"));

            s.setPremiumSeatCount(
                    rs.getInt("premiumSeatCount"));

            s.setGoldSeatCount(
                    rs.getInt("goldSeatCount"));

            s.setTotalSeat(
                    rs.getInt("totalSeat"));

            s.setScreenType(
                    rs.getString("screenType"));

            s.setStatus(
                    rs.getString("status"));

            list.add(s);
        }

    } catch(Exception e) {

        e.printStackTrace();
    }

    return list;
}


    //Return total screens for show a staff home panel
    public int getScreenCount() {

    int count = 0;

    try {
        String sql = "SELECT COUNT(*) FROM screen";
        PreparedStatement ps = con.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            count = rs.getInt(1);
        }

    } catch (Exception e) {
        e.printStackTrace();
    }

    return count;
}

    /*public static void main(String[] args) {

    ScreenDAO dao = new ScreenDAO();

    ScreenModel s = new ScreenModel();

    s.setScreenName("Screen A");

    s.setRegularSeatCount(50);
    s.setPremiumSeatCount(30);
    s.setGoldSeatCount(20);

    s.setTotalSeat(100);

    s.setScreenType("IMAX");
    s.setStatus("Active");

    if (dao.insertScreen(s))
        System.out.println("Screen Added Successfully");
    else
        System.out.println("Screen Failed");

    System.out.println("\nAll Screens:");

    try {
        java.sql.ResultSet rs = dao.getAllScreens();

        while (rs.next()) {
            System.out.println(
                rs.getInt("screenId") + " | " +
                rs.getString("screenName") + " | " +
                rs.getInt("regularSeatCount") + " | " +
                rs.getInt("premiumSeatCount") + " | " +
                rs.getInt("goldSeatCount") + " | " +
                rs.getInt("totalSeat") + " | " +
                rs.getString("screenType") + " | " +
                rs.getString("status")
            );
        }

    } catch (Exception e) {
        e.printStackTrace();
    }
}*/
}
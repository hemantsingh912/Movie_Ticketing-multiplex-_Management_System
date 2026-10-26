package Dao;

import Db.DBConnection;
import Model.ShowModel;
import java.sql.*;

public class ShowDAO {

    Connection con;

    public ShowDAO() {
        con = DBConnection.getConnection();
    }

    // ADD SHOW AND RETURN SHOW ID
    public int insertShow(ShowModel s) {

        try {

            String sql =
            "INSERT INTO shows(movieId,screenId,showDate,showTime,showStatus) VALUES(?,?,?,?,?)";

            PreparedStatement ps =
            con.prepareStatement(
                    sql,
                    Statement.RETURN_GENERATED_KEYS);

            ps.setInt(1, s.getMovieId());
            ps.setInt(2, s.getScreenId());
            ps.setDate(3, s.getShowDate());
            ps.setTime(4, s.getShowTime());
            ps.setString(5, s.getShowStatus());

            ps.executeUpdate();

            ResultSet rs =
            ps.getGeneratedKeys();

            if(rs.next()) {

                return rs.getInt(1);
            }

        } catch(Exception e) {
            e.printStackTrace();
        }

        return -1;
    }

    // UPDATE SHOW
    public boolean updateShow(ShowModel s) {

        try {

            String sql =
            "UPDATE shows SET movieId=?,screenId=?,showDate=?,showTime=?,showStatus=? WHERE showId=?";

            PreparedStatement ps =
            con.prepareStatement(sql);

            ps.setInt(1, s.getMovieId());
            ps.setInt(2, s.getScreenId());
            ps.setDate(3, s.getShowDate());
            ps.setTime(4, s.getShowTime());
            ps.setString(5, s.getShowStatus());
            ps.setInt(6, s.getShowId());

            return ps.executeUpdate() > 0;

        } catch(Exception e) {
            e.printStackTrace();
        }

        return false;
    }

   

    // SEARCH SHOW
    public ResultSet getShowById(int showId) {

        try {

            String sql =
            "SELECT * FROM shows WHERE showId=?";

            PreparedStatement ps =
            con.prepareStatement(sql);

            ps.setInt(1, showId);

            return ps.executeQuery();

        } catch(Exception e) {
            e.printStackTrace();
        }

        return null;
    }

   
    

    // SHOWS BY MOVIE
    public ResultSet getShowsByMovie(int movieId) {

        try {

            String sql =
            "SELECT * FROM shows WHERE movieId=?";

            PreparedStatement ps =
            con.prepareStatement(sql);

            ps.setInt(1, movieId);

            return ps.executeQuery();

        } catch(Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    // ALL SHOWS
    public ResultSet getAllShows() {

        try {

            String sql =
            "SELECT * FROM shows";

            PreparedStatement ps =
            con.prepareStatement(sql);

            return ps.executeQuery();

        } catch(Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    // TABLE VIEW DATA
   public ResultSet getShowDetails() {

    try {

        String sql =
        "SELECT s.showId, " +
        "m.movieName, " +
        "sc.screenName, " +
        "s.showDate, " +
        "s.showTime, " +

        "MAX(CASE WHEN sp.seatType='Regular' THEN sp.price END) AS RegularPrice, " +
        "MAX(CASE WHEN sp.seatType='Premium' THEN sp.price END) AS PremiumPrice, " +
        "MAX(CASE WHEN sp.seatType='Gold' THEN sp.price END) AS GoldPrice, " +

        "s.showStatus " +

        "FROM shows s " +
        "JOIN movie m ON s.movieId = m.movieId " +
        "JOIN screen sc ON s.screenId = sc.screenId " +
        "LEFT JOIN seat_price sp ON s.showId = sp.showId " +

        "GROUP BY s.showId, m.movieName, sc.screenName, " +
        "s.showDate, s.showTime, s.showStatus " +

        "ORDER BY s.showDate, s.showTime";

        PreparedStatement ps =
        con.prepareStatement(sql);

        return ps.executeQuery();

    } catch(Exception e) {

        e.printStackTrace();
    }

    return null;
}
    

    // CHECK SCREEN AVAILABILITY
    public boolean isShowTimeAvailable(
            int screenId,
            Date showDate,
            Time newTime,
            int durationMinutes) {

        try {

            String sql =
            "SELECT s.showTime,m.durationMinutes " +
            "FROM shows s " +
            "JOIN movie m ON s.movieId=m.movieId " +
            "WHERE s.screenId=? AND s.showDate=?";

            PreparedStatement ps =
            con.prepareStatement(sql);

            ps.setInt(1, screenId);
            ps.setDate(2, showDate);

            ResultSet rs =
            ps.executeQuery();

            while(rs.next()) {

                Time oldTime =
                rs.getTime("showTime");

                int oldDuration =
                rs.getInt("durationMinutes");

                long oldStart =
                oldTime.getTime();

                long oldEnd =
                oldStart +
                ((oldDuration + 30) * 60 * 1000);

                long newStart =
                newTime.getTime();

                if(newStart < oldEnd) {

                    return false;
                }
            }

        } catch(Exception e) {
            e.printStackTrace();
        }

        return true;
    }

    // TODAY SHOW COUNT
    public int getTodayShowCount() {

        int count = 0;

        try {

            String sql =
            "SELECT COUNT(*) FROM shows WHERE showDate = CURDATE()";

            PreparedStatement ps =
            con.prepareStatement(sql);

            ResultSet rs =
            ps.executeQuery();

            if(rs.next()) {

                count = rs.getInt(1);
            }

        } catch(Exception e) {
            e.printStackTrace();
        }

        return count;
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

        // First delete seat prices
        String sql1 =
        "DELETE FROM seat_price WHERE showId=?";

        PreparedStatement ps1 =
        con.prepareStatement(sql1);

        ps1.setInt(1, showId);

        ps1.executeUpdate();

        // Then delete show
        String sql2 =
        "DELETE FROM shows WHERE showId=?";

        PreparedStatement ps2 =
        con.prepareStatement(sql2);

        ps2.setInt(1, showId);

        return ps2.executeUpdate() > 0;

    }
    catch(Exception e) {

        e.printStackTrace();

        System.out.println(e.getMessage());
    }

    return false;
}

    public ResultSet getShowDetailsByMovie(int movieId){

    try{

        String sql =
        "SELECT s.showId, sc.screenName, s.showTime " +
        "FROM shows s " +
        "JOIN screen sc ON s.screenId=sc.screenId " +
        "WHERE s.movieId=? AND s.showStatus='Active'";

        PreparedStatement ps =
        con.prepareStatement(sql);

        ps.setInt(1, movieId);

        return ps.executeQuery();

    }catch(Exception e){
        e.printStackTrace();
    }

    return null;
}

    public ResultSet getShowsByMovieWithScreen(int movieId) {

    try {

        String sql =
        "SELECT s.showId, s.showTime, " +
        "sc.screenName " +
        "FROM shows s " +
        "JOIN screen sc ON s.screenId=sc.screenId " +
        "WHERE s.movieId=?";

        PreparedStatement ps =
        con.prepareStatement(sql);

        ps.setInt(1, movieId);

        return ps.executeQuery();

    } catch(Exception e) {
        e.printStackTrace();
    }

    return null;
}

}
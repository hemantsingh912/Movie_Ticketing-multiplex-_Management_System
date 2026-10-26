package Dao;

import Db.DBConnection;
import Model.StaffModel;
import java.sql.*;

public class StaffDAO {

    Connection con;

    public StaffDAO() {
        con = DBConnection.getConnection();
    }

    // =========================
    // 1. INSERT STAFF
    // =========================
    public boolean insertStaff(StaffModel s) {

        try {
            String sql = "INSERT INTO staff(staffName,email,username,password,phoneNumber,joiningDate,status) VALUES(?,?,?,?,?,?,?)";

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, s.getStaffName());
            ps.setString(2, s.getEmail());
            ps.setString(3, s.getUsername());
            ps.setString(4, s.getPassword());
            ps.setString(5, s.getPhoneNumber());
            ps.setDate(6, s.getJoiningDate());
            ps.setString(7, s.getStatus());

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    // =========================
    // 2. UPDATE STAFF
    // =========================
    public boolean updateStaff(StaffModel s) {

        try {
            String sql = "UPDATE staff SET staffName=?,email=?,username=?,password=?,phoneNumber=?,joiningDate=?,status=? WHERE staffId=?";

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, s.getStaffName());
            ps.setString(2, s.getEmail());
            ps.setString(3, s.getUsername());
            ps.setString(4, s.getPassword());
            ps.setString(5, s.getPhoneNumber());
            ps.setDate(6, s.getJoiningDate());
            ps.setString(7, s.getStatus());
            ps.setInt(8, s.getStaffId());

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    // =========================
    // 3. DELETE STAFF
    // =========================
    public boolean deleteStaff(int staffId) {

        try {
            String sql = "DELETE FROM staff WHERE staffId=?";

            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, staffId);

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    // =========================
    // 4. GET STAFF BY ID
    // =========================
    public ResultSet getStaffById(int staffId) {

        try {
            String sql = "SELECT * FROM staff WHERE staffId=?";

            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, staffId);

            return ps.executeQuery();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    // =========================
    // 5. GET ALL STAFF
    // =========================
    public ResultSet getAllStaff() {

        try {
            String sql = "SELECT * FROM staff";

            PreparedStatement ps = con.prepareStatement(sql);

            return ps.executeQuery();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    // =========================
    // 6. LOGIN CHECK (BONUS)
    // =========================
    public boolean loginStaff(String username, String password) {

        try {
            String sql = "SELECT * FROM staff WHERE username=? AND password=? AND status='Active'";

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, username);
            ps.setString(2, password);

            ResultSet rs = ps.executeQuery();

            return rs.next();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    
  /*  public static void main(String[] args) {

    StaffDAO dao = new StaffDAO();

    StaffModel s = new StaffModel();

    long id = System.currentTimeMillis(); // unique generator

    s.setStaffName("Test Staff");
    s.setEmail("staff" + id + "@gmail.com");
    s.setUsername("staff" + id);
    s.setPassword("12345");
    s.setPhoneNumber("9876500000");
    s.setJoiningDate(java.sql.Date.valueOf("2026-06-20"));
    s.setStatus("Active");

    if (dao.insertStaff(s))
        System.out.println("Staff Added Successfully");
    else
        System.out.println("Staff Failed");

    System.out.println("\nAll Staff Data:");

    try {
        java.sql.ResultSet rs = dao.getAllStaff();

        while (rs.next()) {
            System.out.println(
                rs.getInt("staffId") + " | " +
                rs.getString("staffName") + " | " +
                rs.getString("email") + " | " +
                rs.getString("username") + " | " +
                rs.getString("status")
            );
        }

    } catch (Exception e) {
        e.printStackTrace();
    }
}*/
}
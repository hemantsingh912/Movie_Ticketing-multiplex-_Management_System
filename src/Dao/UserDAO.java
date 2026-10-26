package Dao;

import Db.DBConnection;
import Model.UserModel;
import java.sql.*;

public class UserDAO {

    Connection con;

    public UserDAO() {
        con = DBConnection.getConnection();
    }

    // ==========================
    // ADD USER
    // ==========================
    public boolean addUser(UserModel user) {

        try {

            String sql = "INSERT INTO users(fullName,email,username,password,role,phone) VALUES(?,?,?,?,?,?)";

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, user.getFullName());
            ps.setString(2, user.getEmail());
            ps.setString(3, user.getUsername());
            ps.setString(4, user.getPassword());
            ps.setString(5, user.getRole());
            ps.setString(6, user.getPhone());

            int result = ps.executeUpdate();

            return result > 0;

        } catch (Exception e) {

            System.out.println(e);
            return false;
        }
    }

    // ==========================
    // SHOW ALL USERS
    // ==========================
    public void getAllUsers() {

        try {

            String sql = "SELECT * FROM users";

            PreparedStatement ps = con.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                System.out.println(
                        rs.getInt("userId") + "  "
                        + rs.getString("fullName") + "  "
                        + rs.getString("email") + "  "
                        + rs.getString("username") + "  "
                        + rs.getString("role") + "  "
                        + rs.getString("phone"));
            }

        } catch (Exception e) {

            System.out.println(e);
        }
    }

    // ==========================
    // UPDATE USER
    // ==========================
    public boolean updateUser(UserModel user) {

        try {

            String sql = "UPDATE users SET fullName=?,email=?,username=?,password=?,role=?,phone=? WHERE userId=?";

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, user.getFullName());
            ps.setString(2, user.getEmail());
            ps.setString(3, user.getUsername());
            ps.setString(4, user.getPassword());
            ps.setString(5, user.getRole());
            ps.setString(6, user.getPhone());
            ps.setInt(7, user.getUserId());

            int result = ps.executeUpdate();

            return result > 0;

        } catch (Exception e) {

            System.out.println(e);
            return false;
        }
    }

    // ==========================
    // DELETE USER
    // ==========================
    public boolean deleteUser(int userId) {

        try {

            String sql = "DELETE FROM users WHERE userId=?";

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, userId);

            int result = ps.executeUpdate();

            return result > 0;

        } catch (Exception e) {

            System.out.println(e);
            return false;
        }
    }

    // ==========================
    // LOGIN USER
    // ==========================
    public UserModel loginUser(String username, String password) {

        try {

            String sql =
                    "SELECT * FROM users WHERE username=? AND password=?";

            PreparedStatement ps =
                    con.prepareStatement(sql);

            ps.setString(1, username);
            ps.setString(2, password);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                UserModel user = new UserModel();

                user.setUserId(rs.getInt("userId"));
                user.setFullName(rs.getString("fullName"));
                user.setEmail(rs.getString("email"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setRole(rs.getString("role"));
                user.setPhone(rs.getString("phone"));

                return user;
            }

        } catch (Exception e) {

            System.out.println(e);
        }

        return null;
    }

        //Admin ma Customer Data Show
    public ResultSet getAllCustomers() {

    try {

        String sql =
        "SELECT userId,fullName,email,role,phone FROM users WHERE role='CUSTOMER'";

        PreparedStatement ps =
                con.prepareStatement(sql);

        return ps.executeQuery();

    } catch(Exception e) {

        System.out.println(e);
    }

    return null;
}

    // Search a Customer ID
    public ResultSet searchCustomer(int id) {

    try {

        String sql =
        "SELECT userId,fullName,email,role,phone FROM users WHERE userId=?";

        PreparedStatement ps =
                con.prepareStatement(sql);

        ps.setInt(1, id);

        return ps.executeQuery();

    } catch(Exception e) {

        System.out.println(e);
    }

    return null;
}

    

}
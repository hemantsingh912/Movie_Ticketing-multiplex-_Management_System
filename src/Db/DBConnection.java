package Db;

import java.sql.*;

public class DBConnection {

    private static Connection con;

    public static Connection getConnection() {

        try {

            if(con == null || con.isClosed()) {

                Class.forName("com.mysql.cj.jdbc.Driver");

                con = DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/movieproject",
                        "root",
                        "hemant0912");
            }

        } catch (Exception e) {
            System.out.println(e);
        }

        return con;
    }

    
   /*  public static void main(String[] args) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Driver Loaded Successfully");
        } catch(Exception e) {
            e.printStackTrace();
        }
}*/
    

}
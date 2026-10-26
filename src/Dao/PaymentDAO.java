package Dao;

import Db.DBConnection;
import Model.PaymentModel;
import java.sql.*;

public class PaymentDAO {

    Connection con;

    public PaymentDAO() {
        con = DBConnection.getConnection();
    }

    // Insert Payment
    public boolean insertPayment(PaymentModel p) {

        try {

            String sql =
            "INSERT INTO payment(bookingId,amount,paymentDate,paymentStatus,paymentMethod,transactionId) VALUES(?,?,?,?,?,?)";

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, p.getBookingId());
            ps.setDouble(2, p.getAmount());
            ps.setTimestamp(3, p.getPaymentDate());
            ps.setString(4, p.getPaymentStatus());
            ps.setString(5, p.getPaymentMethod());
            ps.setString(6, p.getTransactionId());

            return ps.executeUpdate() > 0;

        } catch(Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    // Update Payment
    public boolean updatePayment(PaymentModel p) {

        try {

            String sql =
            "UPDATE payment SET bookingId=?, amount=?, paymentDate=?, paymentStatus=?, paymentMethod=?, transactionId=? WHERE paymentId=?";

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, p.getBookingId());
            ps.setDouble(2, p.getAmount());
            ps.setTimestamp(3, p.getPaymentDate());
            ps.setString(4, p.getPaymentStatus());
            ps.setString(5, p.getPaymentMethod());
            ps.setString(6, p.getTransactionId());
            ps.setInt(7, p.getPaymentId());

            return ps.executeUpdate() > 0;

        } catch(Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    // Delete Payment
    public boolean deletePayment(int paymentId) {

        try {

            String sql =
            "DELETE FROM payment WHERE paymentId=?";

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, paymentId);

            return ps.executeUpdate() > 0;

        } catch(Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    // Search Payment
    public ResultSet getPaymentById(int paymentId) {

        try {

            String sql =
            "SELECT * FROM payment WHERE paymentId=?";

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, paymentId);

            return ps.executeQuery();

        } catch(Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    // Show All Payments
    public ResultSet getAllPayments() {

        try {

            String sql = "SELECT * FROM payment";

            PreparedStatement ps = con.prepareStatement(sql);

            return ps.executeQuery();

        } catch(Exception e) {
            e.printStackTrace();
        }

        return null;
    }
   /*  public static void main(String[] args) {
        PaymentModel p = new PaymentModel();

p.setBookingId(10);
p.setAmount(300);

p.setPaymentDate(
        new Timestamp(System.currentTimeMillis()));

p.setPaymentStatus("Success");

p.setPaymentMethod("UPI");

p.setTransactionId("TXN10010");

PaymentDAO dao = new PaymentDAO();

if(dao.insertPayment(p))
    System.out.println("Payment Added");
else
    System.out.println("Payment Failed");
    }*/
}
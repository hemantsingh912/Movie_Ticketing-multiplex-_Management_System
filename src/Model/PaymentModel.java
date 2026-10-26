package Model;

import java.sql.Timestamp;

public class PaymentModel {
        
        private int paymentId;
        private int bookingId;
        private double amount;
        private Timestamp paymentDate;
        private String paymentStatus;
        private String paymentMethod;
        private String transactionId;

    public PaymentModel() {}

        

public PaymentModel(int paymentId, int bookingId, double amount, Timestamp paymentDate, String paymentStatus,
        String paymentMethod, String transactionId) {
    this.paymentId = paymentId;
    this.bookingId = bookingId;
    this.amount = amount;
    this.paymentDate = paymentDate;
    this.paymentStatus = paymentStatus;
    this.paymentMethod = paymentMethod;
    this.transactionId = transactionId;
}

public int getPaymentId() {
    return paymentId;
}

public void setPaymentId(int paymentId) {
    this.paymentId = paymentId;
}

public int getBookingId() {
    return bookingId;
}

public void setBookingId(int bookingId) {
    this.bookingId = bookingId;
}

public double getAmount() {
    return amount;
}

public void setAmount(double amount) {
    this.amount = amount;
}

public Timestamp getPaymentDate() {
    return paymentDate;
}

public void setPaymentDate(Timestamp paymentDate) {
    this.paymentDate = paymentDate;
}

public String getPaymentStatus() {
    return paymentStatus;
}

public void setPaymentStatus(String paymentStatus) {
    this.paymentStatus = paymentStatus;
}

public String getPaymentMethod() {
    return paymentMethod;
}

public void setPaymentMethod(String paymentMethod) {
    this.paymentMethod = paymentMethod;
}

public String getTransactionId() {
    return transactionId;
}

public void setTransactionId(String transactionId) {
    this.transactionId = transactionId;
}
}

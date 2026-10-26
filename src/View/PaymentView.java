package View;

import Dao.PaymentDAO;
import java.awt.*;
import java.sql.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class PaymentView extends JPanel {

    private JTextField txtPaymentId;
    private JButton searchBtn;
    private JButton refreshBtn;

    private JTable table;
    private DefaultTableModel model;

    private PaymentDAO dao;

    public PaymentView() {

        dao = new PaymentDAO();

        setLayout(new BorderLayout());
        setBackground(new Color(245, 247, 250));

        // ================= HEADER =================
        JPanel header = new JPanel();
        header.setLayout(new FlowLayout(FlowLayout.LEFT, 15, 12));
        header.setBackground(new Color(33, 37, 41));

        JLabel title = new JLabel("PAYMENT LIST");
        title.setForeground(Color.WHITE);
        title.setFont(new Font("Segoe UI", Font.BOLD, 22));

        JLabel lblId = new JLabel("Search Payment ID:");
        lblId.setForeground(Color.WHITE);
        lblId.setFont(new Font("Segoe UI", Font.PLAIN, 14));

        txtPaymentId = new JTextField(10);
        txtPaymentId.setFont(new Font("Segoe UI", Font.PLAIN, 14));

        searchBtn = new JButton("SEARCH");
        refreshBtn = new JButton("REFRESH");

        styleButton(searchBtn, new Color(163, 128, 196));
        styleButton(refreshBtn, new Color(243, 224, 178));

        header.add(title);
        header.add(Box.createHorizontalStrut(40));
        header.add(lblId);
        header.add(txtPaymentId);
        header.add(searchBtn);
        header.add(refreshBtn);

        add(header, BorderLayout.NORTH);

        // ================= TABLE =================
        model = new DefaultTableModel();

        model.addColumn("Payment ID");
        model.addColumn("Booking ID");
        model.addColumn("Amount");
        model.addColumn("Payment Date");
        model.addColumn("Status");
        model.addColumn("Method");
        model.addColumn("Transaction ID");

        table = new JTable(model) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        table.setRowHeight(30);
        table.setFont(new Font("Segoe UI", Font.PLAIN, 14));

        table.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 14));
        table.getTableHeader().setBackground(new Color(108, 117, 125));
        table.getTableHeader().setForeground(Color.WHITE);

        table.setGridColor(new Color(220, 220, 220));
        table.setSelectionBackground(new Color(255, 200, 120));
        table.setSelectionForeground(Color.BLACK);

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        add(scrollPane, BorderLayout.CENTER);

        // ================= ACTIONS =================
        loadPayments();

        searchBtn.addActionListener(e -> searchPayment());
        refreshBtn.addActionListener(e -> loadPayments());
    }

    // ================= LOAD ALL =================
    public void loadPayments() {

        try {

            ResultSet rs = dao.getAllPayments();

            model.setRowCount(0);

            while (rs.next()) {

                model.addRow(new Object[]{
                        rs.getInt("paymentId"),
                        rs.getInt("bookingId"),
                        rs.getDouble("amount"),
                        rs.getTimestamp("paymentDate"),
                        rs.getString("paymentStatus"),
                        rs.getString("paymentMethod"),
                        rs.getString("transactionId")
                });
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // ================= SEARCH =================
    public void searchPayment() {

        try {

            int id = Integer.parseInt(txtPaymentId.getText());

            ResultSet rs = dao.getPaymentById(id);

            model.setRowCount(0);

            if (rs.next()) {

                model.addRow(new Object[]{
                        rs.getInt("paymentId"),
                        rs.getInt("bookingId"),
                        rs.getDouble("amount"),
                        rs.getTimestamp("paymentDate"),
                        rs.getString("paymentStatus"),
                        rs.getString("paymentMethod"),
                        rs.getString("transactionId")
                });

            } else {
                JOptionPane.showMessageDialog(this, "Payment Not Found!");
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Invalid Payment ID!");
        }
    }

    // ================= BUTTON STYLE =================
    private void styleButton(JButton btn, Color color) {

        btn.setBackground(color);
        btn.setForeground(Color.WHITE);
        btn.setFont(new Font("Segoe UI", Font.BOLD, 13));
        btn.setFocusPainted(false);
        btn.setBorderPainted(false);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));

        btn.setPreferredSize(new Dimension(110, 32));

        btn.addMouseListener(new java.awt.event.MouseAdapter() {

            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn.setBackground(color.darker());
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn.setBackground(color);
            }
        });
    }
}
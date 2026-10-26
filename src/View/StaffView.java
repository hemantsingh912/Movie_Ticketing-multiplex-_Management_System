package View;

import Dao.StaffDAO;
import java.awt.*;
import java.sql.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class StaffView extends JPanel {

    private JTextField txtSearch;

    private JTextField txtId, txtName, txtEmail, txtUser, txtPhone, txtPassword;
    private JComboBox<String> statusBox;

    private JTable table;
    private DefaultTableModel model;

    private StaffDAO dao;

    public StaffView() {

        dao = new StaffDAO();

        setLayout(null);
        setBackground(new Color(18, 22, 28)); // DARK BACKGROUND

        // ================= TITLE =================
        JLabel title = new JLabel("STAFF MANAGEMENT SYSTEM");
        title.setFont(new Font("Segoe UI", Font.BOLD, 28));
        title.setForeground(Color.WHITE);
        title.setBounds(30, 10, 600, 40);
        add(title);

        // ================= SEARCH BAR =================
        JPanel searchPanel = new JPanel();
        searchPanel.setBounds(30, 60, 1250, 50);
        searchPanel.setLayout(null);
        searchPanel.setBackground(new Color(30, 34, 40));

        txtSearch = new JTextField();
        txtSearch.setBounds(20, 10, 400, 30);
        txtSearch.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        searchPanel.add(txtSearch);

        JButton searchBtn = new JButton("SEARCH");
        searchBtn.setBounds(440, 10, 120, 30);
        styleButton(searchBtn);
        searchPanel.add(searchBtn);

        JButton refreshBtn = new JButton("REFRESH");
        refreshBtn.setBounds(580, 10, 120, 30);
        styleButton(refreshBtn);
        searchPanel.add(refreshBtn);

        refreshBtn.addActionListener(e -> {
            loadStaff();
            clearForm();
        });

        add(searchPanel);

        // ================= FORM PANEL =================
        JPanel form = new JPanel();
        form.setBounds(30, 120, 1250, 200);
        form.setLayout(null);
        form.setBackground(new Color(28, 32, 38));

        txtId = new JTextField();       // auto id (not used manually)
        txtName = new JTextField();
        txtEmail = new JTextField();
        txtUser = new JTextField();
        txtPhone = new JTextField();
        txtPassword = new JTextField();
        statusBox = new JComboBox<>(new String[]{"Active", "Inactive", "On Leave"});

        addField(form, "Name", txtName, 20, 30);
        addField(form, "Email", txtEmail, 250, 30);
        addField(form, "Username", txtUser, 480, 30);

        addField(form, "Phone", txtPhone, 20, 100);
        addField(form, "Password", txtPassword, 250, 100);

        JLabel st = new JLabel("Status");
        st.setBounds(480, 100, 100, 25);
        st.setForeground(Color.WHITE);
        form.add(st);

        statusBox.setBounds(480, 125, 180, 30);
        form.add(statusBox);

        add(form);

        // ================= BUTTON PANEL =================
        JPanel btnPanel = new JPanel();
        btnPanel.setBounds(30, 330, 1250, 60);
        btnPanel.setLayout(null);
        btnPanel.setBackground(new Color(18, 22, 28));

        JButton addBtn = new JButton("ADD");
        JButton updateBtn = new JButton("UPDATE");
        JButton deleteBtn = new JButton("DELETE");

        JButton[] btns = {addBtn, updateBtn, deleteBtn};

        int x = 20;
        for (JButton b : btns) {
            b.setBounds(x, 10, 150, 40);
            styleButton(b);
            btnPanel.add(b);
            x += 170;
        }

        add(btnPanel);

        // ================= TABLE =================
        model = new DefaultTableModel();
        model.addColumn("ID");
        model.addColumn("Name");
        model.addColumn("Email");
        model.addColumn("Username");
        model.addColumn("Phone");
        model.addColumn("Status");

        table = new JTable(model);
        table.setRowHeight(28);
        table.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        table.setBackground(Color.WHITE);

        JScrollPane sp = new JScrollPane(table);
        sp.setBounds(30, 400, 1250, 360);
        add(sp);

        loadStaff();

        // ================= ACTIONS =================
        addBtn.addActionListener(e -> insertStaff());
        updateBtn.addActionListener(e -> updateStaff());
        deleteBtn.addActionListener(e -> deleteStaff());
        searchBtn.addActionListener(e -> searchStaffById());
        refreshBtn.addActionListener(e -> loadStaff());
    }

    // ================= STYLE BUTTON =================
    private void styleButton(JButton b) {
        b.setBackground(new Color(0, 153, 255));
        b.setForeground(Color.WHITE);
        b.setFont(new Font("Segoe UI", Font.BOLD, 13));
        b.setFocusPainted(false);
    }

    // ================= FIELD HELPER =================
    private void addField(JPanel p, String label, JTextField tf, int x, int y) {

        JLabel l = new JLabel(label);
        l.setBounds(x, y, 150, 25);
        l.setForeground(Color.WHITE);
        l.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        p.add(l);

        tf.setBounds(x, y + 25, 200, 30);
        tf.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        p.add(tf);
    }

    // ================= LOAD =================
    public void loadStaff() {
        try {
            ResultSet rs = dao.getAllStaff();
            model.setRowCount(0);

            while (rs.next()) {
                model.addRow(new Object[]{
                        rs.getInt("staffId"),
                        rs.getString("staffName"),
                        rs.getString("email"),
                        rs.getString("username"),
                        rs.getString("phoneNumber"),
                        rs.getString("status")
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // ================= INSERT (ADMIN ENTERS PASSWORD TOO) =================
    public void insertStaff() {
        try {
            Model.StaffModel s = new Model.StaffModel();

            s.setStaffName(txtName.getText());
            s.setEmail(txtEmail.getText());
            s.setUsername(txtUser.getText());
            s.setPassword(txtPassword.getText()); // ADMIN INPUT
            s.setPhoneNumber(txtPhone.getText());
            s.setJoiningDate(new java.sql.Date(System.currentTimeMillis()));
            s.setStatus(statusBox.getSelectedItem().toString());

            if (dao.insertStaff(s)) {
                JOptionPane.showMessageDialog(this, "Staff Added Successfully");
                loadStaff();
            }

            if (dao.insertStaff(s)) {
            JOptionPane.showMessageDialog(this, "Staff Added Successfully");
            loadStaff();
            clearForm();
        }

        } catch (Exception e) {
            e.printStackTrace();
        }

         
    }

   

    // ================= UPDATE =================
    public void updateStaff() {
        try {
            Model.StaffModel s = new Model.StaffModel();

            s.setStaffId(Integer.parseInt(txtId.getText()));
            s.setStaffName(txtName.getText());
            s.setEmail(txtEmail.getText());
            s.setUsername(txtUser.getText());
            s.setPassword(txtPassword.getText());
            s.setPhoneNumber(txtPhone.getText());
            s.setJoiningDate(new java.sql.Date(System.currentTimeMillis()));
            s.setStatus(statusBox.getSelectedItem().toString());

            dao.updateStaff(s);
            loadStaff();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // ================= DELETE =================
    public void deleteStaff() {
        try {
            dao.deleteStaff(Integer.parseInt(txtId.getText()));
            loadStaff();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // ================= SEARCH BY NAME =================
    public void searchStaffById() {
    try {
        int id = Integer.parseInt(txtSearch.getText());

        ResultSet rs = dao.getStaffById(id);
        model.setRowCount(0);

        if (rs.next()) {
            model.addRow(new Object[]{
                    rs.getInt("staffId"),
                    rs.getString("staffName"),
                    rs.getString("email"),
                    rs.getString("username"),
                    rs.getString("phoneNumber"),
                    rs.getString("status")
            });
        } else {
            JOptionPane.showMessageDialog(this, "Staff Not Found");
        }

    } catch (Exception e) {
        JOptionPane.showMessageDialog(this, "Enter Valid ID");
    }
}


        // Input box Khali karega 
        private void clearForm() {
    txtId.setText("");
    txtName.setText("");
    txtEmail.setText("");
    txtUser.setText("");
    txtPhone.setText("");
    txtPassword.setText("");
    statusBox.setSelectedIndex(0);
    txtSearch.setText("");
}
}
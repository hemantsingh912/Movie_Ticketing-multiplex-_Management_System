package View;

import Dao.ScreenDAO;
import Dao.SeatDAO;
import Model.ScreenModel;
import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class ScreenView extends JPanel {

    JTextField txtSearch;

    JTextField txtScreenName;
    JTextField txtRegularSeat;
    JTextField txtPremiumSeat;
    JTextField txtGoldSeat;
    JTextField txtTotalSeat;

    JComboBox<String> cmbScreenType;
    JComboBox<String> cmbStatus;

    JButton btnSearch;
    JButton btnRefresh;
    JButton btnSubmit;
    JButton btnDelete;

    JTable table;
    DefaultTableModel model;
    ScreenDAO dao = new ScreenDAO();

    public ScreenView() {

        setLayout(null);
        setBackground(new Color(245, 247, 250));

        // ================= SEARCH =================

        JLabel lblSearch = new JLabel("Screen ID");
        lblSearch.setBounds(30, 20, 100, 30);
        add(lblSearch);

        txtSearch = new JTextField();
        txtSearch.setBounds(130, 20, 250, 35);
        add(txtSearch);

        btnSearch = new JButton("Search");
        btnSearch.setBounds(400, 20, 120, 35);
        btnSearch.setBackground(new Color(163,128,196));
        btnSearch.setForeground(Color.WHITE);
        add(btnSearch);

        btnRefresh = new JButton("Refresh");
        btnRefresh.setBounds(540,20,120,35);
        btnRefresh.setBackground(new Color(243,224,178));
        add(btnRefresh);

        // ================= FORM =================

        JPanel form = new JPanel();
        form.setLayout(null);
        form.setBackground(Color.WHITE);
        form.setBorder(
                BorderFactory.createTitledBorder(
                        BorderFactory.createLineBorder(
                                Color.LIGHT_GRAY),
                        "Screen Details"));

        form.setBounds(20, 80, 1460, 250);

        add(form);

        addLabel(form,"Screen Name",30,40);
        txtScreenName = new JTextField();
        txtScreenName.setBounds(180,40,250,35);
        form.add(txtScreenName);

        addLabel(form,"Regular Seats",450,40);
        txtRegularSeat = new JTextField();
        txtRegularSeat.setBounds(570,40,200,35);
        form.add(txtRegularSeat);

        addLabel(form,"Premium Seats",830,40);
        txtPremiumSeat = new JTextField();
        txtPremiumSeat.setBounds(950,40,200,35);
        form.add(txtPremiumSeat);

        addLabel(form,"Gold Seats",30,110);
        txtGoldSeat = new JTextField();
        txtGoldSeat.setBounds(180,110,250,35);
        form.add(txtGoldSeat);  

        java.awt.event.KeyAdapter seatListener =
        new java.awt.event.KeyAdapter() {

    @Override
    public void keyReleased(java.awt.event.KeyEvent e) {

        try {

            int regular = txtRegularSeat.getText().isEmpty()
                    ? 0 : Integer.parseInt(txtRegularSeat.getText());

            int premium = txtPremiumSeat.getText().isEmpty()
                    ? 0 : Integer.parseInt(txtPremiumSeat.getText());

            int gold = txtGoldSeat.getText().isEmpty()
                    ? 0 : Integer.parseInt(txtGoldSeat.getText());

            txtTotalSeat.setText(
                    String.valueOf(
                            regular + premium + gold));

        } catch(Exception ex) {

            txtTotalSeat.setText("");
        }
    }
};

txtRegularSeat.addKeyListener(seatListener);
txtPremiumSeat.addKeyListener(seatListener);
txtGoldSeat.addKeyListener(seatListener);

        addLabel(form,"Total Seats",450,110);
        txtTotalSeat = new JTextField();
        txtTotalSeat.setBounds(570,110,200,35);
        txtTotalSeat.setEditable(false);
        form.add(txtTotalSeat);

        addLabel(form,"Screen Type",800,110);

        cmbScreenType =
                new JComboBox<>(
                        new String[]{
                                "2D",
                                "3D",
                                "IMAX"
                        });

        cmbScreenType.setBounds(950,110,200,35);
        form.add(cmbScreenType);

        addLabel(form,"Status",30,180);

        cmbStatus =
                new JComboBox<>(
                        new String[]{
                                "Active",
                                "Inactive"
                        });

        cmbStatus.setBounds(180,180,250,35);
        form.add(cmbStatus);

        btnSubmit = new JButton("Submit");
        btnSubmit.setBounds(700,180,150,40);
        btnSubmit.setBackground(new Color(40,167,69));
        btnSubmit.setForeground(Color.WHITE);
        form.add(btnSubmit);

        btnDelete = new JButton("Delete");
        btnDelete.setBounds(880,180,150,40);
        btnDelete.setBackground(new Color(220,53,69));
        btnDelete.setForeground(Color.WHITE);
        form.add(btnDelete);

        // ================= TABLE =================

        model = new DefaultTableModel();

        model = new DefaultTableModel(
        new Object[]{
            "ID",
            "Screen Name",
            "Regular",
            "Premium",
            "Gold",
            "Total",
            "Type",
            "Status"
        }, 0) {

            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        table = new JTable(model);

table.setRowHeight(30);
table.setDefaultEditor(Object.class, null);

        JScrollPane sp =
                new JScrollPane(table);

        sp.setBounds(20,360,1200,450);

        add(sp);
        loadScreens();
        
        btnSubmit.addActionListener(e -> addScreen());
        btnSearch.addActionListener(e -> searchScreen());
        btnDelete.addActionListener(e -> deleteScreen());
        btnRefresh.addActionListener(e -> refreshForm());
    }

    private void addLabel(
            JPanel panel,
            String text,
            int x,
            int y) {

        JLabel lbl =
                new JLabel(text);

        lbl.setBounds(x,y,140,35);

        lbl.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        16));

        panel.add(lbl);
    }

    private void loadScreens() {

    model.setRowCount(0);

    ArrayList<ScreenModel> list =
            dao.getScreenList();

    for(ScreenModel s : list) {

        model.addRow(new Object[]{

            s.getScreenId(),
            s.getScreenName(),
            s.getRegularSeatCount(),
            s.getPremiumSeatCount(),
            s.getGoldSeatCount(),
            s.getTotalSeat(),
            s.getScreenType(),
            s.getStatus()
        });
    }
}


    private void addScreen() {
        if(txtScreenName.getText().trim().isEmpty()
        || txtRegularSeat.getText().trim().isEmpty()
        || txtPremiumSeat.getText().trim().isEmpty()
        || txtGoldSeat.getText().trim().isEmpty()) {

    JOptionPane.showMessageDialog(
            this,
            "Fill All Fields");

    return;
}
    try {

        if(txtScreenName.getText().trim().isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Enter Screen Name");

            return;
        }

        ScreenModel s = new ScreenModel();

        s.setScreenName(
                txtScreenName.getText());

        s.setRegularSeatCount(
                Integer.parseInt(
                        txtRegularSeat.getText()));

        s.setPremiumSeatCount(
                Integer.parseInt(
                        txtPremiumSeat.getText()));

        s.setGoldSeatCount(
                Integer.parseInt(
                        txtGoldSeat.getText()));

        s.setTotalSeat(
                Integer.parseInt(
                        txtTotalSeat.getText()));

        s.setScreenType(
                cmbScreenType.getSelectedItem().toString());

        s.setStatus(
                cmbStatus.getSelectedItem().toString());

        if(dao.insertScreen(s)) {

            JOptionPane.showMessageDialog(
                    this,
                    "Screen Added Successfully");

            // Auto Generate Seats
            int screenId = dao.getLastScreenId();

            SeatDAO seatDao = new SeatDAO();

            seatDao.generateSeats(
                    screenId,
                    s.getRegularSeatCount(),
                    s.getPremiumSeatCount(),
                    s.getGoldSeatCount());

            loadScreens();
            refreshForm();

        } else {

            JOptionPane.showMessageDialog(
                    this,
                    "Failed");
        }

    } catch(Exception e) {

        JOptionPane.showMessageDialog(
                this,
                e.getMessage());
    }
}

        private void refreshForm() {

    txtSearch.setText("");

    txtScreenName.setText("");
    txtRegularSeat.setText("");
    txtPremiumSeat.setText("");
    txtGoldSeat.setText("");
    txtTotalSeat.setText("");

    cmbScreenType.setSelectedIndex(0);
    cmbStatus.setSelectedIndex(0);

    table.clearSelection();

    loadScreens();
}


    private void searchScreen() {

    try {

        int id =
                Integer.parseInt(
                        txtSearch.getText());

        ScreenModel s =
                dao.searchScreen(id);

        if(s == null) {

            JOptionPane.showMessageDialog(
                    this,
                    "Screen Not Found");

            return;
        }

        txtScreenName.setText(
                s.getScreenName());

        txtRegularSeat.setText(
                String.valueOf(
                        s.getRegularSeatCount()));

        txtPremiumSeat.setText(
                String.valueOf(
                        s.getPremiumSeatCount()));

        txtGoldSeat.setText(
                String.valueOf(
                        s.getGoldSeatCount()));

        txtTotalSeat.setText(
                String.valueOf(
                        s.getTotalSeat()));

        cmbScreenType.setSelectedItem(
                s.getScreenType());

        cmbStatus.setSelectedItem(
                s.getStatus());

    } catch(Exception e) {

        JOptionPane.showMessageDialog(
                this,
                "Invalid Screen ID");
    }
}

    private void deleteScreen() {

    int row = table.getSelectedRow();

    if(row == -1) {

        JOptionPane.showMessageDialog(
                this,
                "Select Screen First");

        return;
    }

    int screenId =
            Integer.parseInt(
                    model.getValueAt(row, 0)
                            .toString());

    if(dao.deleteScreen(screenId)) {

        JOptionPane.showMessageDialog(
                this,
                "Screen Deleted");

        loadScreens();

    } else {

        JOptionPane.showMessageDialog(
                this,
                "Delete Failed");
    }
}

    
}
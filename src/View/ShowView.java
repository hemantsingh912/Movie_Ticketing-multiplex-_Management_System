package View;

import Dao.MovieDAO;
import Dao.ScreenDAO;
import Dao.SeatPriceDAO;
import Dao.ShowDAO;
import Model.MovieModel;
import Model.ScreenModel;
import Model.SeatPriceModel;
import Model.ShowModel;
import java.awt.*;
import java.sql.ResultSet;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class ShowView extends JPanel {

    public JTextField txtSearch;
    public JTextField txtDate;
    public JTextField txtTime;

   
    JComboBox<MovieModel> cmbMovie;
    JComboBox<ScreenModel> cmbScreen;
    public JComboBox cmbStatus;

    public JButton btnSearch;
    public JButton btnRefresh;
    public JButton btnAdd;
    public JButton btnDelete;

    public JTable table;
    public DefaultTableModel model;

    public JTextField txtRegularPrice;
public JTextField txtPremiumPrice;
public JTextField txtGoldPrice;

    

    public ShowView() {

        setLayout(null);
    setBackground(new Color(245,247,250));
    Font f =new Font("Segoe UI", Font.BOLD, 15);

        

        // SEARCH PANEL

        JLabel lblSearch = new JLabel("Show ID");
        lblSearch.setBounds(30,20,100,30);
        lblSearch.setForeground(Color.BLACK);
        add(lblSearch);

        txtSearch = new JTextField();
        txtSearch.setBounds(140,20,250,35);
        add(txtSearch);

        btnSearch = new JButton("Search");
       btnSearch.setBounds(410,20,120,35);
        btnSearch.setBackground(
        new Color(163,128,196));
        btnSearch.setForeground(Color.WHITE);
        add(btnSearch);

        btnRefresh = new JButton("Refresh");
        btnRefresh.setBounds(550,20,120,35);
        btnRefresh.setBackground(new Color(243,224,178));
        add(btnRefresh);

        // FORM PANEL

        JPanel formPanel = new JPanel();
        formPanel.setLayout(null);
        formPanel.setBackground(Color.WHITE);
        formPanel.setBorder( BorderFactory.createTitledBorder(BorderFactory.createLineBorder(
                        new Color(180,180,180)),"Movie Details",
                        0,0,new Font("Segoe UI",Font.BOLD,16)));
        formPanel.setBounds(20,70,1460,220);

        add(formPanel);

        JLabel lblMovie = new JLabel("Movie");
        lblMovie.setBounds(30,20,100,25);
        lblMovie.setForeground(Color.black);
        formPanel.add(lblMovie);

        cmbMovie = new JComboBox<MovieModel>();
        cmbMovie.setBounds(140,20,220,30);
        formPanel.add(cmbMovie);

        JLabel lblScreen = new JLabel("Screen");
        lblScreen.setBounds(400,20,100,25);
        lblScreen.setForeground(Color.black);
        formPanel.add(lblScreen);

            
        cmbScreen = new JComboBox<ScreenModel>();
        cmbScreen.setBounds(520,20,220,30);
        formPanel.add(cmbScreen);

        JLabel lblDate = new JLabel("Show Date");
        lblDate.setBounds(30,80,100,25);
        lblDate.setForeground(Color.black);
        formPanel.add(lblDate);

        txtDate = new JTextField();
        txtDate.setBounds(140,80,220,30);
        txtDate.setToolTipText("yyyy-mm-dd");
        formPanel.add(txtDate);

        JLabel lblTime = new JLabel("Show Time");
        lblTime.setBounds(400,80,100,25);
        lblTime.setForeground(Color.black);
        formPanel.add(lblTime);

        txtTime = new JTextField();
        txtTime.setBounds(520,80,220,30);
        txtTime.setToolTipText("HH:mm:ss");
        formPanel.add(txtTime);

        JLabel lblStatus = new JLabel("Status");
        lblStatus.setBounds(780,20,100,25);
        lblStatus.setForeground(Color.black);
        formPanel.add(lblStatus);

        cmbStatus = new JComboBox(new String[]{ "Active","House Full","Cancelled","Completed"});

        cmbStatus.setBounds(880,20,220,30);
        formPanel.add(cmbStatus);

            JLabel lblRegular = new JLabel("Regular ₹");
            lblRegular.setBounds(780,80,90,25);
            lblRegular.setForeground(Color.black);
            formPanel.add(lblRegular);
            txtRegularPrice = new JTextField();
            txtRegularPrice.setBounds(870,80,80,30);
            formPanel.add(txtRegularPrice);


            JLabel lblPremium = new JLabel("Premium ₹");
            lblPremium.setBounds(960,80,90,25);
            lblPremium.setForeground(Color.black);
            formPanel.add(lblPremium);
            txtPremiumPrice = new JTextField();
            txtPremiumPrice.setBounds(1050,80,80,30);
            formPanel.add(txtPremiumPrice);


            JLabel lblGold = new JLabel("Gold ₹");
            lblGold.setBounds(780,120,90,25);
            lblGold.setForeground(Color.black);
            formPanel.add(lblGold);
            txtGoldPrice = new JTextField();
            txtGoldPrice.setBounds(870,120,80,30);
            formPanel.add(txtGoldPrice);

       JButton btnAdd = new JButton("Save Movie");
        btnAdd.setBounds(900,160,120,40);
        btnAdd.setBackground(new Color(40,167,69));
        btnAdd.setForeground(Color.WHITE);
        formPanel.add(btnAdd);

        btnDelete = new JButton("Delete Movie");
       btnDelete.setBounds(1050,160,120,40);
        btnDelete.setBackground(new Color(220,53,69));
        btnDelete.setForeground(Color.WHITE);
        btnDelete.setForeground(Color.WHITE);
        formPanel.add(btnDelete);

        // TABLE

        String columns[] = {
                            "Show ID",
                            "Movie",
                            "Screen",
                            "Date",
                            "Time",
                            "Regular",
                            "Premium",
                            "Gold",
                            "Status"
                        };

        model = new DefaultTableModel(columns,0);
                        
        table = new JTable(model);

        table.setRowHeight(30);
        table.setFont(new Font("Arial",Font.PLAIN,14));
        table.setBackground(new Color(60,63,65));
        table.setForeground(Color.WHITE);
        table.setSelectionBackground(new Color(52,152,219));
        table.setSelectionForeground(Color.WHITE);
        table.setGridColor(Color.GRAY);

        JScrollPane sp = new JScrollPane(table);
        sp.setBounds(40,330,1150,300);
        add(sp);

        
        loadMovies();
        loadScreens();
        loadTable();

        btnSearch.addActionListener(e -> searchShow());
        btnRefresh.addActionListener(e -> refreshData());
        btnAdd.addActionListener(e -> addShow());
        btnDelete.addActionListener(e -> deleteShow());
        
    }
    private void loadMovies() {

    MovieDAO dao = new MovieDAO();

    cmbMovie.removeAllItems();

    for(MovieModel m : dao.getMovieList()) {

        cmbMovie.addItem(m);
    }
}
    private void loadScreens() {

    ScreenDAO dao = new ScreenDAO();

    cmbScreen.removeAllItems();

    for(ScreenModel s : dao.getScreenList()) {

        cmbScreen.addItem(s);
    }
}

    private void loadTable() {

    model.setRowCount(0);

    try {

        ShowDAO dao =
        new ShowDAO();

        ResultSet rs =
        dao.getShowDetails();

        while(rs.next()) {

            model.addRow(new Object[]{

                rs.getInt("showId"),
                rs.getString("movieName"),
                rs.getString("screenName"),
                rs.getDate("showDate"),
                rs.getTime("showTime"),

                rs.getDouble("RegularPrice"),
                rs.getDouble("PremiumPrice"),
                rs.getDouble("GoldPrice"),

                rs.getString("showStatus")
            });
        }

    } catch(Exception e) {

        e.printStackTrace();
    }
}

    private void loadDummyData() {

        model.setRowCount(0);

        model.addRow(new Object[]{
                1,
                "Pushpa 2",
                "Screen 1",
                "2026-06-25",
                "13:00",
                "Active"
        });

        model.addRow(new Object[]{
                2,
                "Red One",
                "Screen 2",
                "2026-06-25",
                "18:00",
                "Active"
        });
    }

    private void searchShow() {

    try {

        String text =
                txtSearch.getText().trim();

        if(text.isEmpty()) {

            JOptionPane.showMessageDialog(
                    null,
                    "Enter Show ID");

            return;
        }

        int showId =
                Integer.parseInt(text);

        ShowDAO dao =
                new ShowDAO();

        ResultSet rs =
                dao.getShowById(showId);

        model.setRowCount(0);

        if(rs.next()) {

            model.addRow(new Object[] {

                rs.getInt("showId"),
                rs.getInt("movieId"),
                rs.getInt("screenId"),
                rs.getDate("showDate"),
                rs.getTime("showTime"),
                rs.getString("showStatus")
            });

        } else {

            JOptionPane.showMessageDialog(
                    null,
                    "Show Not Found");
        }

    } catch(Exception e) {

        JOptionPane.showMessageDialog(
                null,
                "Invalid Show ID");
    }
}

private void refreshData() {

    txtSearch.setText("");
    txtDate.setText("");
    txtTime.setText("");

    if(cmbMovie.getItemCount() > 0)
        cmbMovie.setSelectedIndex(0);

    if(cmbScreen.getItemCount() > 0)
        cmbScreen.setSelectedIndex(0);

    cmbStatus.setSelectedIndex(0);

    loadTable();
}

    private void addShow() {

    try {

        MovieModel movie =
                (MovieModel) cmbMovie.getSelectedItem();

        ScreenModel screen =
                (ScreenModel) cmbScreen.getSelectedItem();

        if(movie == null || screen == null) {

            JOptionPane.showMessageDialog(
                    null,
                    "Select Movie and Screen");

            return;
        }

        java.sql.Date showDate =
                java.sql.Date.valueOf(
                        txtDate.getText().trim());

        java.sql.Time showTime =
                java.sql.Time.valueOf(
                        txtTime.getText().trim() + ":00");

        String status =
                cmbStatus.getSelectedItem().toString();

        ShowDAO dao =
                new ShowDAO();

        boolean available =
                dao.isShowTimeAvailable(
                        screen.getScreenId(),
                        showDate,
                        showTime,
                        movie.getDurationMinutes());

        if(!available) {

            JOptionPane.showMessageDialog(
                    null,
                    "Screen already occupied at this time");

            return;
        }

        ShowModel show =
                new ShowModel();

        show.setMovieId(
                movie.getMovieId());

        show.setScreenId(
                screen.getScreenId());

        show.setShowDate(
                showDate);

        show.setShowTime(
                showTime);

        show.setShowStatus(
                status);
int showId = dao.insertShow(show);

if(showId > 0)
{
    SeatPriceDAO priceDAO = new SeatPriceDAO();

    SeatPriceModel sp1 = new SeatPriceModel();
    sp1.setShowId(showId);
    sp1.setSeatType("Regular");
    sp1.setPrice(
            Double.parseDouble(
                    txtRegularPrice.getText()));

    priceDAO.insertSeatPrice(sp1);

    SeatPriceModel sp2 = new SeatPriceModel();
    sp2.setShowId(showId);
    sp2.setSeatType("Premium");
    sp2.setPrice(
            Double.parseDouble(
                    txtPremiumPrice.getText()));

    priceDAO.insertSeatPrice(sp2);

    SeatPriceModel sp3 = new SeatPriceModel();
    sp3.setShowId(showId);
    sp3.setSeatType("Gold");
    sp3.setPrice(
            Double.parseDouble(
                    txtGoldPrice.getText()));

    priceDAO.insertSeatPrice(sp3);

    JOptionPane.showMessageDialog(
            null,
            "Show Added Successfully");

    loadTable();
    clearForm();
}
else
{
    JOptionPane.showMessageDialog(
            null,
            "Failed");
}

    } catch(Exception e) {

        JOptionPane.showMessageDialog(
                null,
                "Invalid Date/Time Format");
    }
}

    private void clearForm() {

    txtDate.setText("");
    txtTime.setText("");

    txtRegularPrice.setText("");
    txtPremiumPrice.setText("");
    txtGoldPrice.setText("");

    if(cmbMovie.getItemCount() > 0)
        cmbMovie.setSelectedIndex(0);

    if(cmbScreen.getItemCount() > 0)
        cmbScreen.setSelectedIndex(0);

    cmbStatus.setSelectedIndex(0);
}  

    private void deleteShow() {

    try {

        int row = table.getSelectedRow();

        if(row == -1) {

            JOptionPane.showMessageDialog(
                    null,
                    "Please Select Show");

            return;
        }

        int showId =
                Integer.parseInt(
                        model.getValueAt(row, 0)
                                .toString());

        int confirm =
                JOptionPane.showConfirmDialog(
                        null,
                        "Delete Selected Show ?",
                        "Confirm",
                        JOptionPane.YES_NO_OPTION);

        if(confirm != JOptionPane.YES_OPTION) {

            return;
        }

        ShowDAO dao = new ShowDAO();

if(dao.deleteShow(showId)) {

    JOptionPane.showMessageDialog(
            null,
            "Show Deleted Successfully");

    loadTable();

} else {

            JOptionPane.showMessageDialog(
                    null,
                    "Delete Failed");
        }

    } catch(Exception e) {

        e.printStackTrace();

        JOptionPane.showMessageDialog(
                null,
                e.getMessage());
    }
}

    
}


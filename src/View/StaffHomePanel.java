package View;

import Dao.MovieDAO;
import Dao.ScreenDAO;
import Dao.ShowDAO;
import java.awt.*;
import javax.swing.*;

public class StaffHomePanel extends JPanel {

    private JLabel totalMoviesLabel;
    private JLabel todayShowsLabel;
    private JLabel totalScreenLabel;

    private JLabel sliderLabel;

    private int index = 0;

    private String images[] = {
        "C:\\Users\\DELL\\OneDrive\\Pictures\\movieposter\\img14.jpg",
        "C:\\Users\\DELL\\OneDrive\\Pictures\\movieposter\\img20.jpg",
        "C:\\Users\\DELL\\OneDrive\\Pictures\\movieposter\\img21.jpg",
        "C:\\Users\\DELL\\OneDrive\\Pictures\\movieposter\\img11.jpg"
    };

    public StaffHomePanel() {

        setLayout(null);
        setBackground(new Color(235, 238, 245));

        JLabel title = new JLabel("STAFF DASHBOARD");
        title.setBounds(40, 10, 500, 40);
        title.setFont(new Font("Segoe UI", Font.BOLD, 28));
        add(title);

        // ================= SLIDER =================
        sliderLabel = new JLabel();
        sliderLabel.setBounds(40, 60, 1200, 380);
        add(sliderLabel);

        showImage();

        Timer timer = new Timer(3000, e -> {
            index++;
            if (index >= images.length) index = 0;
            showImage();
        });
        timer.start();

        // ================= CARDS =================

        add(createCard("Total Movies", new Color(255, 87, 34), 40, 470));
        add(createCard("Today's Shows", new Color(33, 150, 243), 420, 470));
        add(createCard("Total Screens", new Color(76, 175, 80), 800, 470));

        loadStats();
    }

    // ================= SLIDER =================
    private void showImage() {
        ImageIcon icon = new ImageIcon(images[index]);
        Image img = icon.getImage().getScaledInstance(1100, 380, Image.SCALE_SMOOTH);
        sliderLabel.setIcon(new ImageIcon(img));
    }

    // ================= CARD =================
    private JPanel createCard(String title, Color color, int x, int y) {

        JPanel card = new JPanel();
        card.setLayout(null);
        card.setBounds(x, y, 340, 180);
        card.setBackground(color);

        JLabel lbl = new JLabel(title);
        lbl.setBounds(20, 20, 300, 30);
        lbl.setFont(new Font("Segoe UI", Font.BOLD, 18));
        lbl.setForeground(Color.WHITE);
        card.add(lbl);

        JLabel value = new JLabel("0");
        value.setBounds(20, 70, 300, 60);
        value.setFont(new Font("Segoe UI", Font.BOLD, 45));
        value.setForeground(Color.WHITE);
        card.add(value);

        if (title.equals("Total Movies")) totalMoviesLabel = value;
        if (title.equals("Today's Shows")) todayShowsLabel = value;
        if (title.equals("Total Screens")) totalScreenLabel = value;

        return card;
    }

    // ================= LOAD DATA FROM DAO =================
    private void loadStats() {

        MovieDAO movieDAO = new MovieDAO();
        ScreenDAO screenDAO = new ScreenDAO();
        ShowDAO showDAO = new ShowDAO();

        totalMoviesLabel.setText(String.valueOf(movieDAO.getMovieCount()));
        totalScreenLabel.setText(String.valueOf(screenDAO.getScreenCount()));
        todayShowsLabel.setText(String.valueOf(showDAO.getTodayShowCount()));
    }
}
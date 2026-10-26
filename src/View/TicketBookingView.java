package View;

import Dao.MovieDAO;
import Model.MovieModel;
import java.awt.*;
import javax.swing.*;

public class TicketBookingView extends JPanel {

    JTextField txtName, txtMobile, txtEmail;

    private JPanel rightPanel;
    private int movieId;

    JComboBox<MovieModel> cmbMovie;

    public TicketBookingView(int movieId, JPanel rightPanel) {

        this.movieId = movieId;
        this.rightPanel = rightPanel;

        setLayout(null);
        setBackground(Color.WHITE);

        // ================= TITLE =================
        JLabel title = new JLabel("TICKET BOOKING");
        title.setBounds(450, 20, 300, 40);
        title.setFont(new Font("Arial", Font.BOLD, 28));
        add(title);

        // ================= FORM =================
        JPanel form = new JPanel();
        form.setLayout(null);
        form.setBounds(250, 80, 650, 400);
        form.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        form.setBackground(new Color(245, 245, 245));
        add(form);

        // ================= NAME =================
        JLabel lblName = new JLabel("Customer Name");
        lblName.setBounds(50, 40, 150, 30);
        txtName = new JTextField();
        txtName.setBounds(220, 40, 300, 30);

        // ================= MOBILE =================
        JLabel lblMobile = new JLabel("Mobile Number");
        lblMobile.setBounds(50, 100, 150, 30);
        txtMobile = new JTextField();
        txtMobile.setBounds(220, 100, 300, 30);

        // ================= EMAIL =================
        JLabel lblEmail = new JLabel("Email");
        lblEmail.setBounds(50, 160, 150, 30);
        txtEmail = new JTextField();
        txtEmail.setBounds(220, 160, 300, 30);

        // ================= MOVIE (OPTION LIST) =================
        JLabel lblMovie = new JLabel("Select Movie");
        lblMovie.setBounds(50, 220, 150, 30);

        cmbMovie = new JComboBox<>();
        cmbMovie.setBounds(220, 220, 300, 30);

        loadMovies(); // 🔥 database se load

        // ================= NEXT BUTTON =================
        JButton btnNext = new JButton("NEXT");
        btnNext.setBounds(250, 300, 150, 40);
        btnNext.setBackground(new Color(255, 140, 0));
        btnNext.setForeground(Color.WHITE);

        

        // ================= ADD =================

        
        form.add(lblName);
        form.add(txtName);

        form.add(lblMobile);
        form.add(txtMobile);

        form.add(lblEmail);
        form.add(txtEmail);

        form.add(lblMovie);
        form.add(cmbMovie);

        form.add(btnNext);
        btnNext.addActionListener(e -> {

    MovieModel movie =
            (MovieModel) cmbMovie.getSelectedItem();

    if(movie == null) {

        JOptionPane.showMessageDialog(this,
                "Please Select Movie");
        return;
    }

    if(txtName.getText().trim().isEmpty()
            || txtMobile.getText().trim().isEmpty()
            || txtEmail.getText().trim().isEmpty()) {

        JOptionPane.showMessageDialog(this,
                "Please Fill All Fields");
        return;
    }

    int selectedMovieId = movie.getMovieId();

    rightPanel.removeAll();
    rightPanel.setLayout(new BorderLayout());

   // rightPanel.add(new SeatSelectionView(selectedMovieId,txtName.getText(),txtMobile.getText(),txtEmail.getText(),rightPanel),BorderLayout.CENTER);

    rightPanel.revalidate();
    rightPanel.repaint();
});

        

        // ================= ACTION =================
        /*btnNext.addActionListener(e -> {

            MovieModel movie =
                    (MovieModel) cmbMovie.getSelectedItem();

            if (movie == null) {
                JOptionPane.showMessageDialog(this, "Select Movie");
                return;
            }

            if (txtName.getText().trim().isEmpty()
                    || txtMobile.getText().trim().isEmpty()
                    || txtEmail.getText().trim().isEmpty()) {

                JOptionPane.showMessageDialog(this,
                        "Please Fill All Fields");
                return;
            }

            int selectedMovieId = movie.getMovieId();

            rightPanel.removeAll();
            rightPanel.setLayout(new BorderLayout());

            rightPanel.add(
                    new SeatSelectionView(selectedMovieId),
                    BorderLayout.CENTER
            );

            rightPanel.revalidate();
            rightPanel.repaint();
        });*/
    }

    // ================= LOAD MOVIES FROM DB =================
    private void loadMovies() {

        MovieDAO dao = new MovieDAO();

        cmbMovie.removeAllItems();

        for (MovieModel m : dao.getMovieList()) {
            cmbMovie.addItem(m);
        }
    }
}
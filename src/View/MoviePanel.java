/*package View;

import Dao.MovieDAO;
import Model.MovieModel;
import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;

public class MoviePanel extends JPanel {

    public MoviePanel() {

        setLayout(null);
        setBackground(new Color(20, 20, 20));

        JLabel heading = new JLabel("Now Showing Movies");
        heading.setBounds(40, 20, 400, 35);
        heading.setForeground(Color.WHITE);
        heading.setFont(new Font("Segoe UI", Font.BOLD, 24));
        add(heading);
        
        loadMovies();
    }

    private void loadMovies() {

        MovieDAO dao = new MovieDAO();
        ArrayList<MovieModel> movieList = dao.getMovieList();

        int x = 40;
        int y = 80;
        int count = 0;

        for (MovieModel movie : movieList) {

            addMovie(
                    movie.getMovieId(),
                    movie.getPosterPath(),
                    movie.getMovieName(),
                    movie.getLanguage(),
                    x,
                    y);

            count++;

            x += 300;

            if (count % 4 == 0) {
                x = 40;
                y += 400;
            }
        }

        // Last card ke hisab se panel height
        setPreferredSize(new Dimension(1300, y + 450));

        revalidate();
        repaint();
    }

    private void addMovie(
            int movieId,
            String posterPath,
            String movieName,
            String language,
            int x,
            int y) {

        JPanel card = new JPanel();
        card.setLayout(null);
        card.setBounds(x, y, 250, 350);
        card.setBackground(new Color(35, 35, 35));
        card.setBorder(
                BorderFactory.createLineBorder(
                        new Color(255, 140, 0), 2));

        JLabel poster;

        try {

            ImageIcon icon = new ImageIcon(
                    getClass().getResource(
                            "/Images/" + posterPath));

            Image img = icon.getImage()
                    .getScaledInstance(
                            230,
                            200,
                            Image.SCALE_SMOOTH);

            poster = new JLabel(new ImageIcon(img));

        } catch (Exception e) {

            poster = new JLabel("No Image");
            poster.setForeground(Color.WHITE);
            poster.setHorizontalAlignment(
                    SwingConstants.CENTER);
        }

        poster.setBounds(10, 10, 230, 200);

        JLabel name = new JLabel(movieName);
        name.setBounds(15, 220, 220, 25);
        name.setForeground(Color.WHITE);
        name.setFont(
                new Font("Segoe UI", Font.BOLD, 18));

        JLabel lang = new JLabel(language);
        lang.setBounds(15, 250, 220, 20);
        lang.setForeground(Color.LIGHT_GRAY);

        JButton bookBtn = new JButton("Book Now");
        bookBtn.setBounds(55, 290, 140, 35);

        bookBtn.setBackground(
                new Color(255, 140, 0));
        bookBtn.setForeground(Color.WHITE);
        bookBtn.setFocusPainted(false);

        bookBtn.addActionListener(e -> {

        rightPanel.removeAll();
        rightPanel.setLayout(new BorderLayout());

        rightPanel.add(
                new TicketBookingView(movieId, rightPanel),
                BorderLayout.CENTER
        );

        rightPanel.revalidate();
        rightPanel.repaint();
        });

        card.add(poster);
        card.add(name);
        card.add(lang);
        card.add(bookBtn);

        add(card);
    }
} */



  package View;

import Dao.MovieDAO;
import Model.MovieModel;
import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;

public class MoviePanel extends JPanel {

    private JPanel rightPanel;

    // ✅ Constructor me rightPanel pass karo
    public MoviePanel(JPanel rightPanel) {
        this.rightPanel = rightPanel;

        setLayout(null);
        setBackground(new Color(20, 20, 20));

        JLabel heading = new JLabel("Now Showing Movies");
        heading.setBounds(40, 20, 400, 35);
        heading.setForeground(Color.WHITE);
        heading.setFont(new Font("Segoe UI", Font.BOLD, 24));
        add(heading);

        loadMovies();
    }

    private void loadMovies() {

        MovieDAO dao = new MovieDAO();
        ArrayList<MovieModel> movieList = dao.getMovieList();

        int x = 40;
        int y = 80;
        int count = 0;

        for (MovieModel movie : movieList) {

            addMovie(
                    movie.getMovieId(),
                    movie.getPosterPath(),
                    movie.getMovieName(),
                    movie.getLanguage(),
                    x,
                    y);

            count++;

            x += 300;

            if (count % 4 == 0) {
                x = 40;
                y += 400;
            }
        }

        setPreferredSize(new Dimension(1300, y + 450));
        revalidate();
        repaint();
    }

    private void addMovie(
            int movieId,
            String posterPath,
            String movieName,
            String language,
            int x,
            int y) {

        JPanel card = new JPanel();
        card.setLayout(null);
        card.setBounds(x, y, 250, 350);
        card.setBackground(new Color(35, 35, 35));
        card.setBorder(BorderFactory.createLineBorder(new Color(255, 140, 0), 2));

        JLabel poster;

        try {
            ImageIcon icon = new ImageIcon(
                    getClass().getResource("/Images/" + posterPath));

            Image img = icon.getImage()
                    .getScaledInstance(230, 200, Image.SCALE_SMOOTH);

            poster = new JLabel(new ImageIcon(img));

        } catch (Exception e) {
            poster = new JLabel("No Image");
            poster.setForeground(Color.WHITE);
            poster.setHorizontalAlignment(SwingConstants.CENTER);
        }

        poster.setBounds(10, 10, 230, 200);

        JLabel name = new JLabel(movieName);
        name.setBounds(15, 220, 220, 25);
        name.setForeground(Color.WHITE);
        name.setFont(new Font("Segoe UI", Font.BOLD, 18));

        JLabel lang = new JLabel(language);
        lang.setBounds(15, 250, 220, 20);
        lang.setForeground(Color.LIGHT_GRAY);

        // ================= BOOK BUTTON =================
        JButton bookBtn = new JButton("Book Now");
        bookBtn.setBounds(55, 290, 140, 35);
        bookBtn.setBackground(new Color(255, 140, 0));
        bookBtn.setForeground(Color.WHITE);
        bookBtn.setFocusPainted(false);

        bookBtn.addActionListener(e -> {

            rightPanel.removeAll();
            rightPanel.setLayout(new BorderLayout());

            rightPanel.add(
                    new TicketBookingView(movieId, rightPanel),
                    BorderLayout.CENTER
            );

            rightPanel.revalidate();
            rightPanel.repaint();
        });

        card.add(poster);
        card.add(name);
        card.add(lang);
        card.add(bookBtn);

        add(card);
    }
}  
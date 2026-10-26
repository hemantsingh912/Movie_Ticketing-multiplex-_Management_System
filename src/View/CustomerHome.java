package View;

import java.awt.*;
import javax.swing.*;

public class CustomerHome extends JPanel {

    private JLabel sliderLabel;

    private String images[] = {
        "C:\\Users\\DELL\\OneDrive\\Pictures\\movieposter\\img14.jpg",
        "C:\\Users\\DELL\\OneDrive\\Pictures\\movieposter\\img20.jpg",
        "C:\\Users\\DELL\\OneDrive\\Pictures\\movieposter\\img21.jpg",
        "C:\\Users\\DELL\\OneDrive\\Pictures\\movieposter\\img11.jpg",
        "C:\\Users\\DELL\\OneDrive\\Pictures\\movieposter\\img8.jpg",
        "C:\\Users\\DELL\\OneDrive\\Pictures\\movieposter\\img7.jpg"
    };

    private int index = 0;
    
   public CustomerHome() {

    
    setLayout(null);
    setBounds(0, 0, 1280, 850);
    setBackground(Color.WHITE);

    JLabel title = new JLabel("Latest Movies");
    title.setBounds(40, 5, 300, 40);
    title.setFont(new Font("Segoe UI", Font.BOLD, 20));
    add(title);

    sliderLabel = new JLabel();
    sliderLabel.setBounds(20, 40, 1170, 380);
    add(sliderLabel);

    showImage();

    Timer timer = new Timer(4000, e -> {
        index++;
        if(index >= images.length)
            index = 0;

        showImage();
    });

    timer.start();

    JLabel heading = new JLabel("Recommended Movies");
    heading.setBounds(40, 430, 400, 35);
    heading.setFont(new Font("Segoe UI", Font.BOLD, 20));
    add(heading);

    addMovie(
        "C:\\Users\\DELL\\OneDrive\\Pictures\\movieposter\\img1.jpg",
        "Pushpa 2",
        "Hindi",
        70);

    addMovie(
        "C:\\Users\\DELL\\OneDrive\\Pictures\\movieposter\\img6.jpg",
        "KGF 2",
        "Hindi, Kannada",
        500);

    addMovie(
        "C:\\Users\\DELL\\OneDrive\\Pictures\\movieposter\\img7.jpg",
        "Salaar",
        "Hindi, Telugu",
        930);

}

    private void showImage() {

        ImageIcon icon = new ImageIcon(images[index]);

        Image img = icon.getImage().getScaledInstance(
                1230,
                430,
                Image.SCALE_SMOOTH);

        sliderLabel.setIcon(new ImageIcon(img));
    }

    // First movie Row

   private void addMovie(String path,String movieName,String language,int x) {

    ImageIcon icon = new ImageIcon(path);

    Image img = icon.getImage().getScaledInstance(
            250, 250, Image.SCALE_SMOOTH);

    JLabel poster = new JLabel(new ImageIcon(img));
    poster.setBounds(x, 470, 250, 220);
    add(poster);

    JLabel name = new JLabel(movieName);
    name.setBounds(x, 700, 250, 25);
    name.setFont(new Font("Segoe UI", Font.BOLD, 18));
    add(name);

    JLabel lang = new JLabel(language);
    lang.setBounds(x, 725, 250, 20);
    lang.setForeground(Color.GRAY);
    add(lang);

    JLabel rating = new JLabel("⭐ 8.8/10");
    rating.setBounds(x, 750, 150, 20);
    add(rating);
}

        // Second movie Row
        
    private void addMovie(String path,String movieName,String language,int x,int y) {

        ImageIcon icon = new ImageIcon(path);

        Image img = icon.getImage().getScaledInstance(250,220,Image.SCALE_SMOOTH);

        JLabel poster = new JLabel(new ImageIcon(img));
        poster.setBounds(x, 470, 250, 220);
        add(poster);

        JLabel name = new JLabel(movieName);
        name.setBounds(x, y + 310, 250, 25);
        name.setFont(new Font("Segoe UI", Font.BOLD, 18));
        add(name);

        JLabel lang = new JLabel(language);
        lang.setBounds(x, y + 335, 250, 20);
        lang.setForeground(Color.GRAY);
        add(lang);

        JLabel rating = new JLabel("⭐ 8.8/10");
        rating.setBounds(x, y + 360, 150, 20);
        add(rating);
    }

    
}
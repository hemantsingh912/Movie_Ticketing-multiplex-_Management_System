package View;

import Dao.StaffDAO;
import Dao.UserDAO;
import Model.UserModel;
import java.awt.*;
import javax.swing.*;

public class LoginView extends JFrame {

    public LoginView() {

        setExtendedState(JFrame.MAXIMIZED_BOTH);

        ImageIcon icon = new ImageIcon(
                "C:\\Users\\DELL\\OneDrive\\Pictures\\movieposter\\img1.jpg"
        );

        Image img = icon.getImage();

        Dimension screen =
                Toolkit.getDefaultToolkit().getScreenSize();

        Image resized = img.getScaledInstance(
                screen.width,
                screen.height,
                Image.SCALE_SMOOTH
        );

        JLabel background =
                new JLabel(new ImageIcon(resized));

        setContentPane(background);
        background.setLayout(null);

        loginPanel();

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public void loginPanel() {

        // Left Panel
        JPanel leftPanel = new JPanel();
        leftPanel.setBounds(360, 100, 410, 400);
        leftPanel.setLayout(null);
        leftPanel.setBackground(new Color(0, 180, 120));

        JButton Backbtn=new JButton("Back");
                Backbtn.setForeground(Color.black);
                Backbtn.setFont(new Font("Arial", Font.BOLD, 10));
                Backbtn.setBackground(new Color(0, 180, 120));
                Backbtn.setBounds(5,10,60,30);
                Backbtn.setFocusPainted(false);
                Backbtn.setBorderPainted(false);
                leftPanel.add(Backbtn);

                Backbtn.addActionListener(e ->{
                    Dashboard obj=new Dashboard();
                });

        JLabel welcome =
                new JLabel("Welcome Back!");

        welcome.setForeground(Color.WHITE);
        welcome.setFont(
                new Font("Arial", Font.BOLD, 30)
        );

        welcome.setBounds(80, 140, 250, 40);

        leftPanel.add(welcome);

        JLabel text = new JLabel(
                "<html>To keep connected with us<br>Please login with your personal info</html>"
        );

        text.setForeground(Color.BLACK);
        text.setFont(
                new Font("Arial", Font.PLAIN, 14)
        );

        text.setBounds(80, 190, 250, 60);

        leftPanel.add(text);

        JButton signupButton =
                new JButton("SIGN UP");

        signupButton.setBounds(
                110, 300, 180, 45
        );

        signupButton.setBackground(
                new Color(0, 150, 100)
        );

        signupButton.setForeground(Color.WHITE);

        signupButton.addActionListener(e -> {

            dispose();
            new SignupView();

        });

        leftPanel.add(signupButton);

        add(leftPanel);

        // Right Panel
        JPanel rightPanel = new JPanel();

        rightPanel.setBounds(
                770, 100, 410, 400
        );

        rightPanel.setLayout(null);
        rightPanel.setBackground(Color.WHITE);

        JLabel loginLabel =
                new JLabel("LOGIN");

        loginLabel.setFont(
                new Font("Arial", Font.BOLD, 30)
        );

        loginLabel.setBounds(
                130, 40, 200, 40
        );

        rightPanel.add(loginLabel);

        JTextField txtUsername =
                new JTextField();

        txtUsername.setBounds(
                60, 120, 280, 40
        );

        txtUsername.setBorder(
                BorderFactory.createTitledBorder(
                        "Username"
                )
        );

        rightPanel.add(txtUsername);

        JPasswordField txtPassword =
                new JPasswordField();

        txtPassword.setBounds(
                60, 190, 280, 40
        );

        txtPassword.setBorder(
                BorderFactory.createTitledBorder(
                        "Password"
                )
        );

        rightPanel.add(txtPassword);

        JButton loginButton =
                new JButton("LOGIN");

        loginButton.setBounds(
                110, 300, 180, 45
        );

        loginButton.setBackground(
                new Color(0, 150, 100)
        );

        loginButton.setForeground(Color.WHITE);

        rightPanel.add(loginButton);

        // LOGIN EVENT
        loginButton.addActionListener(e -> {

    String username =
            txtUsername.getText().trim();

    String password =
            new String(
                    txtPassword.getPassword()
            );

    if(username.isEmpty()
            || password.isEmpty()) {

        JOptionPane.showMessageDialog(
                this,
                "Please Enter Username And Password"
        );

        return;
    }

    // ================= ADMIN LOGIN =================

    if(username.equals("Hemantsingh")&& password.equals("Hemant0912")) {

        JOptionPane.showMessageDialog(
                this,
                "Admin Login Successful"
        );

        dispose();

        new AdminDashboardView(); // Admin Dashboard Class

        return;
    }

    // ================= STAFF LOGIN =================

    StaffDAO staffDAO = new StaffDAO();

    if (staffDAO.loginStaff(username, password)) {

        JOptionPane.showMessageDialog(
                this,
                "Staff Login Successful"
        );

        dispose();

        new StaffDashboardView(); // Staff Dashboard

        return;
    }

    // ================= CUSTOMER LOGIN =================

    UserDAO dao = new UserDAO();

    UserModel user =
            dao.loginUser(
                    username,
                    password
            );

    if(user != null) {

        JOptionPane.showMessageDialog(
                this,
                "Login Successful\nWelcome "
                        + user.getFullName()
        );

        dispose();
        
        new Dashboard(); // Customer Dashboard

    } else {

        JOptionPane.showMessageDialog(
                this,
                "Invalid Username Or Password"
        );
    }

});

        add(rightPanel);
    }

    public static void main(String[] args) {

        new LoginView();

    }
}
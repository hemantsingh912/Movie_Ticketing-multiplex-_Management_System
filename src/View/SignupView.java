package View;

import Dao.UserDAO;
import Model.UserModel;
import java.awt.*;
import javax.swing.*;
public class SignupView extends JFrame {

        public SignupView() {

        setExtendedState(JFrame.MAXIMIZED_BOTH);

        ImageIcon icon = new ImageIcon("C:\\Users\\DELL\\OneDrive\\Pictures\\movieposter\\img1.jpg");

        Image img = icon.getImage();

        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();

        Image resized = img.getScaledInstance(screen.width,screen.height,Image.SCALE_SMOOTH);

        JLabel background = new JLabel(new ImageIcon(resized));

        setContentPane(background);
        background.setLayout(null);

        Singup();       //function call

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }



            
    public  void  Singup() {
    //  login login = new login(1);
        
            // Left Panel
        JPanel leftPanel = new JPanel();
        leftPanel.setBounds(360, 70, 410, 450);
        leftPanel.setLayout(null); 
        leftPanel.setBackground(new Color(0, 180, 120));

        // Image label
        JLabel imageLabel = new JLabel();
        imageLabel.setBounds(10, 10, 390, 429);
        leftPanel.add(imageLabel);

        // Images
        String[] images = {
            "C:\\Users\\DELL\\OneDrive\\Pictures\\movieposter\\img11.jpg",
            "C:\\Users\\DELL\\OneDrive\\Pictures\\movieposter\\\\img12.jpg",
            "C:\\Users\\DELL\\OneDrive\\Pictures\\movieposter\\\\img5.jpg",
            "C:\\Users\\DELL\\OneDrive\\Pictures\\movieposter\\\\img6.jpg",
            "C:\\Users\\DELL\\OneDrive\\Pictures\\movieposter\\\\img9.jpg",
            "C:\\Users\\DELL\\OneDrive\\Pictures\\movieposter\\\\img10.jpg",
        };

        int[] index = {0};

        Timer timer = new Timer(4000, e -> {

            ImageIcon icon = new ImageIcon(images[index[0]]);

            Image img = icon.getImage().getScaledInstance(
                    imageLabel.getWidth(),
                    imageLabel.getHeight(),
                    Image.SCALE_SMOOTH
            );

            imageLabel.setIcon(new ImageIcon(img));

            index[0]++;

            if (index[0] >= images.length) {
                index[0] = 0;
            }
        });

        timer.start();
        add(leftPanel);




    // Right Panel
            JPanel Rightpane = new JPanel();
                Rightpane.setBounds(770, 70, 410, 450);
                Rightpane.setLayout(null);
                Rightpane.setBackground(Color.WHITE);

                JButton Backbtn=new JButton("Back");
                Backbtn.setForeground(Color.black);
                Backbtn.setFont(new Font("Arial", Font.BOLD, 10));
                Backbtn.setBackground(new Color(0, 150, 100));
                Backbtn.setBounds(5,10,60,30);
                Backbtn.setFocusPainted(false);
                Backbtn.setBorderPainted(false);
                Rightpane.add(Backbtn);

                Backbtn.addActionListener(e ->{
                    Dashboard obj=new Dashboard();
                });


    // Login Title
                JLabel Login = new JLabel("Sing UP!");
                Login.setForeground(Color.BLACK);
                Login.setFont(new Font("Arial", Font.BOLD, 30));
                Login.setBounds(120, 10, 250, 40);
                Rightpane.add(Login);
                // Name
                JTextField txtName = new JTextField();
                txtName.setBounds(60, 70, 280, 40);
                txtName.setBorder(BorderFactory.createTitledBorder("Full Name"));
                Rightpane.add(txtName);

                // Email
                JTextField txtEmail = new JTextField();
                txtEmail.setBounds(60, 120, 280, 40);
                txtEmail.setBorder(BorderFactory.createTitledBorder("Email"));
                Rightpane.add(txtEmail);

                // Phone Number
                JTextField txtPhone = new JTextField();
                txtPhone.setBounds(60, 170, 280, 40);
                txtPhone.setBorder(BorderFactory.createTitledBorder("Phone Number"));
                Rightpane.add(txtPhone);

                // Username
                JTextField txtUsername = new JTextField();
                txtUsername.setBounds(60, 220, 280, 40);
                txtUsername.setBorder(BorderFactory.createTitledBorder("Username"));
                Rightpane.add(txtUsername);

                // Password
                JPasswordField txtPassword = new JPasswordField();
                txtPassword.setBounds(60, 270, 280, 40);
                txtPassword.setBorder(BorderFactory.createTitledBorder("Password"));
                Rightpane.add(txtPassword);
                // Sign Up Button
            JButton signUpButton = new JButton("SIGN UP");
                signUpButton.setBounds(110, 340, 180, 45);
                signUpButton.setBackground(new Color(0, 150, 100));
                signUpButton.setForeground(Color.WHITE);
                signUpButton.setFont(new Font("Arial", Font.BOLD, 14));
                signUpButton.setFocusPainted(false);
                signUpButton.setBorderPainted(false);
                signUpButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
                Rightpane.add(signUpButton);

                

                    signUpButton.addActionListener(e -> {
                                
                                String username = txtUsername.getText();
                                String password = new String(txtPassword.getPassword());
                                String name = txtName.getText();
                                String email = txtEmail.getText();
                                    String phone = txtPhone.getText();
                                if(username.isEmpty() || password.isEmpty() || name.isEmpty() || email.isEmpty() || phone.isEmpty()) {

                                    JOptionPane.showMessageDialog(this, "Please fill in all fields.");

                                } else {

                                    // UserDAO call

                                    UserDAO dao = new UserDAO();

                                        UserModel user = new UserModel();

                                        user.setFullName(name);
                                        user.setEmail(email);
                                        user.setUsername(username);
                                        user.setPassword(password);
                                        user.setRole("Customer");
                                        user.setPhone(phone);

                                        dao.addUser(user);
                                        new LoginView();

                                }
                            });

    // Add Right Panel
    add(Rightpane);

        



    }

    public static void main(String[] args){
        SignupView obj=new SignupView();
    }

}

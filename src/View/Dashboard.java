package View;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
public class Dashboard extends JFrame {

            private JPanel rightPanel;

        public Dashboard() {
            setSize(1600, 1000);
            setTitle("DashBoard");
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setBackground(Color.WHITE);
            setLayout(null);
            showDashboard();
            setVisible(true);
            

        }
public void showDashboard() {

    setTitle("Movie Ticket Booking System");
    setLayout(null);

    // ================= TOP PANEL =================

    //Top panel for dashboard
            JPanel topPanel = new JPanel();
            setTitle("Dashboard");
            topPanel.setBounds(0, 0, 1600, 70);
            topPanel.setBackground(Color.black);
            topPanel.setVisible(true);
            Toppanel(topPanel);
            add(topPanel);

    // ================= LEFT PANEL =================

    JPanel leftPanel = new JPanel();
    leftPanel.setBounds(0, 60, 320, 850);
    leftPanel.setBackground(Color.black);
    leftPanel.setLayout(null);
    add(leftPanel);

    // Profile Image

    ImageIcon icon = new ImageIcon("C:\\Users\\DELL\\OneDrive\\Pictures\\movieposter\\User3.jpeg");
    Image img = icon.getImage().getScaledInstance(130, 130, Image.SCALE_SMOOTH);

    JLabel profilePic = new JLabel(new ImageIcon(img));
    profilePic.setBounds(95, 20, 130, 130);
    leftPanel.add(profilePic);

    // Name

    JLabel name = new JLabel("Hemant Singh");
    name.setBounds(0, 165, 320, 30);
    name.setHorizontalAlignment(SwingConstants.CENTER);
    name.setForeground(Color.WHITE);
    name.setFont(new Font("Segoe UI", Font.BOLD, 22));
    leftPanel.add(name);

    JLabel role = new JLabel("Customer");
    role.setBounds(0, 195, 320, 20);
    role.setHorizontalAlignment(SwingConstants.CENTER);
    role.setForeground(Color.LIGHT_GRAY);
    role.setFont(new Font("Segoe UI", Font.PLAIN, 14));
    leftPanel.add(role);

    JSeparator sp = new JSeparator();
    sp.setBounds(20, 235, 280, 2);
    leftPanel.add(sp);

    Font menuFont = new Font("Segoe UI", Font.BOLD, 16);

    JButton homeBtn = new JButton("  HOME");
    homeBtn.setBounds(10, 260, 300, 45);

    JButton movieBtn = new JButton("  MOVIES");
    movieBtn.setBounds(10, 315, 300, 45);

    JButton bookingBtn = new JButton("  TICKET BOOKING");
    bookingBtn.setBounds(10, 370, 300, 45);

    JButton receiptBtn = new JButton("  PAYMENT RECEIPT");
    receiptBtn.setBounds(10, 425, 300, 45);

    JButton historyBtn = new JButton("  BOOKING HISTORY");
    historyBtn.setBounds(10, 480, 300, 45);

    JButton profileBtn = new JButton("  PROFILE");
    profileBtn.setBounds(10, 535, 300, 45);

    JButton logoutBtn = new JButton("  LOGOUT");
    logoutBtn.setBounds(10, 720, 300, 45);

    JButton buttons[] = {homeBtn,movieBtn,bookingBtn,receiptBtn,historyBtn,profileBtn,logoutBtn};

    for (JButton btn : buttons) {

            btn.setFont(menuFont);
            btn.setHorizontalAlignment(SwingConstants.LEFT);

            btn.setBackground(new Color(35, 35, 35));
            btn.setForeground(Color.WHITE);

            btn.setFocusPainted(false);
            btn.setBorderPainted(false);
            btn.setCursor(new Cursor(Cursor.HAND_CURSOR));

            btn.addMouseListener(new java.awt.event.MouseAdapter() {

                public void mouseEntered(java.awt.event.MouseEvent evt) {
                    btn.setBackground(new Color(255, 140, 0));
                }

                public void mouseExited(java.awt.event.MouseEvent evt) {
                    btn.setBackground(new Color(35, 35, 35));
                }
            });

            leftPanel.add(btn);
        }

         





    logoutBtn.setBackground(new Color(180, 0, 0));

    // Home Button jispar Dashboard Panel chelga
    homeBtn.addActionListener(new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {

        rightPanel.removeAll();

        JScrollPane scrollPane =
                new JScrollPane(new CustomerHome());

        scrollPane.setVerticalScrollBarPolicy(
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

        rightPanel.add(scrollPane, BorderLayout.CENTER);

        rightPanel.revalidate();
        rightPanel.repaint();
    }
});     


    // Booking Button jispar Ticket Booking Panel open hoga
bookingBtn.addActionListener(e -> {

    rightPanel.removeAll();
    rightPanel.setLayout(new BorderLayout());

    rightPanel.add(
        new TicketBookingView(-1, rightPanel),
        BorderLayout.CENTER
    );

    rightPanel.revalidate();
    rightPanel.repaint();
});
    

    // Movie Button jispar movie Panel chelga
    movieBtn.addActionListener(new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {

        rightPanel.removeAll();

        JScrollPane scrollPane =
                new JScrollPane(new MoviePanel(rightPanel));

        scrollPane.setVerticalScrollBarPolicy(
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

        rightPanel.add(scrollPane, BorderLayout.CENTER);

        rightPanel.revalidate();
        rightPanel.repaint();
    }
});

    // ================= RIGHT PANEL =================
           
    rightPanel = new JPanel();
    RightPanel(rightPanel);

    setVisible(true);

    
}

 public void RightPanel(JPanel rightPanel) {

    rightPanel.setBounds(320, 60, 1280, 850);
    rightPanel.setLayout(new BorderLayout());

    CustomerHome home = new CustomerHome();

    JScrollPane scrollPane = new JScrollPane(home);

    scrollPane.setVerticalScrollBarPolicy(
            JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

    scrollPane.setHorizontalScrollBarPolicy(
            JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

    rightPanel.add(scrollPane);

    add(rightPanel);
}  







public void Toppanel(JPanel topPanel) {

            // Top panel Label of Dashboard

        JLabel dashboardLabel = new JLabel("Dashboard");
        dashboardLabel.setForeground(Color.WHITE);
        dashboardLabel.setFont(new Font("Arial", Font.BOLD, 20));
        dashboardLabel.setBounds(20, 10, 200, 30);
        topPanel.setLayout(null);

                    

        topPanel.add(dashboardLabel);

            // Login button for dashboard
        JButton loginButton = new JButton("LOGIN");
        loginButton.setBounds(1400, 20, 100, 30);
        loginButton.setBackground(new Color(0, 150, 100));
        loginButton.setForeground(Color.WHITE);
        loginButton.setFont(new Font("Arial", Font.BOLD, 14));
        
        loginButton.setFocusPainted(false);
        loginButton.setBorderPainted(false);

                loginButton.addActionListener(e -> {
                   // dispose();               // Current dashboard window Close
                    new LoginView();
                });

        topPanel.add(loginButton);
       }

    public static void main(String[] args) {
        new Dashboard();
    }
}

//new MoviePanel()
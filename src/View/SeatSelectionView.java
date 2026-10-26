package View;
import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.border.TitledBorder;

public class SeatSelectionView extends JFrame {

    // Swing Components
    private JPanel seatPanel;
    private JLabel regularCountLbl, premiumCountLbl, goldCountLbl, totalLbl;
    private JLabel billRegularLbl, billPremiumLbl, billGoldLbl;
    private JComboBox<String> movieCombo, languageCombo, showCombo, screenCombo;

    // Counter and Pricing Variables
    private int regularCount = 0;
    private int premiumCount = 0;
    private int goldCount = 0;

    private final double REGULAR_PRICE = 10.0;
    private final double PREMIUM_PRICE = 12.50;
    private final double GOLD_PRICE = 40.0;

    // Color Palette matching your UI
    private final Color regularColor = new Color(76, 175, 80);   // Green
    private final Color premiumColor = new Color(33, 150, 243);  // Blue
    private final Color goldColor = new Color(255, 193, 7);     // Yellow
    private final Color bookedColor = new Color(33, 33, 33);     // Dark Black

    private ArrayList<JButton> allSeatsList = new ArrayList<>();

    public SeatSelectionView() {
        setTitle("Movie Theater Seat Booking System");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1200, 750);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // 1. TOP PANEL (Header + Comboboxes)
        createTopPanel();

        // 2. MAIN CENTER AREA (Seats Panel + Live Billing Panel)
        createMainCenterArea();

        // 3. BOTTOM PANEL (Legend + Save Button)
        createBottomPanel();
    }

    private void createTopPanel() {
        JPanel topMasterPanel = new JPanel(new BorderLayout());
        
        // Header Banner
        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(Color.BLACK);
        JLabel headerTitle = new JLabel("MOVIE THEATER SEAT BOOKING", JLabel.CENTER);
        headerTitle.setForeground(Color.WHITE);
        headerTitle.setFont(new Font("Arial", Font.BOLD, 18));
        headerPanel.add(headerTitle);
        topMasterPanel.add(headerPanel, BorderLayout.NORTH);

        // Dropdowns Panel
        JPanel dropdownPanel = new JPanel(new GridLayout(1, 4, 15, 0));
        dropdownPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        dropdownPanel.setBackground(Color.WHITE);

        // Movie Box
        JPanel p1 = new JPanel(new BorderLayout());
        p1.setBackground(Color.WHITE);
        p1.add(new JLabel("Movie"), BorderLayout.NORTH);
        movieCombo = new JComboBox<>(new String[]{"Avengers", "Spiderman", "Batman"});
        p1.add(movieCombo, BorderLayout.CENTER);

        // Language Box
        JPanel p2 = new JPanel(new BorderLayout());
        p2.setBackground(Color.WHITE);
        p2.add(new JLabel("Language"), BorderLayout.NORTH);
        languageCombo = new JComboBox<>(new String[]{"Hindi", "English", "Tamil"});
        p2.add(languageCombo, BorderLayout.CENTER);

        // Show Box (with Live Date & Time format)
        JPanel p3 = new JPanel(new BorderLayout());
        p3.setBackground(Color.WHITE);
        p3.add(new JLabel("Show"), BorderLayout.NORTH);
        showCombo = new JComboBox<>(new String[]{"Date: Oct 26, 2024 - Time: 10:00 AM", "Date: Oct 26, 2024 - Time: 01:00 PM"});
        p3.add(showCombo, BorderLayout.CENTER);

        // Screen Box
        JPanel p4 = new JPanel(new BorderLayout());
        p4.setBackground(Color.WHITE);
        p4.add(new JLabel("Screen"), BorderLayout.NORTH);
        screenCombo = new JComboBox<>(new String[]{"Screen 1 (2D)", "Screen 2 (3D)"});
        p4.add(screenCombo, BorderLayout.CENTER);

        dropdownPanel.add(p1);
        dropdownPanel.add(p2);
        dropdownPanel.add(p3);
        dropdownPanel.add(p4);

        topMasterPanel.add(dropdownPanel, BorderLayout.SOUTH);
        add(topMasterPanel, BorderLayout.NORTH);
    }

    private void createMainCenterArea() {
        JPanel mainSplitPanel = new JPanel(new BorderLayout());
        mainSplitPanel.setBackground(Color.WHITE);

        // --- SEAT SELECTION PANEL (Left 80%) ---
        seatPanel = new JPanel();
        seatPanel.setLayout(new BoxLayout(seatPanel, BoxLayout.Y_AXIS));
        seatPanel.setBackground(new Color(245, 245, 245));
        
        TitledBorder seatBorder = BorderFactory.createTitledBorder("Seat Selection Panel");
        seatBorder.setTitleFont(new Font("Arial", Font.BOLD, 12));
        seatPanel.setBorder(seatBorder);

        generateTheaterSeats(); // Row-wise seats design generator

        JScrollPane scrollPane = new JScrollPane(seatPanel);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        mainSplitPanel.add(scrollPane, BorderLayout.CENTER);

        // --- LIVE BILLING PANEL (Right 20%) ---
        JPanel billPanel = new JPanel();
        billPanel.setPreferredSize(new Dimension(280, 500));
        billPanel.setLayout(new BoxLayout(billPanel, BoxLayout.Y_AXIS));
        billPanel.setBackground(new Color(240, 242, 245));
        billPanel.setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 15));

        // Header Inner
        JPanel billingHeader = new JPanel();
        billingHeader.setBackground(Color.BLACK);
        JLabel billingTitle = new JLabel("LIVE BILLING PANEL", JLabel.CENTER);
        billingTitle.setForeground(Color.WHITE);
        billingTitle.setFont(new Font("Arial", Font.BOLD, 14));
        billingHeader.add(billingTitle);
        billingHeader.setMaximumSize(new Dimension(300, 35));
        billPanel.add(billingHeader);

        billPanel.add(Box.createVerticalStrut(15));

        // Summary Section
        JLabel summaryHead = new JLabel("Summary");
        summaryHead.setFont(new Font("Arial", Font.BOLD, 13));
        billPanel.add(summaryHead);
        billPanel.add(Box.createVerticalStrut(5));

        regularCountLbl = new JLabel("Regular Seats Selected: 0");
        premiumCountLbl = new JLabel("Premium Seats Selected: 0");
        goldCountLbl = new JLabel("Gold Seats Selected: 0");
        
        billPanel.add(regularCountLbl);
        billPanel.add(Box.createVerticalStrut(5));
        billPanel.add(premiumCountLbl);
        billPanel.add(Box.createVerticalStrut(5));
        billPanel.add(goldCountLbl);

        billPanel.add(Box.createVerticalStrut(15));
        billPanel.add(new JSeparator());
        billPanel.add(Box.createVerticalStrut(10));

        // Bill Dynamic Section
        JLabel billHead = new JLabel("BILL");
        billHead.setFont(new Font("Arial", Font.BOLD, 13));
        billPanel.add(billHead);
        billPanel.add(Box.createVerticalStrut(5));

        billRegularLbl = new JLabel("Regular = 0 (0 * " + REGULAR_PRICE + ")");
        billPremiumLbl = new JLabel("Premium = 0 (0 * " + PREMIUM_PRICE + ")");
        billGoldLbl = new JLabel("Gold = 0 (0 * " + GOLD_PRICE + ")");

        billPanel.add(billRegularLbl);
        billPanel.add(Box.createVerticalStrut(5));
        billPanel.add(billPremiumLbl);
        billPanel.add(Box.createVerticalStrut(5));
        billPanel.add(billGoldLbl);

        billPanel.add(Box.createVerticalStrut(15));
        billPanel.add(new JSeparator());
        billPanel.add(Box.createVerticalStrut(15));

        // Total Text Box
        totalLbl = new JLabel("TOTAL = 0.00");
        totalLbl.setFont(new Font("Arial", Font.BOLD, 18));
        billPanel.add(totalLbl);

        mainSplitPanel.add(billPanel, BorderLayout.EAST);
        add(mainSplitPanel, BorderLayout.CENTER);
    }

    private void generateTheaterSeats() {
        seatPanel.removeAll();
        allSeatsList.clear();

        // 1. Regular Seats Group (Top Section)
        seatPanel.add(Box.createVerticalStrut(10));
        for (int row = 1; row <= 6; row++) {
            JPanel rowPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 6, 4));
            rowPanel.setOpaque(false);
            for (int col = 1; col <= 16; col++) {
                // Kuchh seats ko sample booked dikhane ke liye
                boolean isBooked = (row == 2 && col == 5) || (row == 4 && col == 8);
                createSeatButton("R" + row + "-" + col, "Regular", regularColor, rowPanel, isBooked);
            }
            seatPanel.add(rowPanel);
        }

        // Gap/Aisle
        seatPanel.add(Box.createVerticalStrut(15));

        // 2. Premium Seats Group (Middle Section)
        for (int row = 1; row <= 3; row++) {
            JPanel rowPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 6, 4));
            rowPanel.setOpaque(false);
            for (int col = 1; col <= 10; col++) {
                createSeatButton("P" + row + "-" + col, "Premium", premiumColor, rowPanel, false);
            }
            seatPanel.add(rowPanel);
        }

        // Gap/Aisle
        seatPanel.add(Box.createVerticalStrut(15));

        // 3. Gold Seats Group (Bottom Section)
        for (int row = 1; row <= 3; row++) {
            JPanel rowPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 6, 4));
            rowPanel.setOpaque(false);
            for (int col = 1; col <= 6; col++) {
                createSeatButton("G" + row + "-" + col, "Gold", goldColor, rowPanel, false);
            }
            seatPanel.add(rowPanel);
        }
    }

    private void createSeatButton(String seatNo, String type, Color baseColor, JPanel targetRow, boolean isBooked) {
        JButton btn = new JButton(seatNo.split("-")[1]); // Display only seat number
        btn.setPreferredSize(new Dimension(42, 32));
        btn.setFont(new Font("Arial", Font.PLAIN, 10));
        btn.setFocusPainted(false);

        if (isBooked) {
            btn.setBackground(bookedColor);
            btn.setForeground(Color.WHITE);
            btn.putClientProperty("status", "BOOKED");
        } else {
            btn.setBackground(baseColor);
            btn.setForeground(Color.WHITE);
            btn.putClientProperty("status", "AVAILABLE");
        }
        
        btn.putClientProperty("type", type);
        btn.putClientProperty("seatNo", seatNo);

        // Selection Action Listener
        btn.addActionListener(e -> {
            String status = (String) btn.getClientProperty("status");
            if ("BOOKED".equals(status)) {
                JOptionPane.showMessageDialog(this, "This seat is already booked!", "Error", JOptionPane.WARNING_MESSAGE);
                return;
            }

            Boolean isSelected = (Boolean) btn.getClientProperty("selected");
            if (isSelected == null) isSelected = false;

            if (!isSelected) {
                // Select Seat
                btn.putClientProperty("selected", true);
                btn.setBorder(BorderFactory.createLineBorder(Color.YELLOW, 3)); // Yellow Border on selection
                modifyCount(type, true);
            } else {
                // Deselect Seat
                btn.putClientProperty("selected", false);
                btn.setBorder(UIManager.getBorder("Button.border")); // Reset border
                modifyCount(type, false);
            }
            calculateLiveBill();
        });

        allSeatsList.add(btn);
        targetRow.add(btn);
    }

    private void modifyCount(String type, boolean increase) {
        int val = increase ? 1 : -1;
        if (type.equals("Regular")) regularCount += val;
        else if (type.equals("Premium")) premiumCount += val;
        else if (type.equals("Gold")) goldCount += val;
    }

    private void calculateLiveBill() {
        // Update Labels text
        regularCountLbl.setText("Regular Seats Selected: " + regularCount);
        premiumCountLbl.setText("Premium Seats Selected: " + premiumCount);
        goldCountLbl.setText("Gold Seats Selected: " + goldCount);

        double regularTotal = regularCount * REGULAR_PRICE;
        double premiumTotal = premiumCount * PREMIUM_PRICE;
        double goldTotal = goldCount * GOLD_PRICE;
        double overallTotal = regularTotal + premiumTotal + goldTotal;

        billRegularLbl.setText(String.format("Regular = %.2f (%d * %.2f)", regularTotal, regularCount, REGULAR_PRICE));
        billPremiumLbl.setText(String.format("Premium = %.2f (%d * %.2f)", premiumTotal, premiumCount, PREMIUM_PRICE));
        billGoldLbl.setText(String.format("Gold = %.2f (%d * %.2f)", goldTotal, goldCount, GOLD_PRICE));

        totalLbl.setText(String.format("TOTAL = %.2f", overallTotal));
    }

    private void createBottomPanel() {
        JPanel bottomMaster = new JPanel(new BorderLayout());
        bottomMaster.setBackground(Color.WHITE);
        bottomMaster.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Legend Indicators (Left side)
        JPanel legendPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 15, 0));
        legendPanel.setBackground(Color.WHITE);

        legendPanel.add(createIndicatorBox("Regular @ 10", regularColor));
        legendPanel.add(createIndicatorBox("Premium @ 12.50", premiumColor));
        legendPanel.add(createIndicatorBox("Gold @ 40", goldColor));
        legendPanel.add(createIndicatorBox("Booked", bookedColor));

        bottomMaster.add(legendPanel, BorderLayout.WEST);

        // SAVE BOOKING BUTTON (Full Bottom Width Style)
        JButton saveBtn = new JButton("SAVE BOOKING");
        saveBtn.setBackground(new Color(20, 24, 30));
        saveBtn.setForeground(Color.WHITE);
        saveBtn.setFont(new Font("Arial", Font.BOLD, 16));
        saveBtn.setFocusPainted(false);
        saveBtn.setPreferredSize(new Dimension(200, 45));

        saveBtn.addActionListener(e -> {
            int totalSelected = regularCount + premiumCount + goldCount;
            if(totalSelected == 0) {
                JOptionPane.showMessageDialog(this, "Please select at least one seat to book!", "No Seats Selected", JOptionPane.INFORMATION_MESSAGE);
                return;
            }
            
            // Logic on Successful Booking Mock
            JOptionPane.showMessageDialog(this, "Booking Saved Successfully!\nTotal Seats: " + totalSelected, "Success", JOptionPane.INFORMATION_MESSAGE);
            
            // Selected seats ko permanently booked color me set karne ke liye code
            for (JButton btn : allSeatsList) {
                Boolean isSel = (Boolean) btn.getClientProperty("selected");
                if (isSel != null && isSel) {
                    btn.putClientProperty("selected", false);
                    btn.putClientProperty("status", "BOOKED");
                    btn.setBackground(bookedColor);
                    btn.setBorder(UIManager.getBorder("Button.border"));
                }
            }
            // Reset counters
            regularCount = 0; premiumCount = 0; goldCount = 0;
            calculateLiveBill();
        });

        bottomMaster.add(saveBtn, BorderLayout.SOUTH);
        add(bottomMaster, BorderLayout.SOUTH);
    }

    private JPanel createIndicatorBox(String name, Color color) {
        JPanel p = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 0));
        p.setBackground(Color.WHITE);
        JPanel colorBox = new JPanel();
        colorBox.setPreferredSize(new Dimension(15, 15));
        colorBox.setBackground(color);
        JLabel lbl = new JLabel(name);
        lbl.setFont(new Font("Arial", Font.PLAIN, 12));
        p.add(colorBox);
        p.add(lbl);
        return p;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new SeatSelectionView().setVisible(true);
        });
    }
}
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.JComboBox;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JTextField;
import javax.swing.JOptionPane; // Import JOptionPane
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class GUI_RentCar {

    private JFrame frame;
    private JTextField textField;

    // JDBC Database URL
    private static final String DB_URL = "jdbc:mysql://localhost:3306/car_rental";
    // Database credentials
    private static final String USER = "root";
    private static final String PASS = "";

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    GUI_RentCar window = new GUI_RentCar();
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public GUI_RentCar() {
        initialize();
    }

    private void initialize() {
        frame = new JFrame();
        frame.setBounds(100, 100, 900, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        JLabel lblNewLabel = new JLabel("Rent A Car");
        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 24));
        lblNewLabel.setBounds(47, 23, 746, 49);
        frame.getContentPane().add(lblNewLabel);

        JLabel lblNewLabel_1 = new JLabel("SELECT CAR");
        lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_1.setFont(new Font("Tahoma", Font.ITALIC, 18));
        lblNewLabel_1.setBounds(35, 82, 166, 36);
        frame.getContentPane().add(lblNewLabel_1);

        JLabel lblNewLabel_1_1 = new JLabel("SELECT CUSTOMER");
        lblNewLabel_1_1.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_1_1.setFont(new Font("Tahoma", Font.ITALIC, 18));
        lblNewLabel_1_1.setBounds(35, 169, 166, 36);
        frame.getContentPane().add(lblNewLabel_1_1);

        JLabel lblNewLabel_1_2 = new JLabel("DAYS");
        lblNewLabel_1_2.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_1_2.setFont(new Font("Tahoma", Font.ITALIC, 18));
        lblNewLabel_1_2.setBounds(35, 265, 166, 36);
        frame.getContentPane().add(lblNewLabel_1_2);

        JButton btnNewButton = new JButton("RENT CAR");
        btnNewButton.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 20));
        btnNewButton.setBounds(47, 362, 196, 58);
        frame.getContentPane().add(btnNewButton);

        JButton btnClose = new JButton("CLOSE");
        btnClose.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 20));
        btnClose.setBounds(597, 362, 196, 58);
        frame.getContentPane().add(btnClose);

        JComboBox<String> comboBox = new JComboBox<>();
        comboBox.setBounds(283, 93, 237, 25);
        frame.getContentPane().add(comboBox);

        JComboBox<String> comboBox_1 = new JComboBox<>();
        comboBox_1.setBounds(283, 180, 237, 25);
        frame.getContentPane().add(comboBox_1);

        textField = new JTextField();
        textField.setBounds(283, 259, 247, 42);
        frame.getContentPane().add(textField);
        textField.setColumns(10);

        btnClose.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                GUI_CarRental carRentalWindow = new GUI_CarRental();
                carRentalWindow.getFrame().setVisible(true);
                frame.dispose();
            }
        });

        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Retrieve selected values
                String selectedCarModel = comboBox.getSelectedItem().toString();
                String selectedCustomerName = comboBox_1.getSelectedItem().toString();
                int rentalDays = Integer.parseInt(textField.getText());

                try {
                    Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);

                    // Retrieve car_id based on selected car model
                    String carIdQuery = "SELECT car_id FROM cars WHERE model = ?";
                    try (PreparedStatement carIdStmt = conn.prepareStatement(carIdQuery)) {
                        carIdStmt.setString(1, selectedCarModel);
                        ResultSet carIdRs = carIdStmt.executeQuery();
                        if (carIdRs.next()) {
                            int carId = carIdRs.getInt("car_id");

                            // Retrieve customer_id based on selected customer name
                            String customerIdQuery = "SELECT customer_id FROM customers WHERE name = ?";
                            try (PreparedStatement customerIdStmt = conn.prepareStatement(customerIdQuery)) {
                                customerIdStmt.setString(1, selectedCustomerName);
                                ResultSet customerIdRs = customerIdStmt.executeQuery();
                                if (customerIdRs.next()) {
                                    int customerId = customerIdRs.getInt("customer_id");

                                    // Insert a new record into the rentals table
                                    String insertQuery = "INSERT INTO rentals (customer_id, car_id, days) VALUES (?, ?, ?)";
                                    try (PreparedStatement insertStmt = conn.prepareStatement(insertQuery)) {
                                        insertStmt.setInt(1, customerId);
                                        insertStmt.setInt(2, carId);
                                        insertStmt.setInt(3, rentalDays);

                                        // Execute the insertion
                                        insertStmt.executeUpdate();
                                    }

                                    // Show success message
                                    JOptionPane.showMessageDialog(frame, "Car Rented Successfully");
                                }
                            }
                        }
                    }

                    conn.close();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

        // Populate carComboBox
        try {
            Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
            String query = "SELECT c.model FROM cars c LEFT JOIN rentals r ON c.car_id = r.car_id WHERE r.car_id IS NULL";
            try (PreparedStatement stmt = conn.prepareStatement(query);
                 ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    comboBox.addItem(rs.getString("model"));
                }
            }
            conn.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        // Populate customerComboBox
        try {
            Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
            String query = "SELECT name FROM customers";
            try (PreparedStatement stmt = conn.prepareStatement(query);
                 ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    comboBox_1.addItem(rs.getString("name"));
                }
            }
            conn.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    // Getter for the frame
    public JFrame getFrame() {
        return frame;
    }
}

import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI_AddCar {

    private JFrame frame;
    private JTextField textField;
    private JTextField textField_1;
    private JTextField textField_2;
    private JTextField textField_3;

    // Add these class-level variables
    private JComboBox<String> comboBox;
    private PreparedStatement categoryInsertStmt;

    // JDBC Database URL
    private static final String DB_URL = "jdbc:mysql://localhost:3306/car_rental";
    // Database credentials
    private static final String USER = "root";
    private static final String PASS = "";

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    GUI_AddCar window = new GUI_AddCar();
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public GUI_AddCar() {
        initialize();
    }

    private void initialize() {
        frame = new JFrame();
        frame.setBounds(100, 100, 876, 461);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        JLabel lblNewLabel = new JLabel("ADD A NEW CAR");
        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 22));
        lblNewLabel.setBounds(191, 22, 441, 37);
        frame.getContentPane().add(lblNewLabel);

        JLabel lblNewLabel_1 = new JLabel("CATEGORY");
        lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_1.setFont(new Font("Tahoma", Font.ITALIC, 18));
        lblNewLabel_1.setBounds(52, 71, 144, 43);
        frame.getContentPane().add(lblNewLabel_1);

        String[] carCategories = {"SMALL", "BIG", "ECONOMY", "SUV"};
        comboBox = new JComboBox<>(carCategories);
        comboBox.setBounds(253, 85, 133, 21);
        frame.getContentPane().add(comboBox);

        JLabel lblNewLabel_2 = new JLabel("MODEL");
        lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_2.setFont(new Font("Tahoma", Font.ITALIC, 18));
        lblNewLabel_2.setBounds(62, 124, 96, 31);
        frame.getContentPane().add(lblNewLabel_2);

        textField = new JTextField();
        textField.setBounds(253, 133, 133, 19);
        frame.getContentPane().add(textField);
        textField.setColumns(10);

        JLabel lblNewLabel_3 = new JLabel("CUBIC CAPACITY");
        lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_3.setFont(new Font("Tahoma", Font.ITALIC, 18));
        lblNewLabel_3.setBounds(52, 182, 154, 31);
        frame.getContentPane().add(lblNewLabel_3);

        textField_1 = new JTextField();
        textField_1.setBounds(253, 191, 133, 19);
        frame.getContentPane().add(textField_1);
        textField_1.setColumns(10);

        JLabel lblNewLabel_4 = new JLabel("SEATS");
        lblNewLabel_4.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_4.setFont(new Font("Tahoma", Font.ITALIC, 18));
        lblNewLabel_4.setBounds(52, 241, 119, 31);
        frame.getContentPane().add(lblNewLabel_4);

        textField_2 = new JTextField();
        textField_2.setBounds(253, 250, 133, 21);
        frame.getContentPane().add(textField_2);
        textField_2.setColumns(10);

        JLabel lblNewLabel_5 = new JLabel("DAILY COST");
        lblNewLabel_5.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_5.setFont(new Font("Tahoma", Font.ITALIC, 18));
        lblNewLabel_5.setBounds(62, 308, 134, 31);
        frame.getContentPane().add(lblNewLabel_5);

        textField_3 = new JTextField();
        textField_3.setBounds(253, 317, 133, 21);
        frame.getContentPane().add(textField_3);
        textField_3.setColumns(10);

        JButton btnNewButton = new JButton("ADD CAR");
        btnNewButton.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 20));
        btnNewButton.setBounds(253, 366, 169, 37);
        frame.getContentPane().add(btnNewButton);

        JButton btnNewButton_1 = new JButton("CLOSE");
        btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 20));
        btnNewButton_1.setBounds(475, 366, 169, 37);
        frame.getContentPane().add(btnNewButton_1);

        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addCarToDatabase();
            }
        });

        btnNewButton_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                GUI_CarRental carRentalWindow = new GUI_CarRental();
                carRentalWindow.getFrame().setVisible(true);
                frame.dispose();
            }
        });
    }

    private void addCarToDatabase() {
        try {
            // Open a connection
            Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);

            // Input validation
            String cubicCapacityStr = textField_1.getText();
            String seatsStr = textField_2.getText();
            String dailyCostStr = textField_3.getText();

            if (!isNumeric(cubicCapacityStr) || !isNumeric(seatsStr) || !isNumeric(dailyCostStr)) {
                System.out.println("Invalid numeric input. Please enter valid numeric values.");
                return;
            }

            int cubicCapacity = Integer.parseInt(cubicCapacityStr);
            int seats = Integer.parseInt(seatsStr);
            double dailyCost = Double.parseDouble(dailyCostStr);

            // Insert into car_categories table
            String category = (String) comboBox.getSelectedItem();
            String categoryInsertQuery = "INSERT INTO car_categories (category_name) VALUES (?)";
            categoryInsertStmt = conn.prepareStatement(categoryInsertQuery,
                    PreparedStatement.RETURN_GENERATED_KEYS);
            categoryInsertStmt.setString(1, category);
            int affectedRows = categoryInsertStmt.executeUpdate();
            if (affectedRows == 0) {
                throw new RuntimeException("Inserting category failed.");
            }

            // Retrieve the generated category ID
            int categoryId;
            try (ResultSet generatedKeys = categoryInsertStmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    categoryId = generatedKeys.getInt(1);
                } else {
                    throw new RuntimeException("Inserting category failed, no ID obtained.");
                }
            }

            // Insert into cars table
            String model = textField.getText();

            String carInsertQuery = "INSERT INTO cars (category_id, model, cubic_capacity, seats, daily_cost) VALUES (?, ?, ?, ?, ?)";
            try (PreparedStatement carInsertStmt = conn.prepareStatement(carInsertQuery)) {
                carInsertStmt.setInt(1, categoryId);
                carInsertStmt.setString(2, model);
                carInsertStmt.setInt(3, cubicCapacity);
                carInsertStmt.setInt(4, seats);
                carInsertStmt.setDouble(5, dailyCost);

                affectedRows = carInsertStmt.executeUpdate();
                if (affectedRows == 0) {
                    throw new RuntimeException("Inserting car failed.");
                }

                // Display success message
                JOptionPane.showMessageDialog(frame, "Car Added Successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
            }

            conn.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private boolean isNumeric(String str) {
        return str.matches("-?\\d+(\\.\\d+)?"); // Check if the string is a number
    }

    public JFrame getFrame() {
        return frame;
    }
}

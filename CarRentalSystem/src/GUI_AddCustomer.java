import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JComboBox;

public class GUI_AddCustomer {

    private JFrame frame;
    private JTextField textField;
    private JTextField textField_1;
    private JTextField textField_3;
    private JTextField textField_4;
    private JTextField textField_5;
    private JLabel successLabel;
    private JComboBox<String> comboBox;

    // JDBC Database URL
    private static final String DB_URL = "jdbc:mysql://localhost:3306/car_rental";
    // Database credentials
    private static final String USER = "root";
    private static final String PASS = "";

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    GUI_AddCustomer window = new GUI_AddCustomer();
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public GUI_AddCustomer() {
        initialize();
    }

    private void initialize() {
        frame = new JFrame();
        frame.setBounds(100, 100, 900, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        JLabel lblNewLabel = new JLabel("ADD A NEW CUSTOMER");
        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 22));
        lblNewLabel.setBounds(84, 22, 268, 37);
        frame.getContentPane().add(lblNewLabel);

        JLabel lblNewLabel_1 = new JLabel("First Name:");
        lblNewLabel_1.setFont(new Font("Tahoma", Font.ITALIC, 18));
        lblNewLabel_1.setBounds(41, 85, 145, 27);
        frame.getContentPane().add(lblNewLabel_1);

        textField = new JTextField();
        textField.setBounds(196, 90, 179, 19);
        frame.getContentPane().add(textField);
        textField.setColumns(10);

        JLabel lblNewLabel_1_1 = new JLabel("Last Name:");
        lblNewLabel_1_1.setFont(new Font("Tahoma", Font.ITALIC, 18));
        lblNewLabel_1_1.setBounds(41, 135, 145, 27);
        frame.getContentPane().add(lblNewLabel_1_1);

        textField_1 = new JTextField();
        textField_1.setColumns(10);
        textField_1.setBounds(196, 142, 179, 19);
        frame.getContentPane().add(textField_1);

        JLabel lblNewLabel_1_3 = new JLabel("Gender:");
        lblNewLabel_1_3.setFont(new Font("Tahoma", Font.ITALIC, 18));
        lblNewLabel_1_3.setBounds(41, 185, 145, 27);
        frame.getContentPane().add(lblNewLabel_1_3);

        comboBox = new JComboBox<>(new String[]{"Male", "Female", "Other"});
        comboBox.setBounds(196, 191, 179, 21);
        frame.getContentPane().add(comboBox);

        JLabel lblNewLabel_1_3_1 = new JLabel("Home Address:");
        lblNewLabel_1_3_1.setFont(new Font("Tahoma", Font.ITALIC, 18));
        lblNewLabel_1_3_1.setBounds(41, 236, 145, 27);
        frame.getContentPane().add(lblNewLabel_1_3_1);

        textField_3 = new JTextField();
        textField_3.setColumns(10);
        textField_3.setBounds(196, 236, 179, 19);
        frame.getContentPane().add(textField_3);

        JLabel lblNewLabel_1_3_2 = new JLabel("Email:");
        lblNewLabel_1_3_2.setFont(new Font("Tahoma", Font.ITALIC, 18));
        lblNewLabel_1_3_2.setBounds(41, 286, 145, 27);
        frame.getContentPane().add(lblNewLabel_1_3_2);

        textField_4 = new JTextField();
        textField_4.setColumns(10);
        textField_4.setBounds(196, 293, 179, 19);
        frame.getContentPane().add(textField_4);

        JLabel lblNewLabel_1_3_3 = new JLabel("Phone:");
        lblNewLabel_1_3_3.setFont(new Font("Tahoma", Font.ITALIC, 18));
        lblNewLabel_1_3_3.setBounds(41, 335, 145, 27);
        frame.getContentPane().add(lblNewLabel_1_3_3);

        textField_5 = new JTextField();
        textField_5.setColumns(10);
        textField_5.setBounds(196, 342, 179, 19);
        frame.getContentPane().add(textField_5);

        JButton btnAddCustomer = new JButton("ADD CUSTOMER");
        btnAddCustomer.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 18));
        btnAddCustomer.setBounds(40, 389, 268, 37);
        frame.getContentPane().add(btnAddCustomer);

        JButton btnClose = new JButton("CLOSE");
        btnClose.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 18));
        btnClose.setBounds(528, 389, 268, 37);
        frame.getContentPane().add(btnClose);

        successLabel = new JLabel("");
        successLabel.setHorizontalAlignment(SwingConstants.CENTER);
        successLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
        successLabel.setBounds(200, 430, 500, 20);
        frame.getContentPane().add(successLabel);

        btnAddCustomer.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addCustomerToDatabase();
            }
        });

        btnClose.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                GUI_CarRental carRentalWindow = new GUI_CarRental();
                carRentalWindow.getFrame().setVisible(true);
                frame.dispose();
            }
        });
    }

    private void addCustomerToDatabase() {
        try {
            // Open a connection
            Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);

            // Insert into customers table
            String firstName = textField.getText();
            String lastName = textField_1.getText();
            String gender = comboBox.getSelectedItem().toString();
            String homeAddress = textField_3.getText();
            String email = textField_4.getText();
            String phone = textField_5.getText();

            String customerInsertQuery = "INSERT INTO customers (name, surname, gender, home_address, email, phone) VALUES (?, ?, ?, ?, ?, ?)";
            try (PreparedStatement customerInsertStmt = conn.prepareStatement(customerInsertQuery)) {
                customerInsertStmt.setString(1, firstName);
                customerInsertStmt.setString(2, lastName);
                customerInsertStmt.setString(3, gender);
                customerInsertStmt.setString(4, homeAddress);
                customerInsertStmt.setString(5, email);
                customerInsertStmt.setString(6, phone);

                int affectedRows = customerInsertStmt.executeUpdate();
                if (affectedRows > 0) {
                    // Display success message
                    successLabel.setText("Customer Added Successfully");
                } else {
                    throw new RuntimeException("Inserting customer failed.");
                }
            }

            conn.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public JFrame getFrame() {
        return frame;
    }
}

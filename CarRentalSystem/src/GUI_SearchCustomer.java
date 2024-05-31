import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class GUI_SearchCustomer {

    private JFrame frame;
    private JTextField textField;
    private JTextField textField_1;
    private JTextField textField_2;
    private JTextField textField_3;
    private JTextField textField_4;
    private JTextField textField_5;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    GUI_SearchCustomer window = new GUI_SearchCustomer();
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public GUI_SearchCustomer() {
        initialize();
    }

    private void initialize() {
        frame = new JFrame();
        frame.setBounds(100, 100, 900, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        JLabel lblNewLabel = new JLabel("SEARCH FOR A CUSTOMER");
        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 22));
        lblNewLabel.setBounds(182, 33, 508, 45);
        frame.getContentPane().add(lblNewLabel);

        JLabel lblNewLabel_1 = new JLabel("CUSTOMER ID:");
        lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_1.setFont(new Font("Tahoma", Font.ITALIC, 18));
        lblNewLabel_1.setBounds(26, 99, 144, 26);
        frame.getContentPane().add(lblNewLabel_1);

        textField = new JTextField();
        textField.setBounds(203, 106, 223, 19);
        frame.getContentPane().add(textField);
        textField.setColumns(10);

        JButton btnNewButton = new JButton("SEARCH");
        btnNewButton.setFont(new Font("Tahoma", Font.ITALIC, 18));
        btnNewButton.setBounds(477, 105, 193, 19);
        frame.getContentPane().add(btnNewButton);

        JLabel lblNewLabel_1_1 = new JLabel("FIRST NAME:");
        lblNewLabel_1_1.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_1_1.setFont(new Font("Tahoma", Font.ITALIC, 18));
        lblNewLabel_1_1.setBounds(26, 160, 144, 26);
        frame.getContentPane().add(lblNewLabel_1_1);

        JLabel lblNewLabel_1_2 = new JLabel("LAST NAME:");
        lblNewLabel_1_2.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_1_2.setFont(new Font("Tahoma", Font.ITALIC, 18));
        lblNewLabel_1_2.setBounds(26, 214, 144, 26);
        frame.getContentPane().add(lblNewLabel_1_2);

        JLabel lblNewLabel_1_3 = new JLabel("EMAIL:");
        lblNewLabel_1_3.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_1_3.setFont(new Font("Tahoma", Font.ITALIC, 18));
        lblNewLabel_1_3.setBounds(26, 270, 144, 26);
        frame.getContentPane().add(lblNewLabel_1_3);

        JLabel lblNewLabel_1_4 = new JLabel("PHONE:");
        lblNewLabel_1_4.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_1_4.setFont(new Font("Tahoma", Font.ITALIC, 18));
        lblNewLabel_1_4.setBounds(26, 327, 144, 26);
        frame.getContentPane().add(lblNewLabel_1_4);

        JLabel lblNewLabel_1_5 = new JLabel("CAR RENTED:");
        lblNewLabel_1_5.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_1_5.setFont(new Font("Tahoma", Font.ITALIC, 18));
        lblNewLabel_1_5.setBounds(26, 385, 144, 26);
        frame.getContentPane().add(lblNewLabel_1_5);

        textField_1 = new JTextField();
        textField_1.setColumns(10);
        textField_1.setBounds(203, 160, 223, 19);
        frame.getContentPane().add(textField_1);

        textField_2 = new JTextField();
        textField_2.setColumns(10);
        textField_2.setBounds(203, 214, 223, 19);
        frame.getContentPane().add(textField_2);

        textField_3 = new JTextField();
        textField_3.setColumns(10);
        textField_3.setBounds(203, 277, 223, 19);
        frame.getContentPane().add(textField_3);

        textField_4 = new JTextField();
        textField_4.setColumns(10);
        textField_4.setBounds(203, 327, 223, 19);
        frame.getContentPane().add(textField_4);

        textField_5 = new JTextField();
        textField_5.setColumns(10);
        textField_5.setBounds(203, 385, 223, 19);
        frame.getContentPane().add(textField_5);

        JButton btnNewButton_1 = new JButton("CLOSE");
        btnNewButton_1.setBounds(532, 321, 193, 32);
        frame.getContentPane().add(btnNewButton_1);

        JButton btnNewButton_1_1 = new JButton("DELETE CUSTOMER");
        btnNewButton_1_1.setBounds(532, 379, 193, 32);
        frame.getContentPane().add(btnNewButton_1_1);

        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String customerId = textField.getText();
                searchCustomer(customerId);
            }

            private void searchCustomer(String customerId) {
                Connection connection = null;
                PreparedStatement preparedStatement = null;
                ResultSet resultSet = null;

                try {
                    connection = DatabaseHandler.getConnection();
                    String query = "SELECT * FROM customers WHERE customer_id = ?";
                    preparedStatement = connection.prepareStatement(query);
                    preparedStatement.setString(1, customerId);
                    resultSet = preparedStatement.executeQuery();

                    if (resultSet.next()) {
                        textField_1.setText(resultSet.getString("name"));
                        textField_2.setText(resultSet.getString("surname"));
                        textField_3.setText(resultSet.getString("email"));
                        textField_4.setText(resultSet.getString("phone"));

                        // Assuming you have a method to get the rented car info
                        String rentedCar = getRentedCarInfo(customerId);
                        textField_5.setText(rentedCar);
                    } else {
                        // Clear fields if customer not found
                        textField_1.setText("");
                        textField_2.setText("");
                        textField_3.setText("");
                        textField_4.setText("");
                        textField_5.setText("-");
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                } finally {
                    DatabaseHandler.closeResources(resultSet, preparedStatement, connection);
                }
            }

            private String getRentedCarInfo(String customerId) throws SQLException {
                Connection connection = null;
                PreparedStatement preparedStatement = null;
                ResultSet resultSet = null;

                try {
                    connection = DatabaseHandler.getConnection();
                    String query = "SELECT model FROM cars INNER JOIN rentals ON cars.car_id = rentals.car_id WHERE rentals.customer_id = ?";
                    preparedStatement = connection.prepareStatement(query);
                    preparedStatement.setString(1, customerId);
                    resultSet = preparedStatement.executeQuery();

                    if (resultSet.next()) {
                        return resultSet.getString("model");
                    } else {
                        return "-";
                    }
                } finally {
                    DatabaseHandler.closeResources(resultSet, preparedStatement, connection);
                }
            }
        });

        btnNewButton_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                GUI_CarRental carRentalWindow = new GUI_CarRental();
                carRentalWindow.getFrame().setVisible(true);
                frame.setVisible(false);
            }
        });

        btnNewButton_1_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String customerId = textField.getText();
                if (customerId.isEmpty()) {
                    System.out.println("Select Customer");
                    return;
                }

                deleteCustomer(customerId);
            }

            private void deleteCustomer(String customerId) {
                Connection connection = null;
                PreparedStatement deleteRentalStatement = null;
                PreparedStatement deleteCustomerStatement = null;

                try {
                    connection = DatabaseHandler.getConnection();
                    connection.setAutoCommit(false);

                    // Delete rentals associated with the customer
                    String deleteRentalQuery = "DELETE FROM rentals WHERE customer_id = ?";
                    deleteRentalStatement = connection.prepareStatement(deleteRentalQuery);
                    deleteRentalStatement.setString(1, customerId);
                    deleteRentalStatement.executeUpdate();

                    // Delete customer
                    String deleteCustomerQuery = "DELETE FROM customers WHERE customer_id = ?";
                    deleteCustomerStatement = connection.prepareStatement(deleteCustomerQuery);
                    deleteCustomerStatement.setString(1, customerId);
                    deleteCustomerStatement.executeUpdate();

                    connection.commit();

                    textField.setText("");
                    textField_1.setText("");
                    textField_2.setText("");
                    textField_3.setText("");
                    textField_4.setText("");
                    textField_5.setText("-");

                    JOptionPane.showMessageDialog(frame, "Customer Deleted Successfully");
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    try {
                        if (connection != null) {
                            connection.rollback();
                        }
                    } catch (SQLException rollbackEx) {
                        rollbackEx.printStackTrace();
                    }
                } finally {
                    // Close resources
                    DatabaseHandler.closeResources(null, deleteRentalStatement, null);
                    DatabaseHandler.closeResources(null, deleteCustomerStatement, connection);
                }
            }
        });

        frame.setVisible(true);
    }

    public JFrame getFrame() {
        return frame;
    }
}

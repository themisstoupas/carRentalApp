import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GUI_SearchCar {

    private JFrame frame;
    private JTextField textField;
    private JTextField textField_1;
    private JTextField textField_2;
    private JTextField textField_3;
    private JTextField textField_4;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    GUI_SearchCar window = new GUI_SearchCar();
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public GUI_SearchCar() {
        initialize();
    }

    private void initialize() {
        frame = new JFrame();
        frame.setBounds(100, 100, 900, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        JButton btnNewButton = new JButton("CLOSE");
        btnNewButton.setBounds(514, 384, 230, 54);
        btnNewButton.setFont(new Font("Tahoma", Font.ITALIC, 18));
        frame.getContentPane().add(btnNewButton);

        JLabel lblNewLabel = new JLabel("SEARCH FOR A CAR");
        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 22));
        lblNewLabel.setBounds(95, 27, 684, 40);
        frame.getContentPane().add(lblNewLabel);

        JLabel lblNewLabel_1 = new JLabel("CAR ID:");
        lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_1.setFont(new Font("Tahoma", Font.ITALIC, 18));
        lblNewLabel_1.setBounds(40, 84, 195, 30);
        frame.getContentPane().add(lblNewLabel_1);

        JLabel lblNewLabel_1_1 = new JLabel("MODEL:");
        lblNewLabel_1_1.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_1_1.setFont(new Font("Tahoma", Font.ITALIC, 18));
        lblNewLabel_1_1.setBounds(40, 147, 195, 30);
        frame.getContentPane().add(lblNewLabel_1_1);

        JLabel lblNewLabel_1_2 = new JLabel("SEATS:");
        lblNewLabel_1_2.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_1_2.setFont(new Font("Tahoma", Font.ITALIC, 18));
        lblNewLabel_1_2.setBounds(40, 208, 195, 30);
        frame.getContentPane().add(lblNewLabel_1_2);

        JLabel lblNewLabel_1_3 = new JLabel("DAILY COST:");
        lblNewLabel_1_3.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_1_3.setFont(new Font("Tahoma", Font.ITALIC, 18));
        lblNewLabel_1_3.setBounds(40, 270, 195, 30);
        frame.getContentPane().add(lblNewLabel_1_3);

        JLabel lblNewLabel_1_3_1 = new JLabel("RENTED BY:");
        lblNewLabel_1_3_1.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_1_3_1.setFont(new Font("Tahoma", Font.ITALIC, 18));
        lblNewLabel_1_3_1.setBounds(40, 334, 195, 30);
        frame.getContentPane().add(lblNewLabel_1_3_1);

        textField = new JTextField();
        textField.setBounds(214, 93, 171, 19);
        frame.getContentPane().add(textField);
        textField.setColumns(10);

        JButton btnNewButton_1 = new JButton("SEARCH");
        btnNewButton_1.setFont(new Font("Tahoma", Font.ITALIC, 18));
        btnNewButton_1.setBounds(426, 92, 163, 22);
        frame.getContentPane().add(btnNewButton_1);

        textField_1 = new JTextField();
        textField_1.setColumns(10);
        textField_1.setBounds(214, 156, 171, 19);
        frame.getContentPane().add(textField_1);

        textField_2 = new JTextField();
        textField_2.setColumns(10);
        textField_2.setBounds(214, 217, 171, 19);
        frame.getContentPane().add(textField_2);

        textField_3 = new JTextField();
        textField_3.setColumns(10);
        textField_3.setBounds(214, 279, 171, 19);
        frame.getContentPane().add(textField_3);

        textField_4 = new JTextField();
        textField_4.setColumns(10);
        textField_4.setBounds(214, 343, 171, 19);
        frame.getContentPane().add(textField_4);

        JButton btnNewButton_2 = new JButton("DELETE CAR");
        btnNewButton_2.setFont(new Font("Tahoma", Font.ITALIC, 18));
        btnNewButton_2.setBounds(68, 391, 239, 40);
        frame.getContentPane().add(btnNewButton_2);

        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.dispose();  // Close the current frame
                GUI_CarRental carRentalWindow = new GUI_CarRental();
                carRentalWindow.getFrame().setVisible(true);  // Open the GUI_CarRental window
            }
        });

        btnNewButton_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String carId = textField.getText();
                searchCar(carId);
            }

            private void searchCar(String carId) {
                Connection connection = null;
                PreparedStatement preparedStatement = null;
                ResultSet resultSet = null;

                try {
                    connection = DatabaseHandler.getConnection();
                    String query = "SELECT cars.*, customers.name AS rented_by FROM cars LEFT JOIN rentals ON cars.car_id = rentals.car_id LEFT JOIN customers ON rentals.customer_id = customers.customer_id WHERE cars.car_id = ?";
                    preparedStatement = connection.prepareStatement(query);
                    preparedStatement.setString(1, carId);
                    resultSet = preparedStatement.executeQuery();

                    if (resultSet.next()) {
                        textField_1.setText(resultSet.getString("model"));
                        textField_2.setText(resultSet.getString("seats"));
                        textField_3.setText(resultSet.getString("daily_cost"));
                        String rentedBy = resultSet.getString("rented_by");
                        textField_4.setText(rentedBy != null ? rentedBy : "-");
                    } else {
                        textField_1.setText("");
                        textField_2.setText("");
                        textField_3.setText("");
                        textField_4.setText("-");
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                } finally {
                    DatabaseHandler.closeResources(resultSet, preparedStatement, connection);
                }
            }
        });

        btnNewButton_2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String carId = textField.getText();
                deleteCar(carId);
            }

            private void deleteCar(String carId) {
                Connection connection = null;
                PreparedStatement deleteRentalsStatement = null;
                PreparedStatement deleteCarStatement = null;

                try {
                    connection = DatabaseHandler.getConnection();

                    // Delete rentals associated with the car
                    String deleteRentalsQuery = "DELETE FROM rentals WHERE car_id = ?";
                    deleteRentalsStatement = connection.prepareStatement(deleteRentalsQuery);
                    deleteRentalsStatement.setString(1, carId);
                    deleteRentalsStatement.executeUpdate();

                    // Delete the car
                    String deleteCarQuery = "DELETE FROM cars WHERE car_id = ?";
                    deleteCarStatement = connection.prepareStatement(deleteCarQuery);
                    deleteCarStatement.setString(1, carId);
                    deleteCarStatement.executeUpdate();

                    // Clear the fields after deletion
                    textField_1.setText("");
                    textField_2.setText("");
                    textField_3.setText("");
                    textField_4.setText("-");

                } catch (SQLException ex) {
                    ex.printStackTrace();
                } finally {
                    DatabaseHandler.closeResources(null, deleteRentalsStatement, null);
                    DatabaseHandler.closeResources(null, deleteCarStatement, connection);
                }
            }
        });

        frame.setVisible(true);
    }

    public JFrame getFrame() {
        return frame;
    }
}

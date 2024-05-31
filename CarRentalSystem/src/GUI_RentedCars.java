import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class GUI_RentedCars {

    private JFrame frame;
    private JTable table;

    // JDBC Database URL
    private static final String DB_URL = "jdbc:mysql://localhost:3306/car_rental";
    // Database credentials
    private static final String USER = "root";
    private static final String PASS = "";

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    GUI_RentedCars window = new GUI_RentedCars();
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the application.
     */
    public GUI_RentedCars() {
        initialize();
        displayRentedCars(); // Fetch and display rented cars on initialization
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        frame = new JFrame();
        frame.setBounds(100, 100, 900, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        JLabel lblTitle = new JLabel("Currently Rented Cars");
        lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitle.setFont(new Font("Arial", Font.BOLD, 24));
        lblTitle.setBounds(10, 11, 864, 50);
        frame.getContentPane().add(lblTitle);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(10, 72, 864, 294);
        frame.getContentPane().add(scrollPane);

        table = new JTable();
        table.setModel(new DefaultTableModel(
            new Object[][] {},
            new String[] {"Car", "Customer", "Days", "Total Cost"}
        ));
        scrollPane.setViewportView(table);

        JButton btnClose = new JButton("CLOSE");
        btnClose.setFont(new Font("Arial", Font.PLAIN, 18));
        btnClose.setBounds(657, 393, 217, 50);
        frame.getContentPane().add(btnClose);

        btnClose.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                GUI_CarRental carRentalWindow = new GUI_CarRental();
                carRentalWindow.getFrame().setVisible(true);
                frame.dispose();
            }
        });
    }

    private void displayRentedCars() {
        DefaultTableModel model = (DefaultTableModel) table.getModel();

        try {
            Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
            String query = "SELECT c.model AS car, cu.name AS customer, r.days, c.daily_cost * r.days AS total_cost " +
                           "FROM rentals r " +
                           "JOIN cars c ON r.car_id = c.car_id " +
                           "JOIN customers cu ON r.customer_id = cu.customer_id";
            try (PreparedStatement stmt = conn.prepareStatement(query);
                 ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    model.addRow(new Object[] {
                        rs.getString("car"),
                        rs.getString("customer"),
                        rs.getInt("days"),
                        rs.getDouble("total_cost")
                    });
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

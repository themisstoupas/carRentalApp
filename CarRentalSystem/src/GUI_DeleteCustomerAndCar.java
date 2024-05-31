import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Font;

public class GUI_DeleteCustomerAndCar {

    private JFrame frame;
    private JTextField searchTextField;
    private JLabel resultLabel;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    GUI_DeleteCustomerAndCar window = new GUI_DeleteCustomerAndCar();
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public GUI_DeleteCustomerAndCar() {
        initialize();
    }

    private void initialize() {
        frame = new JFrame();
        frame.setBounds(100, 100, 901, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        JPanel panel = new JPanel();
        panel.setBounds(10, 11, 414, 239);
        frame.getContentPane().add(panel);
        panel.setLayout(null);

        JLabel lblSearch = new JLabel("Enter Customer/Car ID:");
        lblSearch.setBounds(10, 30, 150, 20);
        panel.add(lblSearch);

        searchTextField = new JTextField();
        searchTextField.setBounds(170, 30, 150, 20);
        panel.add(searchTextField);
        searchTextField.setColumns(10);

        JButton btnSearch = new JButton("Search");
        btnSearch.setBounds(330, 30, 80, 20);
        panel.add(btnSearch);

        resultLabel = new JLabel("");
        resultLabel.setBounds(10, 70, 400, 150);
        panel.add(resultLabel);

        JButton btnNewButton = new JButton("CLOSE");
        btnNewButton.setFont(new Font("Tahoma", Font.ITALIC, 18));
        btnNewButton.setBounds(227, 329, 223, 43);
        frame.getContentPane().add(btnNewButton);

        btnSearch.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Perform database query and fetch data based on the entered ID
                // Update the resultLabel with the retrieved information
                String searchId = searchTextField.getText();
                // Implement database query here and update resultLabel accordingly
                // For now, let's assume you have a method to fetch data
                String result = fetchDataFromDatabase(searchId);
                resultLabel.setText(result);
            }
        });

        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                GUI_CarRental carRentalWindow = new GUI_CarRental();
                carRentalWindow.getFrame().setVisible(true);
                frame.dispose();
            }
        });
    }

    private String fetchDataFromDatabase(String id) {
        // Replace this with actual database query logic
        // For now, returning a placeholder result
        return "Result: Data for ID " + id + " not found.";
    }

    public JFrame getFrame() {
        return frame;
    }
}

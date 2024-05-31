import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.SwingConstants;
import javax.swing.JButton;

public class GUI_CarRental {

    private JFrame frame;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    GUI_CarRental window = new GUI_CarRental();
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public GUI_CarRental() {
        initialize();
    }

    private void initialize() {
        frame = new JFrame();
        frame.setBounds(100, 100, 902, 469);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        JLabel lblNewLabel = new JLabel("Welcome to Car Rental System");
        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 24));
        lblNewLabel.setBounds(142, 26, 536, 44);
        frame.getContentPane().add(lblNewLabel);

        JButton btnNewButton = new JButton("ADD CAR");
        btnNewButton.setFont(new Font("Tahoma", Font.ITALIC, 18));
        btnNewButton.setBounds(273, 80, 275, 35);
        frame.getContentPane().add(btnNewButton);

        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                GUI_AddCar addCarWindow = new GUI_AddCar();
                addCarWindow.getFrame().setVisible(true);
                frame.setVisible(false);
            }
        });

        JButton btnAddCustomer = new JButton("ADD CUSTOMER");
        btnAddCustomer.setFont(new Font("Tahoma", Font.ITALIC, 18));
        btnAddCustomer.setBounds(273, 137, 275, 35);
        frame.getContentPane().add(btnAddCustomer);

        btnAddCustomer.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                GUI_AddCustomer addCustomerWindow = new GUI_AddCustomer();
                addCustomerWindow.getFrame().setVisible(true);
                frame.setVisible(false);
            }
        });

        JButton btnRentedCars = new JButton("RENTED CARS");
        btnRentedCars.setFont(new Font("Tahoma", Font.ITALIC, 18));
        btnRentedCars.setBounds(273, 198, 275, 35);
        frame.getContentPane().add(btnRentedCars);

        btnRentedCars.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                GUI_RentedCars rentedCarsWindow = new GUI_RentedCars();
                rentedCarsWindow.getFrame().setVisible(true);
                frame.setVisible(false);
            }
        });

        JButton btnSearchCustomercar = new JButton("RENT CAR");
        btnSearchCustomercar.setFont(new Font("Tahoma", Font.ITALIC, 18));
        btnSearchCustomercar.setBounds(273, 256, 275, 35);
        frame.getContentPane().add(btnSearchCustomercar);

        btnSearchCustomercar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                GUI_RentCar rentCarWindow = new GUI_RentCar();
                rentCarWindow.getFrame().setVisible(true);
                frame.setVisible(false);
            }
        });

        JButton btnSearchCustomercar_1 = new JButton("SEARCH CUSTOMER");
        btnSearchCustomercar_1.setFont(new Font("Tahoma", Font.ITALIC, 18));
        btnSearchCustomercar_1.setBounds(273, 318, 275, 35);
        frame.getContentPane().add(btnSearchCustomercar_1);

        btnSearchCustomercar_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                GUI_SearchCustomer searchCustomerWindow = new GUI_SearchCustomer();
                searchCustomerWindow.getFrame().setVisible(true);
                frame.setVisible(false);
            }
        });

        JButton btnSearchCar = new JButton("SEARCH CAR");
        btnSearchCar.setFont(new Font("Tahoma", Font.ITALIC, 18));
        btnSearchCar.setBounds(273, 376, 275, 35);
        frame.getContentPane().add(btnSearchCar);

        btnSearchCar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                GUI_SearchCar searchCarWindow = new GUI_SearchCar();
                searchCarWindow.getFrame().setVisible(true);
                frame.setVisible(false);
            }
        });
    }

    public JFrame getFrame() {
        return frame;
    }
}

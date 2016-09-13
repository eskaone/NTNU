package GUI;

import Database.UserManagement;
import GUI.WindowPanels.Users;

import javax.swing.*;
import java.awt.*;

/**
 * Created by olekristianaune on 09.03.2016.
 */
public class AddUser extends JDialog {
    private JTextField firstName;
    private JTextField lastName;
    private JTextField email;
    private JComboBox<String> userType;
    private JTextField userName;
    private JPanel mainPane;
    private JButton cancelButton;
    private JButton addUserButton;
    private JTextField phone;
    private JPasswordField password;
    private JRadioButton changePasswordRadioButton;

    UserManagement userManagement;

    /**
     * Constructor to the AddUser graphical user interface.
     */
    public AddUser() {
        setTitle("New User");
        setContentPane(mainPane);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        Image icon = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/Images/icon32.png"));
        setIconImage(icon);

        // Setting up the userType select box
        userType.addItem("Admin");
        userType.addItem("Sale");
        userType.addItem("Driver");
        userType.addItem("Chef");

        addUserButton.addActionListener(e -> {
            String fName = firstName.getText();
            String lName = lastName.getText();
            String mail = email.getText();
            String phoneNr = phone.getText();
            int type1 = userType.getSelectedIndex();
            String uName = userName.getText();
            String pass = new String(password.getPassword());


            userManagement = new UserManagement();
            // TODO - legg inn sjekk for om registrering var vellykket
            userManagement.registerUser(fName, lName, uName, pass, mail, phoneNr, type1); // Legg til bruker i database

            Users.updateUsers();

            setVisible(false);
            dispose();

        });

        cancelButton.addActionListener(e -> {
            setVisible(false);
            dispose();
        });

        pack();
        setLocationRelativeTo(getParent());
        setModal(true);
        setVisible(true);
    }
}

package GUI.WindowPanels;

import Database.UserManagement;
import GUI.AddUser;
import GUI.EditUser;
import Util.HelperClasses.MainTableModel;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import static javax.swing.JOptionPane.*;

/**
 * Created by olekristianaune on 13.03.2016.
 */
public class Users {

    static UserManagement userManagement = new UserManagement();
    static MainTableModel userModel;
    static MainTableModel inactiveUserModel;
    static DefaultListSelectionModel listSelectionModel;

    /**
     * Constructor for the window panel Users.
     * @param addUserButton         JButton that opens the interface to add a new user.
     * @param userTable             JTable with all the active users.
     * @param inactiveUserTable     JTable with all the inactive users.
     * @param searchUsers           JTextField that allows the user to search through the users.
     * @param deleteUsersButton     JButton that deletes the selected user.
     * @param editUserButton        JButton that opens the interface to edit the selected user.
     * @param reactivateUserButton  JButton that reactivates an inactive user.
     */
    public Users(JButton addUserButton, final JTable userTable, JTable inactiveUserTable, final JTextField searchUsers, JButton deleteUsersButton, JButton editUserButton, JButton reactivateUserButton) {

        final int usernameColumnNr = 4;
        final int userTypeColumnNr = 5;

        String[] header = {"First Name", "Last Name", "Email", "Phone", "Username", "User Type"}; // Header titles

        userModel = new MainTableModel();
        inactiveUserModel = new MainTableModel();

        // Add header to columns
        userModel.setColumnIdentifiers(header);
        inactiveUserModel.setColumnIdentifiers(header);

        userTable.setModel(userModel); // Add model to table
        userTable.setAutoCreateRowSorter(true); // Auto sort table by row
        userTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        inactiveUserTable.setModel(inactiveUserModel);
        inactiveUserTable.setAutoCreateRowSorter(true);
        inactiveUserTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        listSelectionModel = new DefaultListSelectionModel();

        addUserButton.addActionListener(e -> new AddUser());

        editUserButton.addActionListener(e ->{
            try {
                if (userTable.getSelectedRows().length == 1 ) {
                    String username = (String) userTable.getValueAt(userTable.getSelectedRow(), usernameColumnNr); //get username for selected row
                    new EditUser(username);
                } else if(userTable.getSelectedRows().length < 1){
                    showMessageDialog(null, "A user needs to be selected.");
                }
                else{
                    showMessageDialog(null, "Only one user can be selected.");
                }
            }
            catch (IndexOutOfBoundsException iobe){
                showMessageDialog(null, "A user needs to be selected.");
            }

        });

        // Right Click Menu
        JPopupMenu popupMenu = new JPopupMenu("Users");
        popupMenu.add(new AbstractAction("New User") {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AddUser();
            }
        });
        popupMenu.add(new AbstractAction("Edit User") {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (userTable.getSelectedRow() != -1) {
                    String username = (String) userModel.getValueAt(userTable.getSelectedRow(), usernameColumnNr);
                    new EditUser(username);
                }
            }
        });
        popupMenu.add(new AbstractAction("Delete User") {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (userTable.getSelectedRow() != -1) {
                    String username = (String) userModel.getValueAt(userTable.getSelectedRow(), usernameColumnNr);
                    userManagement.deleteUser(username);
                }
                updateUsers();
                updateInactiveUsers();
            }
        });

        // Mouse Click Listener
        userTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                int r = userTable.rowAtPoint(e.getPoint());
                if (r >= 0 && r < userTable.getRowCount()) {
                    userTable.setRowSelectionInterval(r, r);
                } else {
                    userTable.clearSelection();
                }

                int rowindex = userTable.getSelectedRow();
                if (rowindex < 0)
                    return;

                if (e.isPopupTrigger() && e.getComponent() instanceof JTable) {
                    popupMenu.show(e.getComponent(), e.getX(), e.getY());
                }
            }
            @Override
            public void mouseReleased(MouseEvent e) {
                int r = userTable.rowAtPoint(e.getPoint());
                if (r >= 0 && r < userTable.getRowCount()) {
                    userTable.setRowSelectionInterval(r, r);
                } else {
                    userTable.clearSelection();
                }

                int rowindex = userTable.getSelectedRow();
                if (rowindex < 0)
                    return;

                if (e.isPopupTrigger() && e.getComponent() instanceof JTable) {
                    popupMenu.show(e.getComponent(), e.getX(), e.getY());
                }
            }
            @Override
            public void mouseClicked(MouseEvent e) {
                if(e.getClickCount() == 2) {
                    if (userTable.getSelectedRow() != -1) {
                        String username = (String) userModel.getValueAt(userTable.getSelectedRow(), usernameColumnNr);
                        new EditUser(username);
                    }
                }
            }
        });

        deleteUsersButton.addActionListener(e ->{
            if (userTable.getSelectedRow() != -1) {
                String username = (String) userModel.getValueAt(userTable.getSelectedRow(), usernameColumnNr);
                userManagement.deleteUser(username);
            }
            updateUsers();
            updateInactiveUsers();
        });

        reactivateUserButton.addActionListener(e -> {
            if (inactiveUserTable.getSelectedRow() != -1) {
                String username = (String) inactiveUserModel.getValueAt(inactiveUserTable.getSelectedRow(), usernameColumnNr);
                userManagement.reactivateUser(username);
            }
            updateUsers();
            updateInactiveUsers();
        });

        // Serach field input changed?
        searchUsers.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                searchFieldChange();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                searchFieldChange();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                searchFieldChange();
            }

            private void searchFieldChange() {
                String searchTerm = searchUsers.getText();

                ArrayList<Object[]> searchResult = userManagement.userSearch(searchTerm);

                updateUsers(searchResult);
            }
        });

    }

    /**
     * Util.Updates the active user table from the database.
     */
    public static void updateUsers() {

        //clears selection in row, important
        listSelectionModel.clearSelection();

        // Get users from database
        ArrayList<Object[]> users = userManagement.userInfo();

        updateUsers(users);
    }

    /**
     * Util.Updates the active user table from the database.
     * @param users List of active users.
     */
    public static void updateUsers(ArrayList<Object[]> users) {

        // Empties entries of Users table
        userModel.setRowCount(0);

        // Add users from arraylist to table
        for (Object[] user : users) {
            userModel.addRow(user);
        }
    }

    /**
     * Util.Updates the inactive user table from the database.
     */
    public static void updateInactiveUsers() {

        ArrayList<Object[]> inactiveUsers = userManagement.getDeletedUsers();

        updateInactiveUsers(inactiveUsers);
    }

    /**
     * Util.Updates the inactive user table from the database.
     * @param inactiveUsers List of inactive users.
     */
    public static void updateInactiveUsers(ArrayList<Object[]> inactiveUsers) {

        // Empties entries of Users table
        inactiveUserModel.setRowCount(0);

        // Add users from arraylist to table
        for (Object[] user : inactiveUsers) {
            inactiveUserModel.addRow(user);
        }

    }
}

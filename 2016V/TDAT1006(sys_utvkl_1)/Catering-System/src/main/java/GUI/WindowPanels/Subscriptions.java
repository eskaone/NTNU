package GUI.WindowPanels;

import Database.SubscriptionManagement;
import GUI.AddOrder;
import GUI.AddSubscription;
import GUI.EditSubscription;
import Util.HelperClasses.MainTableModel;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import static javax.swing.JOptionPane.YES_OPTION;
import static javax.swing.JOptionPane.showConfirmDialog;
import static javax.swing.JOptionPane.showMessageDialog;

/**
 * Created by olekristianaune on 13.03.2016.
 */
public class Subscriptions {

    static SubscriptionManagement subscriptionManagement = new SubscriptionManagement();
    static MainTableModel subscriptionModel;
    private final int idColumnNr = 0;
    private final int nameColumnNr = 1;

    /**
     * A tab that consists of a subscription panel. Constrctor creates model for table
     * and sets up actionlistener. Sends calls to other classes as actions are performed.
     *
     * @param subscriptionsTable the main table where all subscriptions are shown.
     * @param searchSubscriptions a tekst field for searching the main table.
     * @param addSubscriptionButton a button to create a new subscription.
     * @param editSubscriptionButton a button to edit existing subscription.
     * @param deleteSubscriptionButton a button to delete existing subscription, also deletes corresponding orders.
     */
    public Subscriptions(JTable subscriptionsTable, final JTextField searchSubscriptions, JButton addSubscriptionButton, JButton editSubscriptionButton, JButton deleteSubscriptionButton) {

        String[] headers = {"ID", "Name", "Date From", "Date To", "Frequency in weeks"};

        subscriptionModel = new MainTableModel();

        subscriptionModel.setColumnIdentifiers(headers);

        subscriptionsTable.setModel(subscriptionModel);
        subscriptionsTable.setAutoCreateRowSorter(true);
        subscriptionsTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);


        DefaultTableCellRenderer intRenderer = new DefaultTableCellRenderer();
        intRenderer.setHorizontalAlignment(SwingConstants.LEFT);
        subscriptionsTable.getColumnModel().getColumn(0).setCellRenderer(intRenderer);
        subscriptionsTable.getColumnModel().getColumn(2).setCellRenderer(intRenderer);

        addSubscriptionButton.addActionListener(e -> new AddSubscription());


        editSubscriptionButton.addActionListener(e -> {
            if(subscriptionsTable.getSelectedColumn() >= 0) {
                int id = (Integer)subscriptionsTable.getValueAt(subscriptionsTable.getSelectedRow(), idColumnNr); //get username for selected row
                new EditSubscription(id);
            }
            else{
                showMessageDialog(null, "An subscription needs to be selected.");
            }
        });

        deleteSubscriptionButton.addActionListener(e -> {
            if (subscriptionsTable.getSelectedRow() > 0) {
                try {
                    int yes = showConfirmDialog(null, "Delete subscription for " +
                            subscriptionsTable.getValueAt(subscriptionsTable.getSelectedRow(),nameColumnNr)+"?", null, JOptionPane.YES_NO_OPTION);
                    if (yes == YES_OPTION) {
                        int id = (Integer) subscriptionsTable.getValueAt(subscriptionsTable.getSelectedRow(), idColumnNr);
                        subscriptionManagement.deleteSubscription(id);
                    }
                    updateSubscriptions();
                }
                catch (NumberFormatException e1){
                    //canceled
                }
            }

        });
        // Right Click Menu
        JPopupMenu popupMenu = new JPopupMenu("Subscriptions");
        popupMenu.add(new AbstractAction("New Util.Subscription") {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AddOrder();
            }
        });
        popupMenu.add(new AbstractAction("Edit Util.Subscription") {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (subscriptionsTable.getSelectedRow() != -1) {
                    int id = (Integer) subscriptionsTable.getValueAt(subscriptionsTable.getSelectedRow(), idColumnNr);
                    new EditSubscription(id);
                }
            }
        });
        popupMenu.add(new AbstractAction("Delete Subscription") {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (subscriptionsTable.getSelectedRow() > 0) {
                    try {
                        int yes = showConfirmDialog(null, "Delete subscription for " +
                                subscriptionsTable.getValueAt(subscriptionsTable.getSelectedRow(),nameColumnNr)+"?", null, JOptionPane.YES_NO_OPTION);
                        if (yes == YES_OPTION) {
                            int id = (Integer) subscriptionsTable.getValueAt(subscriptionsTable.getSelectedRow(), idColumnNr);
                            subscriptionManagement.deleteSubscription(id);
                        }
                        updateSubscriptions();
                    }
                    catch (NumberFormatException e1){
                        //canceled
                    }
                }
            }
        });


        subscriptionsTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                int r = subscriptionsTable.rowAtPoint(e.getPoint());
                if (r >= 0 && r < subscriptionsTable.getRowCount()) {
                    subscriptionsTable.setRowSelectionInterval(r, r);
                } else {
                    subscriptionsTable.clearSelection();
                }

                int rowindex = subscriptionsTable.getSelectedRow();
                if (rowindex < 0)
                    return;

                if (e.isPopupTrigger() && e.getComponent() instanceof JTable) {
                    popupMenu.show(e.getComponent(), e.getX(), e.getY());
                }
            }
            @Override
            public void mouseReleased(MouseEvent e) {
                int r = subscriptionsTable.rowAtPoint(e.getPoint());
                if (r >= 0 && r < subscriptionsTable.getRowCount()) {
                    subscriptionsTable.setRowSelectionInterval(r, r);
                } else {
                    subscriptionsTable.clearSelection();
                }

                int rowindex = subscriptionsTable.getSelectedRow();
                if (rowindex < 0)
                    return;

                if (e.isPopupTrigger() && e.getComponent() instanceof JTable) {
                    popupMenu.show(e.getComponent(), e.getX(), e.getY());
                }
            }
            @Override
            public void mouseClicked(MouseEvent e) {
                if(e.getClickCount() == 2) {
                    int id = (Integer) subscriptionsTable.getValueAt(subscriptionsTable.getSelectedRow(), idColumnNr);
                    new EditSubscription(id);
                }
            }
        });
        searchSubscriptions.getDocument().addDocumentListener(new DocumentListener() {
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
                String searchTerm = searchSubscriptions.getText();

                updateFromSearch(searchTerm);
            }
        });

    }

    /**
     * Sends a call to the database with a searchterm, then updates the subscritions table
     * with information matching the searchterm.
     *
     * @param searchTerm a string of what to search for.
     */
    private static void updateFromSearch(String searchTerm){
        // Empties entries of Users table
        ArrayList<Object[]> searchResult = subscriptionManagement.subscriptionSearch(searchTerm);
        subscriptionModel.setRowCount(0);

        // Add users from arraylist to table
        for (Object[] sub : searchResult) {
            subscriptionModel.addRow(sub);
        }
    }

    /**
     * a general method to update the subscription table, sends a call for all
     * subscriptions in the database and updates the subscription table with this
     * information.
     *
     * The method is usually called when an update is made, a new subscription is added or
     * through the auto updater.
     *
     */
    public static void updateSubscriptions() {

        // Get users from database
        ArrayList<Object[]> subs = subscriptionManagement.getSubscriptions();

        // Empties entries of Users table
        subscriptionModel.setRowCount(0);

        // Add users from arraylist to table
        for (Object[] sub : subs) {
            subscriptionModel.addRow(sub);
        }

    }


}

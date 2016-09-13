package GUI;

import Database.FoodManagement;
import Database.OrderManagement;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;

import static GUI.WindowPanels.Orders.updateOrders;
import static javax.swing.JOptionPane.showInputDialog;
import static javax.swing.JOptionPane.showMessageDialog;

/**
 * Created by Evdal on 15.04.2016.
 */
public class PanelForSubs extends JPanel {
    private JTable recipeTable;
    private JButton rightButton;
    private JButton leftButton;
    private JFormattedTextField timeTextField;
    private JTextArea commentArea;
    private JTextField searchField;
    private JList recipeList;
    private JPanel mainPanel;
    private JSpinner timeSpinner;
    final DefaultTableModel recipeTableModel;
    private int day;

    private FoodManagement foodManagement = new FoodManagement();

    private final int recipeColumnNr = 0;
    private final int quantityColumnNr = 1;
    private final String defaultTimeValue = "12:00";
    private final String seconds = ":00";

    /**
     * Panel created as a new tab for each day added to a subscription.
     * @param day which day the panel should be added for. 0-7, where 0 is sunday.
     */
    public PanelForSubs(int day){


        /* Create Order Table */
        this.day = day;

        String[] headers = {"Recipe", "Portions"};


        recipeTableModel = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                //all cells false
                return false;
            }
        };


        recipeTableModel.setColumnIdentifiers(headers);

        recipeTable.setModel(recipeTableModel);

        //set up timeSpinner
        SpinnerDateModel timeModel = new SpinnerDateModel();
        timeModel.setCalendarField(Calendar.MINUTE);
        timeSpinner.setModel(timeModel);
        timeSpinner.setEditor(new JSpinner.DateEditor(timeSpinner, "HH:mm"));




        /* Create Recipe List */
        final DefaultListModel<String> recipeModel = new DefaultListModel<>();
        recipeList.setModel(recipeModel);
        recipeList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        final ArrayList<Object[]> recipes = foodManagement.getRecipes();

        for (Object[] recipe : recipes) {
            recipeModel.addElement((String)recipe[1]);
        }

        ArrayList<Object> searchList = new ArrayList<>(Arrays.asList(recipeModel.toArray()));
        searchField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                searchRecipes();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                searchRecipes();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                searchRecipes();
            }

            private void searchRecipes() {
                recipeModel.removeAllElements();
                for (Object o : searchList) {
                    String searchInput = searchField.getText();
                    if (((String)o).toLowerCase().contains(searchInput.toLowerCase())) {
                        recipeModel.addElement((String)o);
                    }
                }
                recipeList.validate();
            }
        });

        /* Left and Right buttons for adding and removing recipes from orders */
        leftButton.addActionListener(e -> {
            String selectedRecipe = (String)recipeList.getSelectedValue();
            int portions = Integer.parseInt(showInputDialog("How many portions of " + selectedRecipe.toLowerCase() + " do you want to add?")); // FIXME: Add failsafe for parsing integer


            if (existsInTable(recipeTable, selectedRecipe) == -1) {
                recipeTableModel.addRow(new Object[]{selectedRecipe,portions});
            } else {
                int row = existsInTable(recipeTable, selectedRecipe);
                int currentPortions = (Integer)recipeTableModel.getValueAt(row, 1);
                if (currentPortions + portions >= 1) {
                    recipeTableModel.setValueAt(currentPortions + portions, row, 1);
                }
            }
        });
        recipeList.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(e.getClickCount() == 2){
                    String selectedRecipe = (String)recipeList.getSelectedValue();
                    int portions = Integer.parseInt(showInputDialog("How many portions of " + selectedRecipe.toLowerCase() + " do you want to add?")); // FIXME: Add failsafe for parsing integer
                    if (existsInTable(recipeTable, selectedRecipe) == -1) {
                        recipeTableModel.addRow(new Object[]{selectedRecipe,portions});
                    } else {
                        int row = existsInTable(recipeTable, selectedRecipe);
                        int currentPortions = (Integer)recipeTableModel.getValueAt(row, 0);
                        if (currentPortions + portions >= 1) {
                            recipeTableModel.setValueAt(currentPortions + portions, row, 0);
                        }
                    }
                }
            }
        });
        recipeTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    String recipe = (String) recipeTable.getValueAt(recipeTable.getSelectedRow(), recipeColumnNr);
                    String in = showInputDialog("How many portions of " + recipe.toLowerCase() + " do you want?");
                    if(!in.equals("")) {
                        int portions = Integer.parseInt(in); // FIXME: Add failsafe for parsing integer
                        recipeTableModel.setValueAt(portions, recipeTable.getSelectedRow(), quantityColumnNr);
                    }
                    else{
                        showMessageDialog(null, "You need to input a number.");
                    }
                }
            }
        });
        recipeTable.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {}
            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_DELETE){
                    int[] selected = recipeTable.getSelectedRows();
                    for(int i =0; i<selected.length;i++){
                        recipeTableModel.removeRow(selected[i]);
                    }
                }
            }
            @Override
            public void keyReleased(KeyEvent e) {}
        });



        rightButton.addActionListener(e -> recipeTableModel.removeRow(recipeTable.getSelectedRow()));
        setVisible(true);
    }

    /**
     *
     * @param table
     * @param entry
     * @return
     */
    private int existsInTable(JTable table, String entry) {
        for (int i = 0; i < table.getRowCount(); i++) {
            if (table.getValueAt(i, 0).equals(entry)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * A method for adding the panel as a tab.
     * @return return the panel created.
     */
    public JPanel getMainPanel(){
        return mainPanel;
    }

    /**
     * A method to get the information inputted in the panel for use
     * in creating a new subscription.
     * @return returns all the recipes, as well as a comment for the day, which time and
     */
    public Object[][] getValues(){
        Date selectedTime = (Date)timeSpinner.getValue();
        DateFormat tFormat = new SimpleDateFormat("HH:mm:ss");
        String selectedTimeString = tFormat.format(selectedTime);
        String comment = commentArea.getText();

        Object[][] selectedRecipes = new Object[5][recipeTableModel.getRowCount()];
        for (int i = 0; i < recipeTableModel.getRowCount(); i++) {
            selectedRecipes[0][i] = recipeTableModel.getValueAt(i, 0);
            selectedRecipes[1][i] = recipeTableModel.getValueAt(i, 1);
        }
        selectedRecipes[2][0] = day;
        selectedRecipes[3][0] = comment;
        selectedRecipes[4][0] = selectedTimeString;

        return selectedRecipes;

    }

    /**
     *
     * @param values
     */
    public void addValues(Object[] values){
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
        for(Object[] obj : (ArrayList<Object[]>)values[0]){
            recipeTableModel.addRow(obj);
        }
        commentArea.setText((String)values[1]);
        try {
            timeSpinner.getModel().setValue(timeFormat.parse((String)values[2]));
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}

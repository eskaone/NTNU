import javax.swing.*;
import java.rmi.*;
import java.rmi.Naming;

public class RMIClient {
    public static void main(String[] args) throws Exception{
        Register register = (Register)Naming.lookup("rmi://10.20.208.57:1099/RegisterImpl");
        String input = JOptionPane.showInputDialog("Enter ID of item: [Exit with blank]");
        while (!input.equals("")) {
            int id = Integer.parseInt(input);
            input = JOptionPane.showInputDialog("Enter amount:");
            int amt = Integer.parseInt(input);
            register.changeInventory(id,amt);
            System.out.println(register.makeDataDescription());
            input = JOptionPane.showInputDialog("Enter ID of item: [Exit with blank]");
        }
        System.exit(0);
    }
}

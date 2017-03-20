import java.util.*;
import java.rmi.*;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.rmi.registry.*;

public class RegisterImpl extends UnicastRemoteObject implements Register {
  public static final int OK = -1;
  public static final int INVALID_ID = -2;
  public static final int EMPTY = -3;

  public RegisterImpl() throws RemoteException {
     super(0);    // required to avoid the 'rmic' step, see below
  }
  private List<Item> register = Collections.synchronizedList(new ArrayList<Item>());

public synchronized boolean registerItem(int id, String designation, String supplier, int inStorage, int lowerLim) {
   if (findItem(id) < 0) {
     Item item = new Item(id, designation, supplier, inStorage, lowerLim);
     register.add(item);
     return true;
   } else return false;
  }

  public synchronized int changeInventory(int id, int amount) {
    int index = findItem(id);
    if (index < 0) return INVALID_ID;
    else {
      if (!(register.get(index)).changeInventory(amount)) {
        return EMPTY;
      } else return OK;
    }
  }

  private int findItem(int id) {
    for (int i = 0; i < register.size(); i++) {
      if((register.get(i)).getId() == id)return i;

    }
    return -1;
  }

  public synchronized String makeOrderList() {
    String resultat = "\n\nOrder list:\n";
    for (int i = 0; i < register.size(); i++) {
      Item u = register.get(i);
      resultat += u.getId() + ", " + u.getDesignation() + ": " +
                  u.findBestQuantum() + "\n";
    }
    return resultat;
  }

  public synchronized String makeDataDescription() {
    String result = "All data:\n";
    for (int i = 0; i < register.size(); i++) {
      result += (register.get(i)).toString() + "\n";
    }
    return result;
  }

}

interface Register extends Remote{
    boolean registerItem(int id, String designation, String supplier, int inStorage, int lowerLim) throws RemoteException;
    int changeInventory(int id,int amount)throws RemoteException;
    String makeOrderList()throws RemoteException;
    String makeDataDescription()throws RemoteException;
}
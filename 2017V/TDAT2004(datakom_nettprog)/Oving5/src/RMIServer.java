import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

public class RMIServer {

    public static void main(String args[]) throws Exception {
        System.out.println("RMI server started");
        try { //special exception handler for registry creation
            LocateRegistry.createRegistry(1099);
            System.out.println("Java RMI registry created.");
        } catch (RemoteException e) {
            //do nothing, error means registry already exists
            System.out.println("Java RMI registry already exists.");
        }

        //Instantiate RmiServer

        RegisterImpl register = new RegisterImpl();
        // Bind this object instance to the name "RmiServer"
        Naming.rebind("rmi://10.20.203.254:1099/RegisterImpl", register);
        System.out.println("PeerServer bound in registry");
        register.registerItem(1,"a","genericSupplier",100,10);
        register.registerItem(2,"b","genericSupplier",100,10);
        register.registerItem(3,"c","genericSupplier",100,10);
        register.registerItem(4,"d","genericSupplier",100,10);
        register.registerItem(5,"e","genericSupplier",100,10);
        System.out.println(register.makeDataDescription());
        System.out.println(register.makeOrderList());
    }
}

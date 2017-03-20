public class Item {
    public static final int ORDER_FACTOR = 5;
    private int id;
    private String designation;
    private String supplier;
    private int inStorage;
    private int lowerLimit;

    public Item(int id, String designation, String supplier, int inStorage, int lowerLimit) {
        this.id = id;
        this.designation = designation;
        this.supplier = supplier;
        this.inStorage = inStorage;
        this.lowerLimit = lowerLimit;
    }


    public static int getOrderFactor() {
        return ORDER_FACTOR;
    }

    public synchronized int getId() {
        return id;
    }

    public synchronized String getDesignation() {
        return designation;
    }

    public synchronized String getSupplier() {
        return supplier;
    }

    public synchronized int getInStorage() {
        return inStorage;
    }

    public synchronized int getLowerLimit() {
        return lowerLimit;
    }

    public synchronized int findBestQuantum() {
        if (inStorage < lowerLimit) return ORDER_FACTOR * lowerLimit;
        return 0;
    }

    public synchronized boolean changeInventory(int change) {
        System.out.println("Endrer lagerbeholdning:\nItem id: " + id + "\nChange: " + change);
        if (inStorage + change < 0) return false;
        else {
            inStorage += change;
            return true;
        }
    }


    public void setId(int id) {
        this.id = id;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }

    public void setInStorage(int inStorage) {
        this.inStorage = inStorage;
    }

    public void setLowerLimit(int lowerLimit) {
        this.lowerLimit = lowerLimit;
    }

    @Override
    public String toString() {
        return "Item{" +
                "id=" + id +
                ", designation='" + designation + '\'' +
                ", supplier='" + supplier + '\'' +
                ", inStorage=" + inStorage +
                ", lowerLimit=" + lowerLimit +
                '}';
    }
}

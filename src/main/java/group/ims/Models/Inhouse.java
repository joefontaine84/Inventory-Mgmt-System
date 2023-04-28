package group.ims.Models;

/**
 * This class inherits the abstract Part class and exists to distinguish parts that are categorized as "inhouse", which have a unique associated parameter of "machineID".
 * */
public class Inhouse extends Part {
    private int machineID;

    /**
     * The Inhouse constructor first calls the parent constructor (the constructor of the abstract Part class) then calls the setMachineID function.
     * @param id the id to set
     * @param name the name to set
     * @param price the price to set
     * @param stock the inventory stock level to set
     * @param min the minimum inventory stock level to set
     * @param max the maximum inventory stock level to set
     * @param machineID the Machine ID to set
     * */
    public Inhouse(int id, String name, double price, int stock, int min, int max, int machineID) {
        super(id, name, price, stock, min, max);
        setMachineID(machineID);
    }

    /**
     * This function returns the inhouse part's machineID variable.
     * @return the machineID
     * */
    public int getMachineID() {
        return machineID;
    }

    /**
     * This function sets the inhouse part's machineID variable.
     * @param machineID the inhouse part's machineID
     * */
    public void setMachineID(int machineID) {
        this.machineID = machineID;
    }
}

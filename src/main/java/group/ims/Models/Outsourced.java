package group.ims.Models;

import group.ims.Models.Part;
/**
 * This class inherits the abstract Part class and exists to distinguish parts that are categorized as "outsourced", which have a unique associated parameter of "companyName".
 * */
public class Outsourced extends Part {

    public String companyName;

    /**
     * The Outsourced constructor first calls the parent constructor (the constructor of the abstract Part class) then calls the setCompanyName function.
     * @param id the id to set
     * @param name the name to set
     * @param price the price to set
     * @param stock the inventory stock level to set
     * @param min the minimum inventory stock level to set
     * @param max the maximum inventory stock level to set
     * @param companyName the company name to set
     * */
    public Outsourced(int id, String name, double price, int stock, int min, int max, String companyName) {
        super(id, name, price, stock, min, max);
        setCompanyName(companyName);
    }

    /**
     * This function returns the outsourced part's company name variable.
     * @return the company name
     * */
    public String getCompanyName() {
        return companyName;
    }

    /**
     * This function sets the outsourced part's company name variable.
     * @param companyName the outsourced part's company name
     * */
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }


}

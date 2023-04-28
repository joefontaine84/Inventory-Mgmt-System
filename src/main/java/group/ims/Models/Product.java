package group.ims.Models;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * This class contains methods and variables for the products managed within the inventory management system.
 * */

public class Product {
    private ObservableList<Part> associatedParts = FXCollections.observableArrayList();
    private int id;
    private String name;
    private double price;
    private int stock;
    private int min;
    private int max;

    /**
     * This is the Product class constructor that sets a products id, name, price, stock, min, and max.
     * @param id the Product's ID
     * @param name the Product's name
     * @param price the Product's price
     * @param stock the Product's stock
     * @param min the Product's min
     * @param max the Product's max
     * */
    public Product(int id, String name, double price, int stock, int min, int max) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.min = min;
        this.max = max;
    }

    /**
     * Another constructor for the Product class that does not take any parameters.
     * */
    public Product() {

    }

    /**
     * This function returns the product's id variable
     * @return the product's id variable
     * */
    public int getId() {
        return id;
    }

    /**
     * This function sets the product's id variable
     * @param id the product's id
     * */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * This function returns the product's name variable
     * @return the product's name variable
     * */
    public String getName() {
        return name;
    }

    /**
     * This function sets the product's name variable
     * @param name the product's name variable
     * */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * This function returns the product's price variable
     * @return the product's price variable
     * */
    public double getPrice() {
        return price;
    }

    /**
     * This function sets the product's price variable
     * @param price the product's price variable
     * */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * This function returns the product's stock variable
     * @return the product's stock variable
     * */
    public int getStock() {
        return stock;
    }

    /**
     * This function sets the product's stock variable
     * @param stock the product's stock variable
     * */
    public void setStock(int stock) {
        this.stock = stock;
    }

    /**
     * This function returns the product's min variable
     * @return the product's min variable
     * */
    public int getMin() {
        return min;
    }

    /**
     * This function sets the product's min variable
     * @param min the product's min variable
     * */
    public void setMin(int min) {
        this.min = min;
    }

    /**
     * This function returns the product's max variable
     * @return the product's max variable
     * */
    public int getMax() {
        return max;
    }

    /**
     * This function sets the product's max variable
     * @param max the product's max variable
     * */
    public void setMax(int max) {
        this.max = max;
    }

    /**
     * This function adds a Part object to the Product's associatedParts ObservableList variable
     * @param part the part object to be added to the Product's associatedParts ObservableList variable
     * */
    public void addAssociatedPart(Part part) {
        associatedParts.add(part);
    }

    /**
     * This function returns the Product's associatedParts ObservableList variable
     * @return the Product's associatedParts ObservableList variable
     * */
    public ObservableList<Part> getAllAssociatedParts() {
        return associatedParts;
    }

    /**
     * This function is not used by the program but could be implemented in a way that sets the Product's associatedParts ObservableList variable to another ObservableList.
     * @param list the list that will be set to the associatedParts variable.
     * */
    public void setAssociatedParts(ObservableList<Part> list) {
        associatedParts = list;
    }

    /**
     * This function is not used by the program but could be implmeted in a way that removes a selected Part object from the product's associatedParts ObservableList variable.
     * @param selectedAssociatedPart the part to delete from the product's associatedParts ObservableList variable.
     * */
   public boolean deleteAssociatedPart(Part selectedAssociatedPart) {
       associatedParts.remove(selectedAssociatedPart);
       return true;
    }

}



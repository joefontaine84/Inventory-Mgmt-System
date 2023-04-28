package group.ims.Models;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;

/**
 * This class implements high-level controls over parts and products within the inventory management system.
 * */

public class Inventory {
    private static ObservableList<Part> allParts = FXCollections.observableArrayList();
    private static ObservableList<Product> allProducts = FXCollections.observableArrayList();

    /**
     * This function adds a new Part object to the allParts ObservableList variable
     * @param newPart a new Part object to add to the allParts ObservableList variable
     * */
    public static void addPart(Part newPart){
        allParts.add(newPart);
    }

    /**
     * This function adds a new Product object to the allProducts ObservableList variable
     * @param newProduct a new Product object to add to the allProducts ObservableList variable
     * */
    public static void addProduct(Product newProduct) {
        allProducts.add(newProduct);
    }
    /**
     * This function is not utilized by the program but conducts a simple search of parts within the allParts ObservableList variable to
     * see if the provided partID matches with any existing partIDs.
     * @return a Part with a matching partID or null.
     * @param partID the partID that is used to search for matching partIDs within the allParts ObservableList variable.
     * */
    public static Part lookupPart (int partID) {
        for (Part part : getAllParts()) {
            if (part.getId() == partID) {
                return part;
            }
        }
        Alert alert = new Alert(Alert.AlertType.ERROR, "Invalid input has been provided.");
        alert.show();
        return null;
    }

    /**
     * This function is not utilized by the program but conducts a simple search of parts within the allParts ObservableList variable to
     * see if the provided part name matches with any existing part names, or if portions of existing part names match with the provided input part name.
     * @return a Part ObservableList with a matching part name (or portions thereof) or null.
     * @param partName  the part name that is used to search for matching part names (or portions thereof) within the allParts ObservableList variable.
     * */
    public static ObservableList<Part> lookupPart (String partName){
        ObservableList<Part> tempList = FXCollections.observableArrayList();
        for (Part part : getAllParts()) {
            if (part.getName().toLowerCase().startsWith(partName.toLowerCase()) || part.getName().toLowerCase().contains(partName.toLowerCase())) {
                tempList.add(part);
            } else {
                continue;
            }
        }
        return tempList;
    }

    /**
     * This function is not utilized by the program but conducts a simple search of products within the allProducts ObservableList variable to
     * see if the provided productID matches with any existing productIDs.
     * @return a Product with a matching partID or null.
     * @param productID the productID that is used to search for matching productIDs within the allProducts ObservableList variable.
     * */

    public static Product lookupProduct(int productID) {
        for (Product product : getAllProducts()) {
            if (product.getId() == productID) {
                return product;
            }
        }
        Alert alert = new Alert(Alert.AlertType.ERROR, "Invalid input has been provided.");
        alert.show();
        return null;
    }

    /**
     * This function is not utilized by the program but conducts a simple search of products within the allProdcuts ObservableList variable to
     * see if the provided product name matches with any existing product names, or if portions of existing product names match with the provided input product name.@return a Product ObservableList with a matching part name (or portions thereof) or null.
     * @param productName the product name that is used to search for matching product names (or portions thereof) within the allProducts ObservableList variable.
     * */
    public static ObservableList<Product> lookupProduct(String productName) {
        ObservableList<Product> tempList = FXCollections.observableArrayList();
        for (Product product : getAllProducts()) {
            if (product.getName().toLowerCase().startsWith(productName.toLowerCase()) || product.getName().toLowerCase().contains(productName.toLowerCase())) {
                tempList.add(product);
            } else {
                continue;
            }
        }
        return tempList;
    }

    /**
     * This function is not utilized by the program but could be implemented in a way which "updates" a part by removing the part from the allParts ObservableList variable
     * and adds a new Part object (maintaining the same part ID) with updated information.
     * @param index the index of the part object within the allParts ObservableList variable to be "updated" (ultimately removed and replaced with a new Part object).
     * @param selectedPart the Part object that will be added to the allParts ObservableList variable to effectively replace the Part object that is being modified.
     * */

    public static void updatePart(int index, Part selectedPart) {
        allParts.remove(allParts.indexOf(index));
        allParts.add(selectedPart);
    }

    /**
     * This function is not utilized by the program but could be implemented in a way which "updates" a product by removing the product from the allProducts ObservableList variable
     * and adds a new Product object (maintaining the same part ID) with updated information.
     * @param index the index of the product object within the allProducts ObservableList variable to be "updated" (ultimately removed and replaced with a new Product object).
     * @param newProduct the Product object that will be added to the allParts ObservableList variable to effectively replace the Product object that is being modified.

     * */
    public static void updateProduct(int index, Product newProduct) {
        allProducts.remove(allProducts.indexOf(index));
        allProducts.add(newProduct);
    }

    /**
     * This function is not utilized by the program but could be implemented in a way which deletes a selected part object from the allParts ObservableList variable.
     * @param selectedPart the part to be removed from the allParts ObservableList variable
     * @return a boolean "true" value
     * */
    public static boolean deletePart(Part selectedPart) {
        allParts.remove(allParts.indexOf(selectedPart));
        return true;
    }

    /**
     * This function is not utilized by the program but could be implemented in a way which deletes a selected product object from the allProducts ObservableList variable.
     * @param selectedProduct the part to be removed from the allParts ObservableList variable
     * @return a boolean "true" value
     * */
    public static boolean deleteProduct(Product selectedProduct) {
        allProducts.remove(allParts.indexOf(selectedProduct));
        return true;
    }

    /**
     * This function returns the allParts ObservableList variable.
     * @return the allParts ObservableList variable
     * */
    public static ObservableList<Part> getAllParts() {
        return allParts;
    }

    /**
     * This function returns the allProducts ObservableList variable.
     * @return the allProducts ObservableList variable
     * */
    public static ObservableList<Product> getAllProducts() {
        return allProducts;
    }






}

package group.ims.Controllers;

import group.ims.Models.Part;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import java.io.IOException;

import static group.ims.Application.primaryStage;
import static group.ims.Models.Inventory.getAllParts;
import static group.ims.Models.Inventory.getAllProducts;

/**
 * This class controls what occurs within the "Modify Product" pane of the GUI.
 * */
public class ModifyProductController {
    public TextField Product_ID; // textfield where the product ID is entered within the GUI
    public TextField Product_Name; // textfield where the product name is entered within the GUI
    public TextField Product_InventoryLevel; // textfield where the product inventory level (stock) is entered within the GUI
    public TextField Product_Price; // textfield where the product price is entered within the GUI
    public TextField Product_Max; // textfield where the product max is entered within the GUI
    public TextField Product_Min; // textfield where the product min is entered within the GUI
    public TextField Product_Search; // textfield where the user enters search input
    public TableView<Part> partTableView; // Tableview where all parts are displayed

    public TableColumn <Part, Integer> partID; // Table column where all part IDs are displayed

    public TableColumn <Part, String> partName; // Table column where all part names are displayed

    public TableColumn <Part, Integer> part_InventoryLevel; // Table column where all part inventory levels (stocks) are displayed
    public TableColumn <Part, Double> part_Price; // Table column where all part prices are displayed
    public TableView<Part> apTableView; // Tableview where all associated parts (hence the "ap" prefix) are displayed
    public TableColumn <Part, Integer> apPartID; // Table column where all the associated part IDs are displayed
    public TableColumn <Part, String> apPartName; // Table column where all the associated part names are displayed
    public TableColumn <Part, Integer> ap_InventoryLevel; // Table column where all the associated part inventory levels (stocks) are displayed
    public TableColumn <Part, Double> ap_Price; // Table column where all the associated part prices are displayed
    public static int selectedIndex = 0; // a static variable accessed by the Main form to determine what Product index within the allProducts ObservableList is being modified - initialized to zero.

    /**
     * This function is called to change the scene of the JavaFX application to the scene corresponding with the Main form.
     * This function is used if a product is successfully added to the products list or if the user clicks the "Cancel" button in the "Modify Product" pane.
     * @throws IOException
     * */
    @FXML
    protected void switch_BackToMain() throws IOException {
        Scene scene;
        Parent root;
        FXMLLoader addPartView = new FXMLLoader(getClass().getResource("/group/ims/mainView.fxml"));
        root = addPartView.load();
        scene = new Scene(root, 1076, 520);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * This function is called to alert the user that a string value was entered in a textfield where an integer or double input was expected.
     * */
    private void numFormatError() {
        Alert alert = new Alert(Alert.AlertType.ERROR, "Invalid input has been provided. Make sure that strings are not entered where numbers are expected.");
        alert.show();
    }

    /**
     * This function determines if the user input matches an existing part ID or part name.
     * The user input can match portions of an existing part name; however, if the user inputs an integer to search by part ID, the provided input must match exactly an existing part ID.
     * This function is used in the search bar above the top tableview in the "Modify Product" pane.
     */
    @FXML
    public void searchParts() {

        ObservableList<Part> tempList = FXCollections.observableArrayList(); // a temporary list variable where objects can be added to if they match the search criteria
        String searchText = Product_Search.getText(); // a variable to hold the search input

        /*The loop below cycles through all parts to determine if any part ID or part name matches with the search input provided by the user.
        The loop first checks to see if the search input matches with an existing part ID. By default, the search expects an integer; however, if a
        string (part name) is provided, the search continues within the catch block.*/

        for (Part part : getAllParts()) {
            try {
                if (part.getId() == Integer.valueOf(searchText)) {
                    tempList.add(part);
                }
            } catch (NumberFormatException e) {
                if (part.getName().toLowerCase().startsWith(searchText.toLowerCase()) || part.getName().toLowerCase().contains(searchText.toLowerCase())) {
                    tempList.add(part);
                } else {
                    continue;
                }
            }
        }
        if (tempList.isEmpty()) {
            partTableView.setItems(getAllParts());
            numFormatError();
        } else {
            partTableView.setItems(tempList);
        }


    }

    /**
     * This is the first function called when the "Modify Product" pane is loaded, which initializes the top and bottom tableviews within the pane.
     * */
    @FXML
    public void initialize() {
        partTableView.setItems(getAllParts());
        partID.setCellValueFactory(new PropertyValueFactory<>("id"));
        partName.setCellValueFactory(new PropertyValueFactory<>("name"));
        part_InventoryLevel.setCellValueFactory(new PropertyValueFactory<>("stock"));
        part_Price.setCellValueFactory(new PropertyValueFactory<>("price"));

        apTableView.setItems(getAllProducts().get(selectedIndex).getAllAssociatedParts());
        System.out.println(selectedIndex);
        apPartID.setCellValueFactory(new PropertyValueFactory<>("id"));
        apPartName.setCellValueFactory(new PropertyValueFactory<>("name"));
        ap_InventoryLevel.setCellValueFactory(new PropertyValueFactory<>("stock"));
        ap_Price.setCellValueFactory(new PropertyValueFactory<>("price"));
    }

    /**
     * This function updates and saves information input into the various textfields within the "Modify Product" form for an already existing product.
     * Errors associated with (1) strings being used as input where integers/doubles are expected, (2) ensuring that the "min" value is less than the "max" value, and (3) ensuring that the "inv"
     * value is between the "min" and "max" values are all appropriately handled in this function.
     * @throws IOException
     * */
    @FXML
    public void saveProduct() throws IOException {

        /*The try/catch block is in place to handle if the user enters string data into a textfield that is expected an integer or double input value.
        So, if the user does in fact enter string data into a textfield that is expecting an integer or double, a NumberFormatException will be thrown, which is addressed
        in the catch block.*/

        try {
            getAllProducts().get(selectedIndex).setPrice(Double.valueOf(Product_Price.getText()));
            getAllProducts().get(selectedIndex).setStock(Integer.valueOf(Product_InventoryLevel.getText()));
            getAllProducts().get(selectedIndex).setMax(Integer.valueOf(Product_Max.getText()));
            getAllProducts().get(selectedIndex).setMin(Integer.valueOf(Product_Min.getText()));
            getAllProducts().get(selectedIndex).setId(Integer.valueOf(Product_ID.getText()));
            getAllProducts().get(selectedIndex).setName(Product_Name.getText());
        } catch (NumberFormatException e) {
            numFormatError();
        }

        /*The if/else block below determines if the input value for the Product_Min textfield is less than the input value for the Product_Max textfield.
         * Additionally, the if/else block determines if the input value for the Product_InventoryLevel textfield is a value between the input values for the Product_Min
         * and Product_Max textfields. */

        if ((Integer.valueOf(Product_Max.getText()) > Integer.valueOf(Product_Min.getText())) && (Integer.valueOf(Product_InventoryLevel.getText()) >= Integer.valueOf(Product_Min.getText()) && Integer.valueOf(Product_InventoryLevel.getText()) <= Integer.valueOf(Product_Max.getText()))) {
            switch_BackToMain();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR, "The inventory input must be a value between (inclusive) the max and min input values. Furthermore, the min input value must be less than the max input value.");
            alert.show();
        }
    }

    /**
     * This function takes a selected part within the top tableview of the "Modify Product" pane and copies the selected part into the bottom tableview of the "Modify Product" pane.
     * Using this function temporarily (until the "Save" button his clicked) adds information within the product's associatedParts list. The top tableview displays a list of all parts, whereas the bottom tableview displays the product's associatedParts.
     * */
    @FXML
    public void addAssociatedPart() {
        Part selectedItem = partTableView.getSelectionModel().getSelectedItem();
        getAllProducts().get(selectedIndex).addAssociatedPart(selectedItem);
        //apTableView.setItems(allProducts.get(selectedIndex).associatedParts);
    }
    /**
     * This function is called to remove a part included in the bottom tableview of the "Modify Product" pane (i.e., remove a selected part from a product's list of associatedParts).
     * This function is called when the user clicks the "Remove Associated Part" button in the "Modify Product" pane, assuming a part in the bottom tableview is already selected.
     * The function handles the error where a part is not selected within the bottom tableview prior to the "Remove Associated Part" button being clicked.
     * */
    @FXML
    public void removeAssocPart() {
        Part selectedItem = apTableView.getSelectionModel().getSelectedItem();
        getAllProducts().get(selectedIndex).getAllAssociatedParts().remove(selectedItem);
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "The part you have selected has been removed from this product's associated parts list.");
        alert.show();
    }
}

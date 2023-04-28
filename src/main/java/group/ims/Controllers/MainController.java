package group.ims.Controllers;

import group.ims.Models.Inhouse;
import group.ims.Models.Outsourced;
import group.ims.Models.Part;
import group.ims.Models.Product;
import javafx.application.Platform;
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
import static group.ims.Controllers.ModifyProductController.selectedIndex;
import static group.ims.Controllers.ModifyPartController.selectedPart;
import static group.ims.Models.Inventory.*;

/**
 * This class controls what occurs within the "Main" pane of the GUI.
 *
 * FUTURE ENHANCEMENT:
 * Future enhancements of this program could include displaying an image of parts and products within the Modify Part/Modify Product panes, in addition to including more data such as dimensional information
 * (e.g., a part's width, height, weight, etc.) for parts and products.
 *
 * */
public class MainController {
    public TableView<Part> partTableView; // Tableview where all parts are displayed

    public TableColumn<Part, Integer> partID; // Table column where all part IDs are displayed

    public TableColumn<Part, String> partName; // Table column where all part strings are displayed

    public TableColumn<Part, Integer> part_InventoryLevel; // Table column where all part inventory levels (stock) are displayed
    public TableColumn<Part, Double> part_Price; // Table column where all part prices are displayed
    public TableView<Product> productTableView; // Tableview where all products are displayed
    public TableColumn<Part, Integer> productID; // Table column where all product IDs are displayed
    public TableColumn<Part, String> productName; // Table column where all product names are displayed
    public TableColumn<Part, Integer> product_InventoryLevel; // Table column where all product inventory levels (stocks) are displayed
    public TableColumn<Part, Double> product_Price; // Table column where all product prices are displayed
    public TextField Part_Search; // Textfield where search input is entered relative to the partTableView
    public TextField Product_Search; // Textfield where search input is entered relative to the productTableView

    /**
     * This function is called to change the scene of the JavaFX application to the scene corresponding with the Add Part form.
     * @throws IOException
     * */
    @FXML
    protected void switch_AddPartView() throws IOException {
        Scene scene;
        FXMLLoader addPartView = new FXMLLoader(getClass().getResource("/group/ims/addPartView.fxml"));
        scene = new Scene(addPartView.load(), 1076, 602);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * This function is called to change the scene of the JavaFX application to the scene corresponding with the Add Product form.
     * @throws IOException
     * */
    @FXML
    protected void switch_AddProductView() throws IOException {
        Scene scene;
        FXMLLoader addPartView = new FXMLLoader(getClass().getResource("/group/ims/addProductView.fxml"));
        scene = new Scene(addPartView.load(), 1076, 602);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * This function is called to change the scene of the JavaFX application to the scene corresponding with the Modify Part form.
     * @throws IOException
     * */
    @FXML
    protected void switch_ModifyPartView() throws IOException {

        /*The outer if/else block handles whether a part object is slected in the partTableView.
        * The inner if/else block determines if the selected part is either an instance of an inhouse object or outsourced object.*/

        if (!partTableView.getSelectionModel().isEmpty()) {
            selectedPart = partTableView.getSelectionModel().getSelectedItem(); // selectedPart is a static variable within the ModifyPartController class... information assigned to this variable in the Main form will be available for use in the Modify Part form.
            Scene scene;
            Parent root;
            FXMLLoader modifyPartView = new FXMLLoader(getClass().getResource("/group/ims/modifyPartView.fxml"));
            root = modifyPartView.load();

            ModifyPartController modPartCont = modifyPartView.getController(); // creates an instance of the ModifyPartController so that pre-existing part data can be loaded into the textfields
            modPartCont.partID.setText(String.valueOf(selectedPart.getId())); // sets the partID textfield text to the selected part's ID value
            modPartCont.partMax.setText(String.valueOf(selectedPart.getMax())); // sets the partMax textfield text to the selected part's max value
            modPartCont.partMin.setText(String.valueOf(selectedPart.getMin())); // sets the partMin textfield text to the selected part's min value
            modPartCont.partPrice.setText(String.valueOf(selectedPart.getPrice())); // sets the partPrice textfield text to the selected part's price value
            modPartCont.partName.setText(selectedPart.getName()); // sets the partName textfield text to the selected part's name value
            modPartCont.part_InventoryLevel.setText(String.valueOf(selectedPart.getStock())); // sets the part_InventoryLevel textfield text to the selected part's stock value

            if (selectedPart instanceof Inhouse) {
                modPartCont.MachineID_CompanyName.setText("Machine ID");
                modPartCont.toggleField.setText(String.valueOf(((Inhouse) selectedPart).getMachineID()));
                modPartCont.inHouseButton.setSelected(true);
            } else {
                modPartCont.MachineID_CompanyName.setText("Company Name");
                modPartCont.toggleField.setText(((Outsourced)selectedPart).getCompanyName());
                modPartCont.outsourcedButton.setSelected(true);
            }
            scene = new Scene(root, 1076, 602);
            primaryStage.setScene(scene);
            primaryStage.show();
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Please select a part to modify.");
            alert.show();
        }
    }

    /**
     * This function is called to change the scene of the JavaFX application to the scene corresponding with the Modify Product form.
     * @throws IOException
     * */
    @FXML
    protected void switch_ModifyProductView() throws IOException {

        // The if/else block determines if a product is selected within the productTableView

        if (!productTableView.getSelectionModel().isEmpty()) {
            Product selected = productTableView.getSelectionModel().getSelectedItem();
            Parent root;
            Scene scene;
            FXMLLoader modifyProductView = new FXMLLoader(getClass().getResource("/group/ims/modifyProductView.fxml"));
            selectedIndex = getAllProducts().indexOf(selected); // selectedIndex is a static variable of the ModifyProductController class... information assigned to this variable in the Main form will be accessible for use in the Modify Product form.
            root = modifyProductView.load();

            ModifyProductController modProdCont = modifyProductView.getController(); // creates an instance of the ModifyProductController so that pre-existing product data can be loaded into the textfields
            modProdCont.Product_ID.setText(String.valueOf(selected.getId())); // sets the Product_ID textfield text to the selected product's ID value
            modProdCont.Product_InventoryLevel.setText(String.valueOf(selected.getStock())); // sets the Product_InventoryLevel textfield text to the selected product's stock value
            modProdCont.Product_Max.setText(String.valueOf(selected.getMax())); // sets the Product_Max textfield text to the selected product's max value
            modProdCont.Product_Min.setText(String.valueOf(selected.getMin())); // sets the Product_Min textfield text to the selected product's min value
            modProdCont.Product_Name.setText(String.valueOf(selected.getName())); // sets the Product_Name textfield text to the selected product's name value
            modProdCont.Product_Price.setText(String.valueOf(selected.getPrice())); // sets the Product_Price textfield text to the selected product's price value


            scene = new Scene(root, 1076, 602);
            primaryStage.setScene(scene);
            primaryStage.show();

        } else {

            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Please select a product to modify.");
            alert.show();

        }
    }

    /**
     * This function deletes a selected part from the part tableview within the Main form.
     * This function is called when the "Delete" button associated with the part tableview is clicked.
     * The function handles the error of the user not selecting a part in the part tableview prior to clicking the "Delete" button.
     * */
    @FXML
    protected void onDelete_Parts() {
        if (!partTableView.getSelectionModel().isEmpty()) {
            Part selectedItem = partTableView.getSelectionModel().getSelectedItem();
            getAllParts().remove(selectedItem);
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "The part you have selected has successfully been removed from the parts list.");
            alert.show();
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Please select a part to remove.");
            alert.show();
        }
    }

    /**
     * Deletes a selected product within the product tableview of the Main form.
     * This function is called when the "Delete" button associated with the product tableview is clicked.
     * This function checks (1) whether the selected product has any associated parts and (2) whether a product was selected within the product tableview when the "Delete" button is clicked. The function
     * handles errors associated with these checks.
     * */
    @FXML
    protected void onDelete_Products() {
        if (!productTableView.getSelectionModel().isEmpty()) {
            Product selectedItem = productTableView.getSelectionModel().getSelectedItem();
            if (!selectedItem.getAllAssociatedParts().isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "You are not allowed to delete a product that has associated parts.");
                alert.show();
            } else {
                getAllProducts().remove(selectedItem);
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "The product you have selected has successfully been removed from the product list.");
                alert.show();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Please select a product to remove.");
            alert.show();
        }
    }

    /**
     * This is the first function that is called when the Main form first loads. This function initializes the parts tableview on the left side of the Main form and the products tableview on the right side of the Main form.
     * */
    @FXML
    public void initialize(){
            partTableView.setItems(getAllParts());
            partID.setCellValueFactory(new PropertyValueFactory<>("id"));
            partName.setCellValueFactory(new PropertyValueFactory<>("name"));
            part_InventoryLevel.setCellValueFactory(new PropertyValueFactory<>("stock"));
            part_Price.setCellValueFactory(new PropertyValueFactory<>("price"));

            productTableView.setItems(getAllProducts());
            productID.setCellValueFactory(new PropertyValueFactory<>("id"));
            productName.setCellValueFactory(new PropertyValueFactory<>("name"));
            product_InventoryLevel.setCellValueFactory(new PropertyValueFactory<>("stock"));
            product_Price.setCellValueFactory(new PropertyValueFactory<>("price"));

    }

    /**
     * This function exits the JavaFX application and is called when the user clicks the "Exit" button within the Main form.
     * */
    @FXML
    public void exit() {
        Platform.exit();
    }

    /**
     * This function is called to handle errors that arise in the searchParts() function and the searchProducts() function as part of the MainController class.
     * When the search input provided by the user, relative to the Product and Part tableviews, does not match with any corresponding parts/products, this function is called.
     * */
    private void searchError() {
        Alert alert = new Alert(Alert.AlertType.ERROR, "The search input you have provided does not match any existing data.");
        alert.show();
    }

    /**
     * This function determines if the user input matches an existing part ID or part name.
     * The user input can match portions of an existing part name; however, if the user inputs an integer to search by part ID, the provided input must match exactly an existing part ID.
     * This function is used in the search bar above the left tableview of the Main form.
     */
    @FXML
    public void searchParts() {

        ObservableList<Part> tempList = FXCollections.observableArrayList(); // a temporary list variable where objects can be added to if they match the search criteria
        String searchText = Part_Search.getText(); // a variable to hold the search input

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
            searchError();
        } else {
            partTableView.setItems(tempList);
        }
    }

    /**
     * This function determines if the user input matches an existing product ID or product name.
     * The user input can match portions of an existing product name; however, if the user inputs an integer to search by product ID, the provided input must match exactly an existing product ID.
     * This function is used in the search bar above the left tableview of the Main form.
     */
    @FXML
    public void searchProducts() {

        ObservableList<Product> tempList = FXCollections.observableArrayList(); // a temporary list variable where objects can be added to if they match the search criteria
        String searchText = Product_Search.getText(); // a variable to hold the search input

        /*The loop below cycles through all products to determine if any product ID or product name matches with the search input provided by the user.
        The loop first checks to see if the search input matches with an existing product ID. By default, the search expects an integer; however, if a
        string (part name) is provided, the search continues within the catch block.*/

        for (Product product : getAllProducts()) {
            try {
                if (product.getId() == Integer.valueOf(searchText)) {
                    tempList.add(product);
                }
            } catch (NumberFormatException e) {
                if (product.getName().toLowerCase().startsWith(searchText.toLowerCase()) || product.getName().toLowerCase().contains(searchText.toLowerCase())) {
                    tempList.add(product);
                } else {
                    continue;
                }
            }
        }
        if (tempList.isEmpty()) {
            productTableView.setItems(getAllProducts());
            searchError();
        } else {
            productTableView.setItems(tempList);
        }
    }
}
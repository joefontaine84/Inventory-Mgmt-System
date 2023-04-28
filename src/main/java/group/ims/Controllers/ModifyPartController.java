package group.ims.Controllers;

import group.ims.Models.Inhouse;
import group.ims.Models.Outsourced;
import group.ims.Models.Part;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.io.IOException;
import static group.ims.Application.primaryStage;
import static group.ims.Models.Inventory.getAllParts;

/**
 * This class controls what occurs within the "Modify Part" pane of the GUI.
 * */
public class ModifyPartController {

    public static Part selectedPart; // a static part object variable that is utilized to hold the part object that is selected within the main form
    public RadioButton inHouseButton; // the "inhouse" radio button
    public RadioButton outsourcedButton; // the "outsourced" radio button

    public TextField partID; // textfield where the part ID is entered within the GUI

    public TextField partName; // textfield where the part name is entered within the GUI

    public TextField part_InventoryLevel; // textfield where the part inventory level (stock) is entered within the GUI
    public TextField partPrice; // textfield where the part price is entered within the GUI
    public TextField partMax; // textfield where the part max is entered within the GUI
    public TextField partMin; // textfield where the part min is entered within the GUI
    public TextField toggleField; // textfield where either the company name for an outsourced part or the machine ID for an inhouse part is entered within the GUI
    public Text MachineID_CompanyName; // text that either reads "Machine ID" or "Company Name" depending on whether the "inhouse" or "outsourced" radio button is selected

    /**
     * This function is called to change the scene of the JavaFX application to the scene corresponding with the Main form.
     * This function is used if a product is successfully added to the products list or if the user clicks the "Cancel" button in the "Modify Part" pane.
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
     * This function exists to appropriately switch the label text within the last field of the "Modify Part" pane; the text toggles between "Machine ID" and "Company Name".
     * This function is called when the user toggles between both the "In House" and "Outsourced" radio buttons in the "Modify Part" pane.
     * */
    @FXML
    public void buttonHandler() {
        if (inHouseButton.isSelected()) {
            MachineID_CompanyName.setText("Machine ID");
        } else {
            MachineID_CompanyName.setText("Company Name");
        }
    }

    /**
     * This function is called to alert the user that a string value was entered in a textfield where an integer or double input was expected.
     * */
    private void numFormatError() {
        Alert alert = new Alert(Alert.AlertType.ERROR, "Invalid input has been provided. Make sure that strings are not entered where numbers are expected.");
        alert.show();
    }

    /**
     * This function creates a new part within the parts list based off the data input into the various textfields of the "Modify Part" pane and deletes the part originally selected.
     *
     * LOGICAL/RUNTIME ERROR:
     * The logical/runtime errors encountered while creating this function was (1) properly handling a NumberFormatException that would occur if the user
     * enters strings where an integer or double is expected and (2) determining if the "inhouse" or "outsourced" radio button was selected and appropriately adjusting the product's object type based
     * on this selection. To solve the NumberFormatException error, the function utilizes a try/catch block. The NumberFormatException will be thrown if the user does in fact enter
     * a string in a textfield where an integer/double is expected, and if this occurs, then the code within the catch block will trigger to handle this exception. To solve the determination of whether
     * the modification to the part is being treated as a "inhouse" object or a "outsourced" object, the function makes use of an if/else statement to determine if the
     * respective inhouse/outsourced radio buttons are selected within the JavaFX GUI. The if/else statement will determine which type of object the part will become (or remain).
     *
     * This function also provides input validation to ensure that (1) the "min" value is less than the "max" value, and (2) the "inv" value is between the "min" and "max" values.
     * @throws IOException
     */
    @FXML
    public void savePart () throws IOException {

        /*The try/catch block is in place to handle if the user enters string data into a textfield that is expected an integer or double input value.
        So, if the user does in fact enter string data into a textfield that is expecting an integer or double, a NumberFormatException will be thrown, which is addressed
        in the catch block.*/

        try {
            int ID = Integer.valueOf(partID.getText());
            String name = partName.getText();
            double price = Double.valueOf(partPrice.getText());
            int inventory = Integer.valueOf(part_InventoryLevel.getText());
            int min = Integer.valueOf(partMin.getText());
            int max = Integer.valueOf(partMax.getText());

            /*The outer if/else block below determines if the input value for the partMin textfield is less than the input value for the partMax textfield.
             * Additionally, the outer if/else block determines if the input value for the part_InventoryLevel textfield is a value between the input values for the partMin
             * and partMax textfields.
             *
             * The inner if/else block assumes that values for part data were entered appropriately. This if/else block evaluates whether the part is an inhouse object or
             * an outsourced object. */

            if ((Integer.valueOf(partMax.getText()) > Integer.valueOf(partMin.getText())) && (Integer.valueOf(part_InventoryLevel.getText()) >= Integer.valueOf(partMin.getText()) && Integer.valueOf(part_InventoryLevel.getText()) <= Integer.valueOf(partMax.getText()))) {
                if (inHouseButton.isSelected()) {
                    int machineID = Integer.valueOf(toggleField.getText());
                    Part part = new Inhouse(ID, name, price, inventory, min, max, machineID);
                    getAllParts().add(part);
                } else {
                    String companyName = toggleField.getText();
                    Part part = new Outsourced(ID, name, price, inventory, min, max, companyName);
                    getAllParts().add(part);
                }
                getAllParts().remove(selectedPart);
                switch_BackToMain();
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR, "The inventory input must be a value between (inclusive) the max and min input values. Furthermore, the min input value must be less than the max input value.");
                alert.show();
            }
        } catch (NumberFormatException e) {
            numFormatError();
        }
    }
} // end bracket









